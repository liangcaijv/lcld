package org.lanqiao.net.gather.read;

import java.io.IOException;
import java.util.List;

import org.lanqiao.net.gather.bean.GrabageSite;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 为广大采集工具添砖加瓦，提供客户端的运行平台 
 * @作者 Gabriel 
 * @博客 http://blog.csdn.net/zdsdiablo
 */
public class TestReadLinkXpath {

	private final WebClient webClient;

	private GrabageSite xconfig;

	public TestReadLinkXpath(GrabageSite xconfig) {
		this.xconfig = xconfig;
		webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);
	}

	public void tryToLoadXlink() throws IOException {
		long lasting = System.currentTimeMillis();
		String link = xconfig.getLink();
		HtmlPage page = (HtmlPage) webClient.getPage(link);
		List<DomNode> nodes = page.getByXPath(xconfig.getXlink());
		for (DomNode node : nodes) {
			String href = node.getAttributes().getNamedItem("href")
					.getNodeValue();
			if (href.startsWith(xconfig.getForward())) {
				System.out.println(node.asText() + "\t" + href);
			}else{
				System.out.println(node.asText() + "\t"+xconfig.getForward()+href);
			}
		}
		System.out.println("get links cost "+(System.currentTimeMillis()-lasting)/1000+"'s");
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		GrabageSite xconfig = new GrabageSite();
		xconfig.setXlink("//table[@class='box']//a[@href]");
		xconfig.setLink("http://portal.czol.info/news/money");
		xconfig.setForward("http://portal.czol.info");
		TestReadLinkXpath test = new TestReadLinkXpath(xconfig);
		test.tryToLoadXlink();
	}

}
