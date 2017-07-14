package org.lanqiao.net.crawler.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.lanqiao.net.crawler.config.PageConfig;

public class PageBean {
  private PageConfig config;
  private String              name;
  private String              url;                                     // 链接
  private Set<String>         subLinks = new HashSet<String>();        // 包含的子链接
  private Map<String, String> params   = new HashMap<String, String>(); // 关注的页面信息

  public PageBean(PageConfig config) {
    this.config = config;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Set<String> getSubLinks() {
    return subLinks;
  }

  public boolean hasSubLink() {
    return subLinks.size() > 0;
  }

  public Map<String, String> getParams() {
    return params;
  }
  public PageConfig reuseConfig() {
    return config;
  }
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.MULTI_LINE_STYLE);
  }

}