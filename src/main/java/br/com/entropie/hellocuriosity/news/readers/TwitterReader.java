package br.com.entropie.hellocuriosity.news.readers;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import br.com.caelum.vraptor.ioc.Component;
import br.com.entropie.hellocuriosity.HelloCuriosityException;
import br.com.entropie.hellocuriosity.news.News;

@Component
public class TwitterReader implements Reader {

	public List<News> lastNews() {
		try {
			List<News> newses = new ArrayList<News>();
			Twitter twitter = TwitterFactory.getSingleton();
			List<Status> statuses = twitter.getUserTimeline("MarsCuriosity");

			for (Status status : statuses) {
				News news = News.buildWith(status);
				newses.add(news);
			}
			return newses;
		} catch (TwitterException e) {
			e.printStackTrace();
			throw new HelloCuriosityException("Error building your feed, sorry =(", e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new TwitterReader().lastNews());
	}
}
