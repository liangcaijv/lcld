package org.lanqiao.net.gather.bean;

/**
 * Ϊ���ɼ�������ש���ߣ��ṩ�ͻ��˵�����ƽ̨ 
 * @���� Gabriel 
 * @���� http://blog.csdn.net/zdsdiablo
 */
public class PageInfo {
	
	private String title;
	private String link;
	private String timeline;
	private String content;
	private String author;
	private String source;
	private String summary;
	
	public PageInfo(){
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
