package br.com.entropie.hellocuriosity.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.entropie.hellocuriosity.news.News;
import br.com.entropie.hellocuriosity.news.Timeline;
import br.com.entropie.hellocuriosity.news.filters.CategoryFilter;
import br.com.entropie.hellocuriosity.news.readers.FakeNews;
import br.com.entropie.hellocuriosity.news.readers.RssReader;
import br.com.entropie.hellocuriosity.news.readers.TwitterReader;
import br.com.entropie.hellocuriosity.utils.PlainJSONSerialization;

@Resource
public class IndexController {

	private final Result result;
	private final RssReader rssReader;
	private final FakeNews fakeDB;
	private final TwitterReader twitterNews;

	public IndexController(Result result, RssReader rssReader, FakeNews fakeDB, TwitterReader twitterNews) {
		this.result = result;
		this.rssReader = rssReader;
		this.fakeDB = fakeDB;
		this.twitterNews = twitterNews;
	}

	@Get("/")
	public void index() {
	}

	@Get("/timeline")
	public void timeline() {
		List<News> news = this.rssReader.defaultFeed().lastNews();

		List<News> fakeNews = fakeDB.lastNews();
		news.addAll(fakeNews);
		
		//TODO fjunior: tirando tweets da timeline por enquanto 
		//List<News> tNews = twitterNews.lastNews();
		//news.addAll(tNews);
		
		Timeline timeline = new Timeline(news);

		this.result.use(PlainJSONSerialization.class).from(timeline)
				.recursive().serialize();
	}
}
