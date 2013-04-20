package br.com.entropie.hellocuriosity.rss;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

	private final static String root = "http://mars.jpl.nasa.gov/rss/news.xml";
	private SyndFeed feeder;
	
	public RssReader(){}

	private RssReader(SyndFeed feeder) {
		this.feeder = feeder;
	}
	
	public static RssReader feedFor(String url) {
		
		XmlReader reader = null;
		SyndFeed feed = null;
		try {
			reader = new XmlReader(new URL(url));
			feed = new SyndFeedInput().build(reader);
			
		} catch (Exception e) {
			closeQuietly(reader);
			throw new HelloCuriosityException("Error building your feed, sorry =(", e);
		}
		
		return new RssReader(feed);
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
		System.out.println(new RssReader().feedFor(root).lastNews());
	}
	
}
