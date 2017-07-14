package org.lanqiao.net.gather.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PageBean {
  private String              name;
  private String              url;                                     // 链接
  private Set<String>         subLinks = new HashSet<String>();        // 包含的子链接
  private Map<String, String> params   = new HashMap<String, String>(); // 关注的页面信息
  private String allContent;

  public PageBean() {
    super();
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

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.MULTI_LINE_STYLE);
  }

  public void setAllContent(String text) {
    this.allContent = text;
  }
}