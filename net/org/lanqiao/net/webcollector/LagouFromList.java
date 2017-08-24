package org.lanqiao.net.webcollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 1.爬虫类继承BreadthCrawler
 * @author hu
 */
public class LagouFromList extends BreadthCrawler {
  private String regexLink = "https://www.lagou.com/jobs/[0-9]+.html.*";

  // 编写构造器

  /**
   *
   * @param crawlPath 路径：存放这次爬虫配置的元数据
   * @param autoParse
   */
  public LagouFromList(String crawlPath, boolean autoParse) {
    super( crawlPath, autoParse );
    /*start page 先添加一个种子页面*/
    // for (int i = 1; i <31 ; i++) {
      this.addSeed(new CrawlDatum("https://www.lagou.com/zhaopin/Java/2/?filterOption=2","list"));
    // }

    /*fetch url like http://news.hfut.edu.cn/show-xxxxxx.html*/
    //从种子页还是做广度搜索，即解析种子页上的超链接，形如下列参数的url将会被加入爬取队列
    // this.addRegex( regexLink );
    /*do not fetch jpg|png|gif*/
    //形如下列参数的url，不进行抓取，jpg文件不要
    this.addRegex( "-.*\\.(jpg|png|gif).*" );
    /*do not fetch url contains #*/
    //任何带#不要
    this.addRegex( "-.*#.*" );
    //50个线程
    setThreads( 50 );
    getConf().setExecuteInterval(5000);
    //要前100个
    getConf().setTopN( 100 );
    //断点续爬
    setResumable(false);
  }

  @Override
  /**
   * 自定义请求方式
   */
  public Page getResponse(CrawlDatum crawlDatum) throws Exception {
    HttpRequest request = new HttpRequest( crawlDatum.url() );

    String method = crawlDatum.meta("method");
    if(method!=null)
      request.setMethod(method);

    String outputData = crawlDatum.meta( "outputData" );
    if (outputData != null) {
      request.setOutputData( outputData.getBytes( "utf-8" ) );
    }
    // 设置cookie,修改为自己浏览器上的内容
    request.setCookie( "user_trace_token=20170713152851-eaddc72a-679c-11e7-a82b-5254005c3644; LGUID=20170713152851-eaddccef-679c-11e7-a82b-5254005c3644; JSESSIONID=ABAAABAACEBACDGF39B84F8F7C74EA527B7E9E4AA1E7186; X_HTTP_TOKEN=8f71dcf24abac9b5aa67edfba02d92e5; _putrc=36A084879D99C8DA; login=true; unick=%E9%83%91%E6%9C%AA; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; hasDeliver=0; TG-TRACK-CODE=index_navigation; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1503305318; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1503309164; SEARCH_ID=519e96d78b9542378938ab7af72b959b; index_location_city=%E6%88%90%E9%83%BD; _gat=1; LGSID=20170821164833-837d9377-864d-11e7-8e22-5254005c3644; LGRID=20170821180526-40e7e0da-8658-11e7-8e2a-5254005c3644; _ga=GA1.2.858636600.1499930930; _gid=GA1.2.180233387.1503305319" );
    //设置浏览器类型及版本
    request.setUserAgent( "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36" );
    //添加请求头
    request.addHeader( "Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8" );
    request.addHeader( "Referer","https://www.lagou.com/" );

    return request.responsePage();
  }

  @Override
  // 真正处理页面的逻辑在这里
  public void visit(Page page, CrawlDatums next) {
    if(page.url().matches(regexLink)){
      System.out.println("正在访问内容页：url=====" + page.url());
      String company = page.select("div.company").first().text();
      String name = page.select("span.name").first().text();
      String salary = page.select("span.salary").first().text();
      String experience = page.selectText(".job_request > p:nth-child(1) > span:nth-child(3)");
      String edu = page.selectText(".job_request > p:nth-child(1) > span:nth-child(4)");
      String type = page.selectText(".job_request > p:nth-child(1) > span:nth-child(5)");
      String description = page.selectText(".job_bt > div:nth-child(2)");
      String work_addr = page.selectText(".work_addr");
      StringBuilder sb = new StringBuilder();
      sb.append(company).append("\n")
          .append(name).append("\n")
          .append(salary).append("\n")
          .append(experience).append("\n")
          .append(edu).append("\n")
          .append(type).append("\n")
          .append(description).append("\n")
          .append(work_addr).append("\n");
      System.out.println(sb);
    }else {
      System.out.println("正在访问列表页：url====="+page.url());
      next.add(page.regexLinks(regexLink));
    }

  }

  public static void main(String[] args) throws Exception {
    LagouFromList crawler = new LagouFromList( "lagou-home-page", true );
        /*start crawl with depth of 4*/
    crawler.start( 2 );
  }

}