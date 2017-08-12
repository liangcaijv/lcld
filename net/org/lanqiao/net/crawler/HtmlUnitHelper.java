package org.lanqiao.net.crawler;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUnitHelper {

  private static final String HREF = "href";


  public static WebClient buildWebClient(boolean isScriptEnables) {
    WebClient client = new WebClient( PageBuilder.BROWSER_VERSION);
    if (isScriptEnables) {
      client.getOptions().setJavaScriptEnabled(true);
      client.getOptions().setCssEnabled(true);
      client.getOptions().setThrowExceptionOnFailingStatusCode(false);
      client.getOptions().setThrowExceptionOnScriptError(false);
      client.getOptions().setRedirectEnabled(false);
      client.getOptions().setAppletEnabled(false);

      client.setCssErrorHandler(new SilentCssErrorHandler());
      client.setAjaxController(new NicelyResynchronizingAjaxController());

      client.getCookieManager().setCookiesEnabled(true);
    }
    return client;
  }

  /**
   * 移除
   * @param page
   * @param xpath
   */
  public static void remove(HtmlPage page, List<String> xpath) {
      HashSet<DomNode> adSet = new HashSet<>();
      for (String advert : xpath) {
        adSet.addAll(page.getByXPath(advert));
      }
      for (DomNode node : adSet) {
        try {
          DomNode pnode = node.getParentNode();
          pnode.removeChild(node);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
  }

  /**
   * 返回xpath制定的超链接的链接
   * @param page
   * @param xpath
   * @return
   */
  public static String getNodeHref(HtmlPage page, String xpath) {
    if (isEmpty(xpath))
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

  /**
   * 返回第一个匹配xpath的节点的文本内容
   * @param page
   * @param xpath
   * @return
   */
  public static String getNodeValue(HtmlPage page, String xpath) {
    if (isEmpty(xpath))
      return "";
    List<DomNode> nodes = page.getByXPath(xpath);
    if (nodes != null && !nodes.isEmpty())
      for (DomNode node : nodes) {
        if (isNotEmpty(node.asText())) {
          return node.asText().trim();
        }
      }
    return "";
  }

  /**
   * 拼接xpath的内容并返回
   * @param page
   * @param xpath
   * @return
   */
  public static String getContent(HtmlPage page, String xpath) {
    if (isEmpty(xpath))
      return "";
    String str = "";
    List<DomNode> nodes = page.getByXPath(xpath);
    if (nodes != null && !nodes.isEmpty())
      for (DomNode node : nodes) {
        if (isNotEmpty(node.asText())) {
          String content = node.asText().trim();
          str += content;
        }
      }
    return str;
  }

  public static boolean isEmpty(String val) {
    if (val == null || val.trim().equals(""))
      return true;
    return false;
  }

  public static boolean isNotEmpty(String val) {
    return !isEmpty(val);
  }

}
