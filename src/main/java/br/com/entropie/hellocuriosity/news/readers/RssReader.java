package br.com.entropie.hellocuriosity.news.readers;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.entropie.hellocuriosity.HelloCuriosityException;
import br.com.entropie.hellocuriosity.news.News;
import br.com.entropie.hellocuriosity.news.filters.NewsFilter;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Component
public class RssReader implements Reader {

	private final static String root = "http://mars.jpl.nasa.gov/rss/news.xml";
	private SyndFeed feeder;
	
	public RssReader(){}

	private RssReader(SyndFeed feeder) {
		this.feeder = feeder;
	}
	
	public RssReader defaultFeed() {
		
		XmlReader reader = null;
		SyndFeed feed = null;
		try {
			reader = new XmlReader(new URL(root));
			feed = new SyndFeedInput().build(reader);
			return new RssReader(feed);
			
		} catch (Exception e) {
			closeQuietly(reader);
			throw new HelloCuriosityException("Error building your feed, sorry =(", e);
		}
	}
	
	public List<News> lastNews(NewsFilter newsSelector) {
		return  newsSelector.filterFrom(lastNews());
	}
	
	public List<News> lastNews() {
		List<News> news = new ArrayList<News>();
		for (Object entry : feeder.getEntries()) {
			news.add(News.buildWith((SyndEntry) entry));
		}
		return news;
	}
	
	private static void closeQuietly(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new RssReader().defaultFeed().lastNews());
	}
}
