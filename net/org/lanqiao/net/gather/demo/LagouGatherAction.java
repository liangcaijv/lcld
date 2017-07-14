package org.lanqiao.net.gather.demo;

import org.lanqiao.net.gather.config.PageConfig;
import org.lanqiao.net.gather.model.PageBean;

public class LagouGatherAction {
  public static void main(String[] args) {
    PageBean bean = PageConfig.of(null)
        .setUrl("https://www.lagou.com/zhaopin/Java/?labelWords=label")
        .setPrefix("https://www.lagou.com")
        .setXlink("//div[@class='p_top']/a[@class='position_link']")
        .addParam("content", "//div[@class='p_top']//h2").toPageBean();
    bean = PageConfig.of(null)
        .setAjax(true)
        .setUrl("https://www.lagou.com/jobs/positionAjax.json?gj=%E5%BA%94%E5%B1%8A%E6%AF%95%E4%B8%9A%E7%94%9F&px=default&needAddtionalResult=false")
        .toPageBean();
//    https://www.lagou.com/jobs/positionAjax.json?gj=%E5%BA%94%E5%B1%8A%E6%AF%95%E4%B8%9A%E7%94%9F&px=default&needAddtionalResult=false
    System.out.println(bean.getParams().get("content"));
    System.out.println(bean.getSubLinks());
  }
}
