//package org.lanqiao.net.crawler;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
//import java.util.Set;
//
//import com.gargoylesoftware.htmlunit.BrowserVersion;
//import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
//import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
//import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.WebRequest;
//import com.gargoylesoftware.htmlunit.html.DomNode;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
//import com.gargoylesoftware.htmlunit.util.Cookie;
//
//public class WorldBankCrawl {
//
//  public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//    WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52);
//    webClient.setCssErrorHandler(new SilentCssErrorHandler());
//    webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//    webClient.getOptions().setCssEnabled(true);
//    webClient.getOptions().setPopupBlockerEnabled(true);
//    webClient.getOptions().setRedirectEnabled(false);
//    webClient.getOptions().setAppletEnabled(false);
//    webClient.getOptions().setJavaScriptEnabled(true);
//    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//    webClient.getOptions().setThrowExceptionOnScriptError(false);
//    webClient.getOptions().setTimeout(50000);
//    
//    webClient.getCookieManager().setCookiesEnabled(true);
//    String Referer = "https://www.lagou.com/";
//    webClient.getPage(Referer);
//    Set<Cookie> cookies = webClient.getCookieManager().getCookies();
//    for (Cookie cookie : cookies) {
//      webClient.getCookieManager().addCookie(cookie);
//    }
//    
//    String current="https://www.lagou.com/zhaopin/Java/?labelWords=label";
//    for (int i = 0; i < 10; i++) {
//      webClient.addRequestHeader("Referer", Referer);
//      HtmlPage page = webClient.getPage(current);
//      webClient.waitForBackgroundJavaScript(10000);
//      List<DomNode> nodes = page.getByXPath("//div[@class='p_top']/a");
//      for (DomNode domNode : nodes) {
//        System.out.println(domNode.getAttributes().getNamedItem("href").getNodeValue());
//      }
//      try {
//        DomNode nextPage = (DomNode) page.getByXPath("//a[@class='page_no']").get(0);
//        Referer = current;
//        current = nextPage.getAttributes().getNamedItem("href").getNodeValue();
//      } catch (Exception e) {
//        e.printStackTrace();
//        break;
//      }
//      
//    }  
//    webClient.close();
//  }
//}
