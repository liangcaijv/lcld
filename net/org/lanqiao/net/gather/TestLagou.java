package org.lanqiao.net.gather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class TestLagou {
  private static Set<Cookie> cookies;

  public static void main(String[] args) throws FailingHttpStatusCodeException, IOException {

    String url = "https://www.lagou.com/jobs/list_Java?px=default&gj=3%E5%B9%B4%E5%8F%8A%E4%BB%A5%E4%B8%8B&city=%E5%8C%97%E4%BA%AC";//想采集的网址
//    String refer = "http://outofmemory.cn/";
    URL link = new URL(url);
    WebRequest request = new WebRequest(link);
    request.setCharset(Charset.forName("UTF-8"));

    //    request.setProxyHost("120.120.120.x");
    //    request.setProxyPort(8080);

    WebClient wc = new WebClient(BrowserVersion.FIREFOX_52);
    wc.getOptions().setJavaScriptEnabled(false);//开启js解析。对于变态网页，这个是必须的
    wc.getOptions().setCssEnabled(false);//开启css解析。对于变态网页，这个是必须的。
    wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
    wc.getOptions().setThrowExceptionOnScriptError(false);
//    wc.getOptions().setTimeout(10000);
    String[] headerText = {
            "Host: www.lagou.com",
            "Connection: keep-alive",
            "Pragma: no-cache",
            "Cache-Control: no-cache",
            "Upgrade-Insecure-Requests: 1",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36",
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
            "Referer: https://www.lagou.com/zhaopin/Java/?labelWords=label",
            "Accept-Encoding: gzip, deflate, sdch, br",
            "Accept-Language: zh-CN,zh;q=0.8"
    };
    ////设置请求报文头里的User-Agent字段
    //wc.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
    //wc.addRequestHeader和request.setAdditionalHeader功能应该是一样的。选择一个即可。
    //其他报文头字段可以根据需要添加
    addHeader(wc, headerText);

    wc.getCookieManager().setCookiesEnabled(true);//开启cookie管理
    //设置cookie。如果你有cookie，可以在这里设置
    if (cookies == null) {
      String cookieText = "JSESSIONID=ABAAABAAAFCAAEG128591F2D39CBA0524A7B5686018D11F; user_trace_token=20170713221207-40f3f649-67d5-11e7-a85f-5254005c3644; PRE_UTM=m_cf_cpt_baidu_pc; PRE_HOST=bzclk.baidu.com; PRE_SITE=http%3A%2F%2Fbzclk.baidu.com%2Fadrc.php%3Ft%3D06KL00c00fATEwT0J_Kg0FNkUsa1xvIu00000rhhYH300000XbTzTM.THL0oUhY1x60UWdBmy-bIfK15H61mWR4PjuBnj0snHPhnyc0IHd7nbn3PjIawjRsPjKanHD1rRRsP1RvPYwAnbRkfWTsnsK95gTqFhdWpyfqnWbLrH03nj0znBusThqbpyfqnHm0uHdCIZwsT1CEQLILIz4_myIEIi4WUvYE5LNYUNq1ULNzmvRqUNqWu-qWTZwxmh7GuZNxTAn0mLFW5HcknWRv%26tpl%3Dtpl_10085_15673_1%26l%3D1053927145%26attach%3Dlocation%253D%2526linkName%253D%2525E6%2525A0%252587%2525E9%2525A2%252598%2526linkText%253D%2525E3%252580%252590%2525E6%25258B%252589%2525E5%25258B%2525BE%2525E7%2525BD%252591%2525E3%252580%252591%2525E5%2525AE%252598%2525E7%2525BD%252591-%2525E4%2525B8%252593%2525E6%2525B3%2525A8%2525E4%2525BA%252592%2525E8%252581%252594%2525E7%2525BD%252591%2525E8%252581%25258C%2525E4%2525B8%25259A%2525E6%25259C%2525BA%2526xp%253Did%28%252522m260704b2%252522%29%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FH2%25255B1%25255D%25252FA%25255B1%25255D%2526linkType%253D%2526checksum%253D128%26ie%3DUTF-8%26f%3D8%26tn%3Dbaidu%26wd%3D%25E6%258B%2589%25E5%258B%25BE%25E7%25BD%2591%26oq%3D%25E6%258B%2589%25E5%258B%25BE%25E7%25BD%2591%26rqlang%3Dcn; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F%3Futm_source%3Dm_cf_cpt_baidu_pc; LGUID=20170713221207-40f3ffe5-67d5-11e7-a85f-5254005c3644; index_location_city=%E5%8C%97%E4%BA%AC; TG-TRACK-CODE=index_navigation; SEARCH_ID=9485e82452e14b5c837bac4073de7189; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1499955122; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1499955170; _gid=GA1.2.543881174.1499955122; _ga=GA1.2.792726271.1499955122; LGSID=20170713221207-40f3fba5-67d5-11e7-a85f-5254005c3644; LGRID=20170713221258-5f2a80d8-67d5-11e7-a85f-5254005c3644";
      cookies = textToCookies(cookieText);
    }
    Iterator<Cookie> iter = cookies.iterator();
    while (iter.hasNext()) {
      wc.getCookieManager().addCookie(iter.next());
    }

    //------------------准备工作已经做好了-------------------
    HtmlPage page = wc.getPage(request);
    if (page == null) {
      System.out.println("采集 " + url + " 失败!!!");
      return;
    }
    String content = page.asText();//网页内容保存在content里
    if (content == null) {
      System.out.println("采集 " + url + " 失败!!!");
      return;
    }
    System.out.println(content);
    //搞定了
    CookieManager CM = wc.getCookieManager(); //WC = Your WebClient's name
    cookies = CM.getCookies();//返回的Cookie在这里，下次请求的时候可能可以用上啦。
  }

  private static Set<Cookie> textToCookies(String cookieText) {
    Set<Cookie> cookies = new HashSet<>();
    String[] cookie = cookieText.split(";");
    for (String kv : cookie) {
      String[] split = kv.split("=");
      Cookie coo = new Cookie("www.lagou.com",split[0].trim(),split[1].trim());
    }
    return cookies;
  }

  private static void addHeader(WebClient wc, String[] headerText) {
    for (String kv : headerText) {
      String[] split = kv.split(":");
      String name = split[0];
      String value = split[1];
      wc.addRequestHeader(name, value);
    }
  }
}
