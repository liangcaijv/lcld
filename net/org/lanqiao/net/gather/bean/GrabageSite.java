package org.lanqiao.net.gather.bean;

import java.util.List;

/**
 * Ϊ���ɼ�������ש���ߣ��ṩ�ͻ��˵�����ƽ̨ 
 * @���� Gabriel 
 * @���� http://blog.csdn.net/zdsdiablo
 */
public class GrabageSite {
	
	private String link;//ԭ����
	private String forward;//����ǰ׺
	private String usable;//�Ƿ����
	
	private String xlink;
	private List<String> advert; //�Ƴ����
	
	private String title;//����
	private String author;//����
	private String source;//��Դ
	private String timeline;//����ʱ��
	private String content;//����
	private String splitpath;//��ҳ
	private String summary;//���
	
	public GrabageSite(){
	}
	
	public String getXlink() {
		return xlink;
	}
	public void setXlink(String xlink) {
		this.xlink = xlink;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTimeline() {
		return timeline;
	}
	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSplitpath() {
		return splitpath;
	}
	public void setSplitpath(String splitpath) {
		this.splitpath = splitpath;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
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

	public List<String> getAdvert() {
		return advert;
	}

	public void setAdvert(List<String> advert) {
		this.advert = advert;
	}
	
	public boolean hasAdvert(){
		if(advert==null || advert.isEmpty())
			return false;
		return true;
	}
	
}
