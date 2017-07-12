package org.lanqiao.net.gather.config;

public enum PageConfigMark {
  LINK("link"),//链接
  FORWARD("forward"),
  SUMMARY("summary"),//简介
  TITLE("title"),//标题
  TIMELINE("timeline"),//发布时间
  AUTHOR("author"),//作者
  SOURCE("source"),//来源
  SPLIT("splitpage"),//分页标志
  CONTENT("CONTENT"), //正文
  
  XLINK("xlink"),
  XPATH("xpath"),
  USABLE("usable"),
  GRABAGES("grabages"),
  GRABAGE("grabage"),
  
  GLOBAL_CONFIG("global-config"),
  JAVASCRIPT("javascript"),
  CSS("css");

  private String value;
  
  private PageConfigMark(String v){
    this.value=v;
  }
  
  public String toString(){
    return value;
  }
  
  public boolean equals(String v){
    if(value.equalsIgnoreCase(v))
      return true;
    return false;
  }
}
