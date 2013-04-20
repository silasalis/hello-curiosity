package br.com.entropie.hellocuriosity.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.entropie.hellocuriosity.News;
import br.com.entropie.hellocuriosity.Timeline;
import br.com.entropie.hellocuriosity.rss.RssReader;

@Resource
public class IndexController {

	private final Result result;
	private final RssReader rssReader;

	public IndexController(Result result, RssReader rssReader) {
		this.result = result;
		this.rssReader = rssReader;
	}

	@Get("/")
	public void index() {
		List<News> news = this.rssReader.lastNews();
		Timeline timeline = new Timeline(news);
		result.use(Results.json()).from(timeline).recursive().serialize();
	}
}
