package org.lanqiao.net.gather.config;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 站锟斤拷锟斤拷锟斤拷锟斤拷息
 * @author zhengwei lastmodified 2017锟斤拷7锟斤拷12锟斤拷
 *
 */
public class SiteConfig {

  private String       link;                   //原锟斤拷锟斤拷
  private String       forward;                //锟斤拷锟斤拷前缀
  private String       usable;                 //锟角凤拷锟斤拷锟�

  private String       xpathOfLinkInNextLevel; // 锟斤拷一锟斤拷锟斤拷锟斤拷锟侥筹拷锟斤拷锟接碉拷xpath
  private List<String> xpathsOfAdvert;         //锟狡筹拷锟斤拷锟�

  private String       xpathOfTitle;           //锟斤拷锟斤拷
  private String       xpathOfAuthor;          //锟斤拷锟斤拷
  private String       xpathOfSource;          //锟斤拷源
  private String       xpathOfTimeline;        //锟斤拷锟斤拷时锟斤拷
  private String       xpathOfContent;         //锟斤拷锟斤拷
  private String       xpathOfSplitpath;       //锟斤拷页
  private String       xpathOfSummary;         //锟斤拷锟�

  private SiteConfig(){
    
  }
  private static SiteConfig instance;
  public staic SiteConfig of(String configPath){
    if (null==instance) {
      instance = new SiteConfig();
    }
    InputStream inputStream = getClass().getResourceAsStream(configPath);
    SAXReader saxReader = new SAXReader();
    Document doc = saxReader.read(inputStream);
    Element root = doc.getRootElement();
    //解析htmlunit配置
    Element globalConfig = root.element("global-config");
  }
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getForward() {
    return forward;
  }

  public void setForward(String forward) {
    this.forward = forward;
  }

  public String getUsable() {
    return usable;
  }

  public void setUsable(String usable) {
    this.usable = usable;
  }

  public String getXpathOfLinkInNextLevel() {
    return xpathOfLinkInNextLevel;
  }

  public void setXpathOfLinkInNextLevel(String xpathOfLinkInNextLevel) {
    this.xpathOfLinkInNextLevel = xpathOfLinkInNextLevel;
  }

  public List<String> getXpathsOfAdvert() {
    return xpathsOfAdvert;
  }

  public void setXpathsOfAdvert(List<String> xpathsOfAdvert) {
    this.xpathsOfAdvert = xpathsOfAdvert;
  }

  public String getXpathOfTitle() {
    return xpathOfTitle;
  }

  public void setXpathOfTitle(String xpathOfTitle) {
    this.xpathOfTitle = xpathOfTitle;
  }

  public String getXpathOfAuthor() {
    return xpathOfAuthor;
  }

  public void setXpathOfAuthor(String xpathOfAuthor) {
    this.xpathOfAuthor = xpathOfAuthor;
  }

  public String getXpathOfSource() {
    return xpathOfSource;
  }

  public void setXpathOfSource(String xpathOfSource) {
    this.xpathOfSource = xpathOfSource;
  }

  public String getXpathOfTimeline() {
    return xpathOfTimeline;
  }

  public void setXpathOfTimeline(String xpathOfTimeline) {
    this.xpathOfTimeline = xpathOfTimeline;
  }

  public String getXpathOfContent() {
    return xpathOfContent;
  }

  public void setXpathOfContent(String xpathOfContent) {
    this.xpathOfContent = xpathOfContent;
  }

  public String getXpathOfSplitpath() {
    return xpathOfSplitpath;
  }

  public void setXpathOfSplitpath(String xpathOfSplitpath) {
    this.xpathOfSplitpath = xpathOfSplitpath;
  }

  public String getXpathOfSummary() {
    return xpathOfSummary;
  }

  public void setXpathOfSummary(String xpathOfSummary) {
    this.xpathOfSummary = xpathOfSummary;
  }

  public boolean hasAdvert() {
    if (xpathsOfAdvert == null || xpathsOfAdvert.isEmpty())
      return false;
    return true;
  }

}
