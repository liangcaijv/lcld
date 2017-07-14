package org.lanqiao.net.crawler.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lanqiao.net.crawler.HtmlUnitParserTools;
import org.lanqiao.net.crawler.model.PageBean;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class PageConfig {
  public static final int            DEBUG_COUNT     = 10;
  public static final BrowserVersion BROWSER_VERSION = BrowserVersion.FIREFOX_52;

  private String                     name;
  private String                     url;
  private boolean                    usable;                                         // 是否可用
  private String                     prefix;                                         // 相对路径的前缀

  private String                     xlink;
  private String                     splitpath;                                      // 分页
  private List<String>               adverts         = new ArrayList<String>();      // 广告的xpath
  private Map<String, String>        params          = new HashMap<String, String>(); // 关注的页面信息的名字和xpath
  private boolean                    enableJs;
  private String                     referer;
  private Set<Cookie>                cookies;
  private int                        wait;
  private String                     nextPageHrefXpath;
  private int                        pageLimit       = 1;                            // 默认提取一页

  private PageConfig() {

  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public boolean isUsable() {
    return usable;
  }

  public String getPrefix() {
    return prefix;
  }

  public String getXlink() {
    return xlink;
  }

  public String getSplitpath() {
    return splitpath;
  }

  public List<String> getAdverts() {
    return adverts;
  }

  public boolean hadAdvert() {
    return adverts.size() > 0;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public boolean hadParam() {
    return params.size() > 0;
  }

  public static PageConfig of(String classPathXmlLocation) {
    PageConfig instance = new PageConfig();
    if (null == classPathXmlLocation) {
      return instance;
    }
    try (InputStream is = instance.getClass().getResourceAsStream(
        classPathXmlLocation)) {
      SAXReader reader = new SAXReader();
      Document doc = reader.read(is);
      Element root = doc.getRootElement();
      build(root, instance);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return instance;
  }

  private static void build(Element root, PageConfig instance) {
    instance.name = root.attributeValue("name");
    instance.usable = Boolean.parseBoolean(ObjectUtils.defaultIfNull(
        root.attributeValue("usable"), "false"));
    instance.url = root.attributeValue("url");
    instance.prefix = root.attributeValue("prefix");

    Element xlinkElement = root.element("xlink");
    if (null != xlinkElement) {
      instance.xlink = xlinkElement.attributeValue("xpath");
    }
    Element splitpathEle = root.element("splitpath");
    if (null != splitpathEle) {
      instance.splitpath = splitpathEle.attributeValue("xpath");
    }

    try {
      List<Element> advertElements = root.element("adverts").elements("advert");
      for (Element element : advertElements) {
        String xpath = element.attributeValue("xpath");
        if (StringUtils.isNotEmpty(xpath.trim())) {
          instance.adverts.add(xpath.trim());
        }
      }
    } catch (NullPointerException e) {
    }
    try {
      List<Element> paramElements = root.element("params").elements();
      for (Element element : paramElements) {
        String xpath = element.attributeValue("xpath");
        if (StringUtils.isNotEmpty(xpath.trim()))
          instance.params.put(element.getName(), xpath.trim());
      }
    } catch (NullPointerException e) {
    }
  }

  public PageBean toPageBean() {
    PageBean bean = new PageBean(this);
    // jdk 7 新用法
    try (WebClient webClient = HtmlUnitParserTools.buildWebClient(enableJs)) {
      HtmlPage hPage = dealCookies(webClient);
      webClient.waitForBackgroundJavaScript(wait);
      dealParams(bean, hPage);
      dealXlink(bean, webClient, hPage);
      bean.setName(name);
      bean.setUrl(url);
      return bean;
    }
  }

  private HtmlPage dealCookies(WebClient webClient) {
    try {
      HtmlPage hPage;
      if (null != referer) {
        hPage = webClient.getPage(referer);
        cookies = webClient.getCookieManager().getCookies();
        webClient.addRequestHeader("Referer", referer);
      }
      addCookies(webClient); // 添加服务端返回的cookies
      Objects.requireNonNull(this.url);
      hPage = webClient.getPage(this.url);
      return hPage;
    } catch (FailingHttpStatusCodeException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void dealParams(PageBean bean, HtmlPage hPage) {
    for (String k : params.keySet()) {
      String nodeValue = HtmlUnitParserTools.getNodeValue(hPage, params.get(k));
      if (StringUtils.isNotBlank(nodeValue)) {
        bean.getParams().put(k, nodeValue);
      }
    }
  }

  /**
   * 循环提取
   * 
   * @param bean
   * @param webClient
   * @param hPage
   */
  private void dealXlink(PageBean bean, WebClient webClient, HtmlPage hPage) {
    if (this.xlink != null) {
      int pageCount = 0;
      // 循环处理的次数
      while (pageCount < pageLimit) {
        // 链接节点
        List<DomNode> linkNodes = hPage.getByXPath(this.getXlink());
        if (linkNodes != null && !linkNodes.isEmpty()) {
          Set<String> xlinkSet = bean.getSubLinks();
          // ---处理每一个链接节点，提取超链接地址---
          for (DomNode node : linkNodes) {
            String href = node.getAttributes().getNamedItem("href")
                .getNodeValue();
            if (this.getPrefix() != null && href.startsWith(this.getPrefix())) {
              xlinkSet.add(href);
            }
            // 外部链接
            else if (href.startsWith("http:") || href.startsWith("https:")) {
              continue;
            } else if (this.getPrefix() != null) {
              xlinkSet.add(this.getPrefix() + href);
            } else {
              xlinkSet.add(href);
            }
          }
        }
        pageCount++;
        // 指向下一页的超链接
        if (nextPageHrefXpath != null) {
          try {
            List<DomNode> nextPageHrefNodes = hPage
                .getByXPath(nextPageHrefXpath);
            String nextPageHref = nextPageHrefNodes.get(0).getAttributes()
                .getNamedItem("href").getNodeValue();
            hPage = webClient.getPage(nextPageHref);
          } catch (FailingHttpStatusCodeException | IOException e) {
            throw new RuntimeException(e);
          } catch (Exception e) {
            continue;
          }
        }
      }
    }
  }

  private void addCookies(WebClient webClient) {
    for (Cookie cookie : cookies) {
      webClient.getCookieManager().addCookie(cookie);
    }
  }

  public PageConfig setUrl(String url) {
    this.url = url;
    return this;
  }

  public PageConfig setXlink(String xlink) {
    this.xlink = xlink;
    return this;
  }

  public PageConfig addParam(String name, String xPath) {
    getParams().put(name, xPath);
    return this;
  }

  public PageConfig setPrefix(String prefix) {
    this.prefix = prefix;
    return this;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.MULTI_LINE_STYLE);
  }

  public static void main(String[] args) {
    System.out.println(PageConfig.of("ftchinese-list.xml").toPageBean());
  }

  public PageConfig enableJs(int wait) {
    this.enableJs = true;
    this.wait = wait;
    return this;
  }

  public PageConfig setReferer(String referer) {
    this.referer = referer;
    return this;
  }

  /**
   * 循环采集配置
   * 
   * @param nextPageHrefXpath
   *          获取分页超链接的xpath
   * @param pageLimit
   *          页码限制
   * @return
   */
  public PageConfig setPaging(String nextPageHrefXpath, int pageLimit) {
    this.nextPageHrefXpath = nextPageHrefXpath;
    this.pageLimit = pageLimit;
    return this;
  }
}
