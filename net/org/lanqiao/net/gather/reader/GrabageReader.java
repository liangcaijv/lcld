package org.lanqiao.net.gather.reader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lanqiao.net.gather.config.PageConfig;
import org.lanqiao.net.gather.config.PageConfigMark;
import org.lanqiao.net.gather.config.SiteConfig;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.Throwables;

/**
 * 为广大采集工具添砖加瓦，提供客户端的运行平台 
 * @作者 Gabriel 
 * @博客 http://blog.csdn.net/zdsdiablo
 */
public class GrabageReader {

  private static final int    DEBUG_COUNT      = 5;

  private static final String ENABLED          = "enabled";
  private static final String HREF             = "href";

  private static final String CONFIG_FILE_PATH = "grabage-config.xml";

  private final WebClient     webClient;

  private List<SiteConfig>      sites;

  public GrabageReader() {
    webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);
    sites = new ArrayList<SiteConfig>();
  }

  //重新加载订阅信息
  public void reLoadSubConfig() {
    try {
      InputStream inputStream = getClass().getResourceAsStream(CONFIG_FILE_PATH);
      SAXReader saxReader = new SAXReader();
      Document doc = saxReader.read(inputStream);
      Element root = doc.getRootElement();
      //解析htmlunit配置
      Element configItem = root.element(PageConfigMark.CONFIG.toString());
      //String version = configItem.element("browser").attributeValue("version").trim();

      //解析采集站点的配置
      List<Element> gatherItems = root.element(PageConfigMark.GRABAGE_ARRAY.toString())
          .elements(PageConfigMark.GRABAGE.toString());
      sites.clear();
      //      依次解析配置项
      for (Element gatherItem : gatherItems) {
        SiteConfig site = new SiteConfig();
        site.setUsable(gatherItem.attributeValue(PageConfigMark.USABLE.toString()).trim());
        site.setForward(gatherItem.attributeValue(PageConfigMark.FORWARD.toString()).trim());
        site.setLink(gatherItem.attributeValue(PageConfigMark.LINK.toString()).trim());
        //
        Element title = gatherItem.element(PageConfigMark.TITLE.toString());
        if (title != null)
          site.setTitle(title.attributeValue(PageConfigMark.XPATH.toString()));

        Element author = gatherItem.element(PageConfigMark.AUTHOR.toString());
        if (author != null)
          site.setAuthor(author.attributeValue(PageConfigMark.XPATH.toString()));

        Element source = gatherItem.element(PageConfigMark.SOURCE.toString());
        if (source != null)
          site.setSource(source.attributeValue(PageConfigMark.XPATH.toString()));

        Element timeline = gatherItem.element(PageConfigMark.TIMELINE.toString());
        if (timeline != null)
          site.setTimeline(timeline.attributeValue(PageConfigMark.XPATH.toString()));

        Element summary = gatherItem.element(PageConfigMark.SUMMARY.toString());
        if (summary != null)
          site.setSummary(summary.attributeValue(PageConfigMark.XPATH.toString()));

        Element content = gatherItem.element(PageConfigMark.CONTENT.toString());
        if (content != null)
          site.setContent(content.attributeValue(PageConfigMark.XPATH.toString()));

        Element splitpath = gatherItem.element(PageConfigMark.SPLIT.toString());
        if (splitpath != null)
          site.setSplitpath(splitpath.attributeValue(PageConfigMark.XPATH.toString()));

        //暂时只做一层，理论上可多层深入
        Element xlink = gatherItem.element(PageConfigMark.XLINK.toString());
        if (xlink != null)
          site.setXlink(xlink.attributeValue(PageConfigMark.XPATH.toString()));

        sites.add(site);
      }

    } catch (Exception e) {
      Throwables.throwIfUnchecked(e);
    }
  }

  public void startReadPage() {
    long lasting = System.currentTimeMillis();
    System.out.println("[grabage]start:" + lasting);
    try {
      List<PageConfig> pbList = reloadPageConfig();

      for (PageConfig pb : pbList) {
        System.out.println("link:" + pb.getLink() + "\ttitle:" + pb.getTitle());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    lasting = System.currentTimeMillis();
    System.out.println("[grabage]complete:" + lasting);
  }

  //重新读取页面抓取配置
  private List<PageConfig> reloadPageConfig() throws Exception {
    List<PageConfig> pbList = new ArrayList<PageConfig>();

    HtmlPage page = null;
    for (SiteConfig site : sites) {
      if (UtilTools.isNotEmpty(site.getUsable()) && site.getUsable().equals("false"))
        continue;
      System.out.println(">>>LINK>>>" + site.getLink());

      page = (HtmlPage) webClient.getPage(site.getLink());
      //解析规则
      HashSet<String> linkSet = new HashSet<String>();

      List<DomNode> linkNodes = page.getByXPath(site.getXlink());
      if (linkNodes != null && !linkNodes.isEmpty())
        for (DomNode node : linkNodes) {
          String href = node.getAttributes().getNamedItem(HREF).getNodeValue();

          if (href.startsWith(site.getForward())) {
            linkSet.add(href);
          }
          //取消外部链接
          else if (href.startsWith("http:") || href.startsWith("https:")) {
            continue;
          }
          //保留相对链接
          else {
            linkSet.add(site.getForward() + href);
          }
        }
      int i = 0;
      for (String link : linkSet) {
        i++;
        if (i > DEBUG_COUNT)
          break;
        PageConfig pageInfo = new PageConfig();
        pageInfo.setLink(link);
        try {
          page = (HtmlPage) webClient.getPage(pageInfo.getLink());
          //移除广告
          removeAdvert(page, site);
          //标题
          pageInfo.setTitle(getNodeValue(page, site.getTitle()));
          //作者
          pageInfo.setAuthor(getNodeValue(page, site.getAuthor()));
          //来源
          pageInfo.setSource(getNodeValue(page, site.getSource()));
          //发布时间
          pageInfo.setTimeline(UtilTools.getTimeLine(getNodeValue(page, site.getTimeline())));
          //简介
          pageInfo.setSummary(getNodeValue(page, site.getSummary()));
          //内容
          pageInfo.setContent(getNodeValues(page, site.getContent()));

          //分页
          searchNextPage(page, pageInfo, site);

          pbList.add(pageInfo);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }

    }
    return pbList;
  }

  /**
   * 移除广告位
   * @param page
   * @param siteInfo
   */
  private void removeAdvert(HtmlPage page, SiteConfig siteInfo) {
    if (siteInfo.hasAdvert()) {
      HashSet<DomNode> adset = new HashSet<DomNode>();
      for (String xadvert : siteInfo.getAdvert()) {
        adset.addAll(page.getByXPath(xadvert));
      }
      for (DomNode node : adset) {
        try {
          DomNode pnode = node.getParentNode();
          pnode.removeChild(node);
        } catch (Exception ex) {
          //可能有嵌套广告的情况，捕获此异常
          ex.printStackTrace();
        }
      }
    }

  }

  /**
   * 寻找次页
   * @param page
   * @param pb
   * @param siteInfo
   */
  private void searchNextPage(HtmlPage page, PageConfig pb, SiteConfig siteInfo) {
    if (UtilTools.isNotEmpty(siteInfo.getSplitpath())) {
      String href = getNodeHref(page, siteInfo.getSplitpath());
      if (UtilTools.isEmpty(href))
        return;
      String nextlink = "";
      if (href.startsWith(siteInfo.getForward())) {
        nextlink = href;
      } else {
        nextlink = siteInfo.getForward() + href;
      }
      try {
        page = (HtmlPage) webClient.getPage(nextlink);
        pb.setContent(pb.getContent() + getNodeValues(page, siteInfo.getContent()));

        searchNextPage(page, pb, siteInfo);
      } catch (Exception ex) {
        return;
      }
    }
  }

  private String getNodeHref(HtmlPage page, String xpath) {
    if (UtilTools.isEmpty(xpath))
      return "";
    List<DomNode> nodes = page.getByXPath(xpath);
    if (nodes != null && !nodes.isEmpty())
      for (DomNode node : nodes) {
        if (node.getAttributes().getLength() > 0) {
          return node.getAttributes().getNamedItem(HREF).getNodeValue();
        }
      }
    return "";
  }

  private String getNodeValue(HtmlPage page, String xpath) {
    if (UtilTools.isEmpty(xpath))
      return "";
    List<DomNode> nodes = page.getByXPath(xpath);
    if (nodes != null && !nodes.isEmpty())
      for (DomNode node : nodes) {
        if (UtilTools.isNotEmpty(node.asText())) {
          return node.asText().trim();
        }
      }
    return "";
  }

  private String getNodeValues(HtmlPage page, String xpath) {
    if (UtilTools.isEmpty(xpath))
      return "";
    String str = "";
    List<DomNode> nodes = page.getByXPath(xpath);
    if (nodes != null && !nodes.isEmpty())
      for (DomNode node : nodes) {
        if (UtilTools.isNotEmpty(node.asText())) {
          str += node.asText().trim();
        }
      }
    return str;
  }

}