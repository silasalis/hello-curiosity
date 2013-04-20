package br.com.entropie.hellocuriosity;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import br.com.entropie.hellocuriosity.utils.Dates;

import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndEntry;

public class News {

	private final String headline;
	private final String text;
	private final String startDate;
	private final List<String> categories;
	private final Asset asset;

	public News(String headline, String text, String startDate, Asset asset, List<String> categories) {
		this.headline = headline;
		this.text = text;
		this.startDate = startDate;
		this.categories = categories;
		this.asset = asset;
	}

	public String getHeadline() {
		return headline;
	}

	public String getText() {
		return text;
	}

	public String getStartDate() {
		return startDate;
	}

	public List<String> getCategories() {
		return categories;
	}

    
	public String toString() {
		return "News [headline=" + headline + ", text=" + text + ", startDate="
+ startDate + "]";
	}

	public static News buildWith(SyndEntry entry) {
		return new News(entry.getTitle(), extractEntryDescription(entry),
				Dates.format(entry.getPublishedDate()), extractEntryImage(entry), extractCategories(entry));

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
		String html = entry.getDescription().getValue();
		return html.replaceAll("<!--.*?-->", "").replaceAll("<[^>]+>", "").trim();
	}

	private static Asset extractEntryImage(SyndEntry entry) {
		String html = entry.getDescription().getValue();
		String value = Jsoup.parse(html, "UTF-8").select("img").attr("src");
		return new Asset(value);
	}

}
