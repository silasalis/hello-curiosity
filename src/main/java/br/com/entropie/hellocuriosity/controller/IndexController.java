package br.com.entropie.hellocuriosity.controller;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.entropie.hellocuriosity.news.News;
import br.com.entropie.hellocuriosity.news.Timeline;
import br.com.entropie.hellocuriosity.news.filters.CategoryFilter;
import br.com.entropie.hellocuriosity.repository.FakeDB;
import br.com.entropie.hellocuriosity.rss.RssReader;
import br.com.entropie.hellocuriosity.utils.PlainJSONSerialization;

@Resource
public class IndexController {

	private final Result result;
	private final RssReader rssReader;
	private final FakeDB fakeDB;

	public IndexController(Result result, RssReader rssReader, FakeDB fakeDB) {
		this.result = result;
		this.rssReader = rssReader;
		this.fakeDB = fakeDB;
	}

	@Get("/")
	public void index() {
	}

	@Get("/timeline")
	public void timeline() {
		List<News> news = this.rssReader.defaultFeed().lastNews(new CategoryFilter());
		
		//TODO quem sabe arrumar isso
		try {
			List<News> fakeNews = fakeDB.getFakeNews();
			news.addAll(fakeNews);
		} catch (Exception e) {
			//fjunior - catch eh para fracos
		}
		Timeline timeline = new Timeline(news);
		
		this.result.use(PlainJSONSerialization.class).from(timeline).recursive().serialize();
	}
}
