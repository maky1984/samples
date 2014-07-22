package com.samples.newsfilter;

public class NewsDescriptor {

	private String title;
	private String link;
	private String pubDate;

	public void setLink(String link) {
		this.link = link;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public String getTitle() {
		return title;
	}

}
