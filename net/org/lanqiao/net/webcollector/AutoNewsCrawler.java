package org.lanqiao.net.webcollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 1.爬虫类继承BreadthCrawler
 * @author hu
 */
public class AutoNewsCrawler extends BreadthCrawler {
// 编写构造器

  /**
   *
   * @param crawlPath 路径：存放这次爬虫配置的元数据
   * @param autoParse
   */
  public AutoNewsCrawler(String crawlPath, boolean autoParse) {
    super( crawlPath, autoParse );
    /*start page 先添加一个种子页面*/
    this.addSeed( "http://news.hfut.edu.cn/list-1-1.html" );

    /*fetch url like http://news.hfut.edu.cn/show-xxxxxx.html*/
    //从种子页还是做广度搜索，即解析种子页上的超链接，形如下列参数的url将会被加入爬取队列
    this.addRegex( "http://news.hfut.edu.cn/show-.*html" );
    /*do not fetch jpg|png|gif*/
    //形如下列参数的url，不进行抓取，jpg文件不要
    this.addRegex( "-.*\\.(jpg|png|gif).*" );
    /*do not fetch url contains #*/
    //任何带#不要
    this.addRegex( "-.*#.*" );
    //50个线程
    setThreads( 50 );
    //要前100个
    getConf().setTopN( 100 );
    //断点续爬
    setResumable(true);
  }



  @Override
  // 真正处理页面的逻辑在这里
  public void visit(Page page, CrawlDatums next) {
    String url = page.url();
    /*if page is news page*/
    //可以在这里再次对url规则进行判断
    if (page.matchUrl( "http://news.hfut.edu.cn/show-.*html" )) {

      /*extract title and content of news by css selector*/
      //提取页面的title
      String title = page.select( "div[id=Article]>h2" ).first().text();
      //提取页面的文本内容
      String content = page.selectText( "div#artibody" );

      System.out.println( "URL:\n" + url );
      System.out.println( "title:\n" + title );
      System.out.println( "content:\n" + content );

            /*If you want to add urls to crawl,add them to nextLink*/
            /*WebCollector automatically filters links that have been fetched before*/
            /*If autoParse is true and the link you add to nextLinks does not match the
              regex rules,the link will also been filtered.*/
      // next.add("http://xxxxxx.com");
    }
  }

  public static void main(String[] args) throws Exception {
    AutoNewsCrawler crawler = new AutoNewsCrawler( "crawl", true );
        /*start crawl with depth of 4*/
    crawler.start( 2 );
  }

}