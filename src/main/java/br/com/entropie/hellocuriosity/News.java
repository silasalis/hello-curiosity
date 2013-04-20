package br.com.entropie.hellocuriosity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.entropie.hellocuriosity.utils.Dates;

import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndEntry;

public class News {

	private final String headline;
	private final String text;
	private final String image;
	private final String startDate;
	private List<String> categories;
	

	public News(String headline, String text, String image, String startDate, List<String> categories) {
		this.headline = headline;
		this.text = text;
		this.image = image;
		this.startDate = startDate;
		this.categories = categories;
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

	public List<String> getCategories() {
		return categories;
	}

    
	@Override
	public String toString() {
		return "News [headline=" + headline + ", text=" + text + ", image="
				+ image + ", startDate=" + startDate + ", categories="
				+ categories + "]";
	}

	public static News buildWith(SyndEntry entry) {
		return new News(entry.getTitle(), extractEntryDescription(entry), "",
				formatDate(entry.getPublishedDate()), extractCategories(entry));

	}

	private static List<String> extractCategories(SyndEntry entry) {
		List<String> categories = new ArrayList<String>();
		for (Object category : entry.getCategories()) {
			String categoryName = ((SyndCategoryImpl) category).getName();
			categories.add(categoryName);
		}
		return categories;
	}

	private static String extractEntryDescription(SyndEntry entry) {
		return entry.getDescription().getValue().replaceAll("<!--.*?-->", "")
				.replaceAll("<[^>]+>", "").trim();
	}

	private static String formatDate(Date entry) {
		return Dates.format(entry);
	}
}
