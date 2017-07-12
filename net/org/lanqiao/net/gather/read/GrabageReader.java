package org.lanqiao.net.gather.read;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lanqiao.net.gather.bean.GrabageSite;
import org.lanqiao.net.gather.bean.PageInfo;
import org.lanqiao.net.gather.bean.PageInfoMark;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 为广大采集工具添砖加瓦，提供客户端的运行平台 
 * @作者 Gabriel 
 * @博客 http://blog.csdn.net/zdsdiablo
 */
public class GrabageReader {
  
  private static final int DEBUG_COUNT=5;
  
  private static final String ENABLED="enabled";
  private static final String HREF="href";

  private static final String CONFIG_FILE_PATH = "grabage-config.xml";
  
  private final WebClient webClient;
  
  private List<GrabageSite> grabageSites;
  
  public GrabageReader() {
    webClient= new WebClient(BrowserVersion.INTERNET_EXPLORER);
    grabageSites= new ArrayList<GrabageSite>();
  }

  //重新加载订阅信息
  public void reLoadSubConfig() {
    long lasting = System.currentTimeMillis();
    System.out.println("[config]start:"+lasting);
    try {
      InputStream is = getClass().getResourceAsStream(CONFIG_FILE_PATH);
      SAXReader reader = new SAXReader();
      Document doc = reader.read(is);
      Element root = doc.getRootElement();
      //解析htmlunit配置
      Element configItem = root.element(PageInfoMark.CONFIG.toString());
      //String version = configItem.element("browser").attributeValue("version").trim();
      
      //解析采集站点的配置
      List<Element> gaItems = root.element(PageInfoMark.GRABAGE_ARRAY.toString()).elements(PageInfoMark.GRABAGE.toString());
      grabageSites.clear();
      
      for(Element gaItem : gaItems){
        GrabageSite gs = new GrabageSite();
        gs.setUsable(gaItem.attributeValue(PageInfoMark.USABLE.toString()).trim());
        gs.setForward(gaItem.attributeValue(PageInfoMark.FORWARD.toString()).trim());
        gs.setLink(gaItem.attributeValue(PageInfoMark.LINK.toString()).trim());
        //
        Element title = gaItem.element(PageInfoMark.TITLE.toString());
        if(title!=null) gs.setTitle(title.attributeValue(PageInfoMark.XPATH.toString()));
        
        Element author = gaItem.element(PageInfoMark.AUTHOR.toString());
        if(author!=null) gs.setAuthor(author.attributeValue(PageInfoMark.XPATH.toString()));
        
        Element source = gaItem.element(PageInfoMark.SOURCE.toString());
        if(source!=null) gs.setSource(source.attributeValue(PageInfoMark.XPATH.toString()));
        
        Element timeline = gaItem.element(PageInfoMark.TIMELINE.toString());
        if(timeline!=null) gs.setTimeline(timeline.attributeValue(PageInfoMark.XPATH.toString()));
        
        Element summary = gaItem.element(PageInfoMark.SUMMARY.toString());
        if(summary!=null) gs.setSummary(summary.attributeValue(PageInfoMark.XPATH.toString()));
        
        Element content = gaItem.element(PageInfoMark.CONTENT.toString());
        if(content!=null) gs.setContent(content.attributeValue(PageInfoMark.XPATH.toString()));
        
        Element splitpath = gaItem.element(PageInfoMark.SPLIT.toString());
        if(splitpath!=null) gs.setSplitpath(splitpath.attributeValue(PageInfoMark.XPATH.toString()));
        
        //暂时只做一层，理论上可多层深入
        Element xlink = gaItem.element(PageInfoMark.XLINK.toString());
        if(xlink!=null) gs.setXlink(xlink.attributeValue(PageInfoMark.XPATH.toString()));
        
        grabageSites.add(gs);
      }
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    lasting = System.currentTimeMillis();
    System.out.println("[config]complete:"+lasting);
  }
  
  public void startReadPage(){
    long lasting = System.currentTimeMillis();
    System.out.println("[grabage]start:"+lasting);
    try {
      List<PageInfo> pbList = reloadPageConfig();
      
      for(PageInfo pb : pbList){
        System.out.println("link:"+pb.getLink()+"\ttitle:"+pb.getTitle());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    lasting = System.currentTimeMillis();
    System.out.println("[grabage]complete:"+lasting);
  }
  
  //重新读取页面抓取配置
  private List<PageInfo> reloadPageConfig() throws Exception{
    List<PageInfo> pbList = new ArrayList<PageInfo>();
    
    HtmlPage page = null;
    for(GrabageSite gsite : grabageSites){
      if(UtilTools.isNotEmpty(gsite.getUsable()) && gsite.getUsable().equals("false")) continue;
      System.out.println(">>>LINK>>>"+gsite.getLink());
      
      page = (HtmlPage)webClient.getPage(gsite.getLink());
      //解析规则
      HashSet<String> linkSet = new HashSet<String>();
      
      List<DomNode> linkNodes = page.getByXPath(gsite.getXlink());     
      if(linkNodes!=null && !linkNodes.isEmpty()) for(DomNode node : linkNodes){
        String href = node.getAttributes().getNamedItem(HREF).getNodeValue();
        
        if (href.startsWith(gsite.getForward())) {
          linkSet.add(href);
        }
        //取消外部链接
        else if(href.startsWith("http:") || href.startsWith("https:")){
          continue;
        }
        //保留相对链接
        else{
          linkSet.add(gsite.getForward()+href);
        }
      }
      int i=0;
      for(String link : linkSet){
        i++;
        if(i>DEBUG_COUNT) break;
        PageInfo pb = new PageInfo();
        pb.setLink(link);
        try{
          page = (HtmlPage)webClient.getPage(pb.getLink());
          //移除广告
          removeAdvert(page,gsite);
          //标题
          pb.setTitle(getNodeValue(page, gsite.getTitle()));
          //作者
          pb.setAuthor(getNodeValue(page, gsite.getAuthor()));
          //来源
          pb.setSource(getNodeValue(page, gsite.getSource()));
          //发布时间
          pb.setTimeline(UtilTools.getTimeLine(
              getNodeValue(page, gsite.getTimeline())));
          //简介
          pb.setSummary(getNodeValue(page, gsite.getSummary()));
          //内容
          pb.setContent(getNodeValues(page, gsite.getContent()));
          
          //分页
          searchNextPage(page,pb,gsite);
          
          pbList.add(pb);
        }catch(Exception ex){
          ex.printStackTrace();
        }
      }
      
    }
    return pbList;
  }
  
  /**
   * 移除广告位
   * @param page
   * @param gsite
   */
  private void removeAdvert(HtmlPage page,GrabageSite gsite){
    if(gsite.hasAdvert()){
      HashSet<DomNode> adset = new HashSet<DomNode>();
      for(String xadvert : gsite.getAdvert()){
        adset.addAll( page.getByXPath(xadvert));
      }
      for(DomNode node : adset){
        try{
          DomNode pnode = node.getParentNode();
          pnode.removeChild(node);
        }catch(Exception ex){
          //可能有嵌套广告的情况，捕获此异常
          ex.printStackTrace();
        }
      }
    }
    
  }
  
  /**
   * 寻找次页
   * @param page
   * @param pb
   * @param gsite
   */
  private void searchNextPage(HtmlPage page,PageInfo pb, GrabageSite gsite){
    if(UtilTools.isNotEmpty(gsite.getSplitpath())){
      String href=getNodeHref(page,gsite.getSplitpath());     
      if(UtilTools.isEmpty(href)) return;
      String nextlink="";
      if (href.startsWith(gsite.getForward())) {
        nextlink=href;
      }else{
        nextlink=gsite.getForward()+href;
      }
      try{
        page = (HtmlPage) webClient.getPage(nextlink);
        pb.setContent(pb.getContent()+getNodeValues(page, gsite.getContent()));
        
        searchNextPage(page,pb,gsite);
      }catch(Exception ex){
        return;
      }
    }
  }
  
  private String getNodeHref(HtmlPage page, String xpath){
    if(UtilTools.isEmpty(xpath))
      return "";
    List<DomNode> nodes =page.getByXPath(xpath);
    if(nodes!=null && !nodes.isEmpty()) for(DomNode node : nodes){
      if(node.getAttributes().getLength()>0){
        return node.getAttributes().getNamedItem(HREF).getNodeValue();
      }
    }
    return "";
  }
  
  private String getNodeValue(HtmlPage page, String xpath){
    if(UtilTools.isEmpty(xpath))
      return "";
    List<DomNode> nodes = page.getByXPath(xpath);
    if(nodes!=null && !nodes.isEmpty()) for(DomNode node : nodes){
      if(UtilTools.isNotEmpty(node.asText())){
        return node.asText().trim();
      }
    }
    return "";
  }
  
  private String getNodeValues(HtmlPage page, String xpath){
    if(UtilTools.isEmpty(xpath))
      return "";
    String str="";
    List<DomNode> nodes = page.getByXPath(xpath);
    if(nodes!=null && !nodes.isEmpty()) for(DomNode node : nodes){
      if(UtilTools.isNotEmpty(node.asText())){
        str+=node.asText().trim();
      }
    }
    return str;
  }
  
}