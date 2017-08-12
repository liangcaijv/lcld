package org.lanqiao.net.webcollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 抓取一个单页
 */
public class LagouSinglePage extends BreadthCrawler {
  @Override
  public Page getResponse(CrawlDatum crawlDatum) throws Exception {
    return super.getResponse( crawlDatum );
  }

  /**
   * 构造一个基于伯克利DB的爬虫
   * 伯克利DB文件夹为crawlPath，crawlPath中维护了历史URL等信息
   * 不同任务不要使用相同的crawlPath
   * 两个使用相同crawlPath的爬虫并行爬取会产生错误
   *  @param crawlPath 伯克利DB使用的文件夹
   * @param autoParse 是否根据设置的正则自动探测新URL
   */
  public LagouSinglePage(String crawlPath, boolean autoParse) {
    super( crawlPath, autoParse );
    // 添加种子
    this.addSeed( "https://www.lagou.com/jobs/2852069.html", "content" );
    getConf().setExecuteInterval( 10000 );

  }

  public static void main(String[] args) throws Exception {
    LagouSinglePage crawler = new LagouSinglePage("lagou",false  );
    crawler.start( 1 );
  }

  @Override
  public void visit(Page page, CrawlDatums next) {
    String company = page.select( "div.company" ).first().text();
    String name = page.select( "span.name" ).first().text();
    String salary = page.select( "span.salary" ).first().text();
    String experience = page.selectText( ".job_request > p:nth-child(1) > span:nth-child(3)" );
    String edu = page.selectText( ".job_request > p:nth-child(1) > span:nth-child(4)" );
    String type = page.selectText( ".job_request > p:nth-child(1) > span:nth-child(5)" );
    String description = page.selectText( ".job_bt > div:nth-child(2)" );
    String work_addr = page.selectText( ".work_addr" );
    StringBuilder sb = new StringBuilder(  );
    sb.append( company ).append( "\n" )
        .append( name ).append( "\n" )
        .append( salary ).append( "\n" )
        .append( experience ).append( "\n" )
        .append( edu ).append( "\n" )
        .append( type ).append( "\n" )
        .append( description ).append( "\n" )
        .append( work_addr ).append( "\n" );
    System.out.println(sb);
  }
}
