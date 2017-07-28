package org.lanqiao.net.crawler;

import org.lanqiao.net.crawler.config.PageConfig;
import org.lanqiao.net.crawler.model.PageBean;

import com.alibaba.fastjson.JSON;

public class TestLagou {
  public static void main(String[] args) {
    PageBean pb = PageConfig.of(null)
        .setReferer("https://www.lagou.com/zhaopin/Java/?labelWords=label")
        .enableJs(10000)
        .setUrl("https://www.lagou.com/jobs/2852069.html")
        .addParam("company", "//div[@class='job-name']/*[@class='company']") // 公司
        .addParam("title", "//div[@class='job-name']/*[@class='name']") // 标题
        .addParam("request", "//*[@class='job_request']/p") // 职位要求
        .addParam("publish_time", "//*[@class='job_request']/p[2]") // 发布时间
        .addParam("job_bt", "//dd[@class='job_bt']") // 职位描述
        .addParam("work_addr", "//div[@class='work_addr']").toPageBean();
    System.out.println(JSON.toJSONString(pb.getParams()));
  }
}
