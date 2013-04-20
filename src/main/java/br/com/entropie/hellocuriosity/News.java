package br.com.entropie.hellocuriosity;

import java.util.Date;

import br.com.entropie.hellocuriosity.utils.Dates;

import com.sun.syndication.feed.synd.SyndEntry;

public class News {

	private final String headline;
	private final String text;
	private final String image;
	private final String startDate;

	public News(String headline, String text, String image, String startDate) {
		this.headline = headline;
		this.text = text;
		this.image = image;
		this.startDate = startDate;
	}

	public String getHeadline() {
		return headline;
	}

	public String getText() {
		return text;
	}

	public String getImage() {
		return image;
	}

	public String getStartDate() {
		return startDate;
	}

	@Override
	public String toString() {
		return "News [headline=" + headline + ", text=" + text + ", image="
				+ image + ", startDate=" + startDate + "]";
	}

	public static News buildWith(SyndEntry entry) {
		return new News(entry.getTitle(), extractEntryDescription(entry), "",
				formatDate(entry.getPublishedDate()));

	}

	private static String extractEntryDescription(SyndEntry entry) {
		return entry.getDescription().getValue().replaceAll("<!--.*?-->", "")
				.replaceAll("<[^>]+>", "").trim();
	}

	private static String formatDate(Date entry) {
		return Dates.format(entry);
	}
}
