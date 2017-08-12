package org.lanqiao.net.webcollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.google.gson.JsonObject;

/**
 * 抓取Ajax请求
 */
public class LagouJsonAndCookie extends BreadthCrawler {

  public LagouJsonAndCookie(String crawlPath, boolean autoParse) {
    super( crawlPath, autoParse );
    this.addSeed( new CrawlDatum( "https://www.lagou.com/jobs/positionAjax.json?gj=3%E5%B9%B4%E5%8F%8A%E4%BB%A5%E4%B8%8B&px=default&city=%E5%8C%97%E4%BA%AC&needAddtionalResult=false&isSchoolJob=0" )
        .meta( "method", "POST" )
        .meta( "outputData", "first=true&pn=1&kd=Java" )
    );
  }

  @Override
  public Page getResponse(CrawlDatum crawlDatum) throws Exception {
    HttpRequest request = new HttpRequest( crawlDatum.url() );
    request.setMethod( crawlDatum.meta( "method" ) );
    String outputData = crawlDatum.meta( "outputData" );
    if (outputData != null) {
      request.setOutputData( outputData.getBytes( "utf-8" ) );
    }
    request.setCookie( "user_trace_token=20170811220335-c596859a-0b67-48c2-8f7f-c6b995c8c2ff; _ga=GA1.2.1134021356.1502460224; _gid=GA1.2.1143714767.1502460224; LGUID=20170811220340-e10aeeba-7e9d-11e7-8564-5254005c3644; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1502460228,1502504737; index_location_city=%E5%8C%97%E4%BA%AC; SEARCH_ID=d5792102f4f84227b65e5bdc99963b98; JSESSIONID=ABAAABAAAFCAAEG10B8E130C639AD18BDDF363657158856; _gat=1; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1502504744; LGSID=20170812102532-83cfc37c-7f05-11e7-85e9-5254005c3644; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; LGRID=20170812102538-87d44e8c-7f05-11e7-a107-525400f775ce; TG-TRACK-CODE=index_navigation" );
    request.setUserAgent( "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0" );
    request.addHeader( "Accept","application/json, text/javascript, */*; q=0.01" );
    request.addHeader( "Referer","https://www.lagou.com/jobs/list_Java?px=default&gj=3%E5%B9%B4%E5%8F%8A%E4%BB%A5%E4%B8%8B&city=%E5%8C%97%E4%BA%AC" );

    return request.responsePage();
  }

  @Override
  public void visit(Page page, CrawlDatums next) {
    JsonObject jsonObject = page.jsonObject();
    System.out.println("JSON信息：" + jsonObject);
  }

  public static void main(String[] args) throws Exception {
    //因为是json，只抓一层
    new LagouJsonAndCookie( "lagouAjax",true ).start( 1 );
  }
}
