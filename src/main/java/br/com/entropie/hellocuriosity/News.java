package br.com.entropie.hellocuriosity;

import java.util.Date;

public class News {

	private final String title;
	private final String content;
	private final String image;
	private final Date published;

	public News(String title, String content, String image, Date published) {
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
}
