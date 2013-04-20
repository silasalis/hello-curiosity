package br.com.entropie.hellocuriosity.news;

import java.util.Date;

import com.sun.syndication.feed.synd.SyndEntry;

public class News {

	private final String title;
	private final String content;
	private final String image;
	private final Date published;

	private News(String title, String content, String image, Date published) {
		this.title = title;
		this.content = content;
		this.image = image;
		this.published = published;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getImage() {
		return image;
	}

	public Date getPublished() {
		return published;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", content=" + content + ", image="
				+ image + ", published=" + published + "]";
	}

	public static News buildWith(SyndEntry entry) {
		return new News(entry.getTitle(), extractEntryDescription(entry), "",
				entry.getPublishedDate());

	}

	private static String extractEntryDescription(SyndEntry entry) {
		return entry.getDescription().getValue().replaceAll("<!--.*?-->", "")
				.replaceAll("<[^>]+>", "").trim();
	}

}
