package org.lanqiao.net.crawler.demo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.lanqiao.net.crawler.config.PageConfig;
import org.lanqiao.net.crawler.model.PageBean;

import com.alibaba.fastjson.JSON;

public class LagouGatherAction {
  private static File jobs = new File("/Users/zhengwei/Downloads/jobs-test.json");

  public static void main(String[] args) throws IOException {
    PageBean bean = PageConfig.of(null)
        .enableJs(10000)  // 启动js和ajax，这很重要
        .setReferer("https://www.lagou.com/")
        .setUrl("https://www.lagou.com/zhaopin/Java/?labelWords=label")
        .click("//a[contains(text(),'3年及以下')]")
        .setPrefix("https://www.lagou.com")
        .setXlink("//div[@class='p_top']/a[@class='position_link']")
        .setPaging("//div[@class='pager_container']/a[text()='下一页']", 1)
        .toPageBean();
    Set<String> subLinks = bean.getSubLinks();
//    这里只打印子链接用于测试
    for (String sublink : subLinks) {
      System.out.println("--------" + sublink + "--------");
    }
//    提取一个子页面上的数据用于测试
    dealSubPage(bean.reuseConfig(), subLinks.iterator().next());
  }

  private static void dealSubPage(PageConfig config, String sublink) {
    try {
      TimeUnit.SECONDS.sleep((long) (Math.random() * 11)); // 随机等待一些时间
    } catch (Exception e1) {
      Thread.currentThread().interrupt();
    }
    PageBean pb = config.setReferer(config.getUrl())
        .setUrl(sublink).setPaging(null, 0).setXlink(null)
        .addParam("company", "//div[@class='job-name']/*[@class='company']") // 公司
        .addParam("title", "//div[@class='job-name']/*[@class='name']") // 标题
        .addParam("request", "//*[@class='job_request']/p") // 职位要求
        .addParam("publish_time", "//*[@class='job_request']/p[2]") // 发布时间
        .addParam("job_bt", "//dd[@class='job_bt']") // 职位描述
        .addParam("work_addr", "//div[@class='work_addr']").toPageBean();
    String data = JSON.toJSONString(pb.getParams(), true);
    System.out.println(data);
//      saveToFile(data);
  }

  private static void saveObj(String data) {
    try {
      FileUtils.write(jobs, data+"\r\n", Charset.forName("utf-8"), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void saveLinks(PageBean bean) throws IOException {
    File job_links = new File("/Users/zhengwei/Downloads/job_links.json");
    for (String sublink : bean.getSubLinks()) {
      System.out.println(sublink);
      FileUtils.write(job_links, sublink+"\n", Charset.forName("utf-8"), true);
    }
  }
}
