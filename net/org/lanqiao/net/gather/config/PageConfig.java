package org.lanqiao.net.gather.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.tools.ant.taskdefs.optional.javah.Kaffeh;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lanqiao.net.gather.HtmlUnitParserTools;
import org.lanqiao.net.gather.model.PageBean;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PageConfig {
  public static final int            DEBUG_COUNT     = 10;
  public static final BrowserVersion BROWSER_VERSION = BrowserVersion.FIREFOX_3;
  
  private String                     name;
  private String                     url;
  private boolean                    usable;                                         // 是否可用
  private boolean                     isAjax;
  private String                     prefix;                                         // 相对路径的前缀

  private String                     xlink;
  private String                     splitpath;                                      // 分页
  private List<String>               adverts         = new ArrayList<String>();      // 广告的xpath
  private Map<String, String>        params          = new HashMap<String, String>(); // 关注的页面信息的名字和xpath

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
    PageBean bean = new PageBean();
    WebClient webClient = HtmlUnitParserTools.buildWebClient(false);
    HtmlPage hPage;
    try {
      Objects.requireNonNull(this.url);
      hPage = (HtmlPage) webClient.getPage(this.url);
      if (isAjax) {
        bean.setAllContent(hPage.asText());
      }
    } catch (FailingHttpStatusCodeException | IOException e) {
      throw new RuntimeException(e);
    }
    if (this.xlink != null) {
      List<DomNode> linkNodes = (List<DomNode>) hPage.getByXPath(this
          .getXlink());
      if (linkNodes != null && !linkNodes.isEmpty()) {
        Set<String> xlinkSet = new HashSet<String>();
        for (DomNode node : linkNodes) {
          String href = node.getAttributes().getNamedItem("href")
              .getNodeValue();
          Objects.requireNonNull(this.getPrefix());
          if (href.startsWith("http:") || href.startsWith("https:")) {
            continue;
          } else if (href.startsWith(this.getPrefix())) {
            xlinkSet.add(href);
          } else {
            xlinkSet.add(this.getPrefix() + href);
          }
          bean.getSubLinks().addAll(xlinkSet);
        }
      }
    }
    for (String k : params.keySet()) {
      bean.getParams().put(k,
          HtmlUnitParserTools.getNodeValue(hPage, params.get(k)));
    }
    bean.setName(name);
    bean.setUrl(url);
    return bean;

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

  public PageConfig setAjax(boolean isAjax) {
    this.isAjax = isAjax;
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
}
