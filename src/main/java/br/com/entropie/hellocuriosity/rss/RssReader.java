package br.com.entropie.hellocuriosity.rss;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.entropie.hellocuriosity.HelloCuriosityException;
import br.com.entropie.hellocuriosity.News;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Component
public class RssReader {

	private final String root = "http://mars.jpl.nasa.gov/rss/news.xml";

	public List<News> lastNews() {

		XmlReader reader = null;

		try {
			URL url = new URL(root);
			reader = new XmlReader(url);
			SyndFeed feed = new SyndFeedInput().build(reader);

			List<News> news = new ArrayList<News>();
			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				SyndEntry entry = (SyndEntry) i.next();

				News latestNews = new News(entry.getTitle(), 
						entry.getDescription().getValue().replaceAll("<!--.*?-->", "").replaceAll("<[^>]+>", "").trim(), 
						"",
						entry.getPublishedDate());

				news.add(latestNews);
			}

			return news;
		} catch (Exception e) {
			throw new HelloCuriosityException(e.getMessage(), e);

		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new RssReader().lastNews());
	}
}
