package org.lanqiao.net.crawler.demo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.lanqiao.net.crawler.config.PageConfig;
import org.lanqiao.net.crawler.model.PageBean;

import com.alibaba.fastjson.JSON;

public class LagouGatherAction {
  public static void main(String[] args) throws IOException {
    PageBean bean = PageConfig.of(null).enableJs(10000)
        .setReferer("https://www.lagou.com/")
        .setUrl("https://www.lagou.com/zhaopin/Java/?labelWords=label")
        .setPrefix("https://www.lagou.com")
        .setXlink("//div[@class='p_top']/a[@class='position_link']")
        .setPaging("//div[@class='pager_container']/a[text()='下一页']", 10)
        .toPageBean();
    File job_links = new File("/Users/zhengwei/Downloads/job_links.json");
    File jobs = new File("/Users/zhengwei/Downloads/jobs.json");
    for (String sublink : bean.getSubLinks()) {
      System.out.println(sublink);
      FileUtils.write(job_links, sublink+"\n", Charset.forName("utf-8"), true);
    }
    for (String sublink : bean.getSubLinks()) {
      System.out.println("--------" + sublink + "--------");
      try {
        TimeUnit.SECONDS.sleep((long) (Math.random() * 11)); // 随机等待一些时间
      } catch (Exception e1) {
        Thread.currentThread().interrupt();
      }
      PageBean pb = bean.reuseConfig().setReferer(bean.getUrl())
          .setUrl(sublink).setPaging(null, 0).setXlink(null)
          .addParam("company", "//div[@class='job-name']/*[@class='company']") // 公司
          .addParam("title", "//div[@class='job-name']/*[@class='name']") // 标题
          .addParam("request", "//*[@class='job_request']/p") // 职位要求
          .addParam("publish_time", "//*[@class='job_request']/p[2]") // 发布时间
          .addParam("job_bt", "//dd[@class='job_bt']") // 职位描述
          .addParam("work_addr", "//div[@class='work_addr']").toPageBean();
      String data = JSON.toJSONString(pb.getParams(), true);
      try {
        FileUtils.write(jobs, data+"\r\n", Charset.forName("utf-8"), true);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
