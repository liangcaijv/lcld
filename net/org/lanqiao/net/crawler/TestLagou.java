package org.lanqiao.net.crawler;

import com.alibaba.fastjson.JSON;

public class TestLagou {
  public static void main(String[] args) {
    PageBean pb = PageBuilder.of(null)
        .setReferer("https://www.lagou.com/zhaopin/Java/?labelWords=label")
        .enableJs(10000)
        .setUrl("https://www.lagou.com/jobs/2852069.html")
        .addXpath("company", "//div[@class='job-name']/*[@class='company']") // 公司
        .addXpath("title", "//div[@class='job-name']/*[@class='name']") // 标题
        .addXpath("request", "//*[@class='job_request']/p") // 职位要求
        .addXpath("publish_time", "//*[@class='job_request']/p[2]") // 发布时间
        .addXpath("job_bt", "//dd[@class='job_bt']") // 职位描述
        .addXpath("work_addr", "//div[@class='work_addr']") // 地址
        .build();
    System.out.println(JSON.toJSONString(pb.getParams()));
  }
}
