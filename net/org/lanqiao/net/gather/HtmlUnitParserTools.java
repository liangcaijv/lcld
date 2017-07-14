package org.lanqiao.net.gather;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lanqiao.net.gather.config.PageConfig;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitParserTools {

  private static final String HREF = "href";

  public static WebClient buildWebClient(boolean isScriptEnables) {
    WebClient client = new WebClient(PageConfig.BROWSER_VERSION);
    client.setJavaScriptEnabled(isScriptEnables);
    return client;
  }

  /**
   * @param page
   * @param gsite
   */
  public static void removeAdvert(HtmlPage page, PageConfig pageConfig) {
    if (pageConfig.hadAdvert()) {
      HashSet<DomNode> adset = new HashSet<DomNode>();
      for (String advert : pageConfig.getAdverts()) {
        adset.addAll((List<DomNode>) page.getByXPath(advert));
      }
      for (DomNode node : adset) {
        try {
          DomNode pnode = node.getParentNode();
          pnode.removeChild(node);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }

  }

  public static String getNodeHref(HtmlPage page, String xpath) {
    if (isEmpty(xpath))
      return "";
    List<DomNode> nodes = (List<DomNode>) page.getByXPath(xpath);
    if (nodes != null && !nodes.isEmpty())
      for (DomNode node : nodes) {
        if (node.getAttributes().getLength() > 0) {
          return node.getAttributes().getNamedItem(HREF).getNodeValue();
        }
      }
    return "";
  }

  public static String getNodeValue(HtmlPage page, String xpath) {
    if (isEmpty(xpath))
      return "";
    List<DomNode> nodes = (List<DomNode>) page.getByXPath(xpath);
    if (nodes != null && !nodes.isEmpty())
      for (DomNode node : nodes) {
        if (isNotEmpty(node.asText())) {
          return node.asText().trim();
        }
      }
    return "";
  }

  public static String getContent(HtmlPage page, String xpath) {
    if (isEmpty(xpath))
      return "";
    String str = "";
    List<DomNode> nodes = (List<DomNode>) page.getByXPath(xpath);
    if (nodes != null && !nodes.isEmpty())
      for (DomNode node : nodes) {

        if (isNotEmpty(node.asText())) {
          String content = node.asText().trim();
          str += content;
        }
      }
    return str;
  }

  private static String[] timeFormats = {
      "\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d",//yyyy-MM-dd hh-mm-ss
      "\\d\\d\\d\\d-\\d\\d-\\d\\d", "\\d\\d\\d\\d年\\d\\d月\\d\\d日\\d\\d:\\d\\d",
      "\\d\\d\\d\\d年\\d\\d月\\d\\d日"  };

  public static boolean isEmpty(String val) {
    if (val == null || val.trim().equals(""))
      return true;
    return false;
  }

  public static boolean isNotEmpty(String val) {
    return !isEmpty(val);
  }

  public static String getTimeLine(String str) {
    if (isEmpty(str))
      return "";

    String result = "";

    for (String format : timeFormats) {
      Pattern p = Pattern.compile(format);
      Matcher m = p.matcher(str);
      if (m.find()) {
        result = m.group();
        break;
      }
    }
    return result;
  }
}
