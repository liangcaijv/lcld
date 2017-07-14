package org.lanqiao.net.gather.demo;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.lanqiao.net.gather.config.PageConfig;
import org.lanqiao.net.gather.model.PageBean;

public class GatherAction {
  public static void main(String[] args) throws InterruptedException {
    PageBean pageBean = PageConfig.of("ftchinese-list.xml").toPageBean();
    Set<String> xUrlSet = pageBean.getSubLinks();
    for (String xUrl : xUrlSet) {
      TimeUnit.SECONDS.sleep(5 + (int) Math.random() * 6);
      PageBean bean = PageConfig.of("ftchinese-content.xml").setUrl(xUrl)
          .toPageBean();
      System.out.println(bean);
    }
  }
}
