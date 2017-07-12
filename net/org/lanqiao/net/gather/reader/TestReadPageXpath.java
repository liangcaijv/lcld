package org.lanqiao.net.gather.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.lanqiao.net.gather.config.GrabageSite;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Ϊ���ɼ�������ש���ߣ��ṩ�ͻ��˵�����ƽ̨ 
 * @���� Gabriel 
 * @���� http://blog.csdn.net/zdsdiablo
 */
public class TestReadPageXpath {

	private final WebClient webClient;

	private GrabageSite xconfig;

	public TestReadPageXpath(GrabageSite xconfig) {
		this.xconfig = xconfig;
		webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);
	}

	public void tryToLoadXlink() throws IOException {
		long lasting = System.currentTimeMillis();
		HtmlPage page = (HtmlPage) webClient.getPage(xconfig.getLink());
		removeAdvert(page,xconfig);
		String timeline = getNodeValue(page,xconfig.getTimeline());
		System.out.println(timeline);
		System.out.println(UtilTools.getTimeLine(timeline));
		if(UtilTools.isNotEmpty(xconfig.getSplitpath())){
			String content="";
			do{
				String str=getNodeValues(page,xconfig.getContent());
				content+=str;
				String nextpage=getNodeHref(page,xconfig.getSplitpath());
				
				String link = xconfig.getForward()+nextpage;
				
				//System.out.println(">>>NEXTLINK>>>"+link+"\n"+str);
				if(UtilTools.isEmpty(nextpage)) break;
				page = (HtmlPage) webClient.getPage(link);
				removeAdvert(page,xconfig);
			}while(page!=null);
			System.out.println(content);
		}else{
			String str=getNodeValues(page,xconfig.getContent());
			System.out.println(str);
		}
		
		System.out.println("get page cost "+(System.currentTimeMillis()-lasting)/1000+"'s");
		
	}
	
	private void removeAdvert(HtmlPage page,GrabageSite xcon){
		if(xcon.hasAdvert()){
			HashSet<DomNode> adset = new HashSet<DomNode>();
			for(String xadvert : xcon.getAdvert()){
				adset.addAll(page.getByXPath(xadvert));
			}
			for(DomNode node : adset){
				DomNode pnode = node.getParentNode();
				pnode.removeChild(node);
			}
		}
		
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GrabageSite xconfig = new GrabageSite();
		if(true){
			//��ͨ����
			xconfig.setContent("id('text')");
			xconfig.setTitle("//h1");
			xconfig.setTimeline("//td[@class='info_text']");
			xconfig.setLink("http://portal.czol.info/news/money/2010-06-30/27.html");
		}else{
			//��ҳ����--��ȥ�����
			xconfig.setForward("http://www.javaeye.com");
			xconfig.setContent("//div[@class='postbody clearfix']");
			xconfig.setTitle("id('forum_main')/thead/tr/th/div[@class='wrapper']");
			xconfig.setSplitpath("//div[@class='pagination']/a[@class='next_page']");
			xconfig.setLink("http://www.javaeye.com/topic/656253");
			List<String> adList=new ArrayList<String>();
			adList.add("id('hunter_recommended')");
			adList.add("id('related_topics')");
			
			xconfig.setAdvert(adList);
		}
		TestReadPageXpath test = new TestReadPageXpath(xconfig);
		test.tryToLoadXlink();
	}
	
	private String getNodeHref(HtmlPage page, String xpath){
		if(UtilTools.isEmpty(xpath))
			return "";
		List<DomNode> nodes = page.getByXPath(xpath);
		if(nodes!=null && !nodes.isEmpty()) for(DomNode node : nodes){
			if(node.getAttributes().getLength()>0){
				return node.getAttributes().getNamedItem("href").getNodeValue();
			}
		}
		return "";
	}
	
	private String getNodeValue(HtmlPage page, String xpath){
		if(UtilTools.isEmpty(xpath))
			return "";
		List<DomNode> nodes =  page.getByXPath(xpath);
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
