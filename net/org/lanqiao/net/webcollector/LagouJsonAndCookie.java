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
    request.setCookie( "index_location_city=%E6%88%90%E9%83%BD; Domain=.lagou.com; Expires=Wed, 20-Sep-2017 09:52:32 GMT;" );
    request.setUserAgent( "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0" );
    request.addHeader( "Accept","application/json, text/javascript, */*; q=0.01" );
    request.addHeader( "Referer","https://www.lagou.com/jobs/list_Java?px=default&gj=3%E5%B9%B4%E5%8F%8A%E4%BB%A5%E4%B8%8B&city=%E5%8C%97%E4%BA%AC" );

    Page page = request.responsePage();
    return page;
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
