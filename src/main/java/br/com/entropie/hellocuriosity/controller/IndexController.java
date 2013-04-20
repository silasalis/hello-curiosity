package br.com.entropie.hellocuriosity.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
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
		result.use(Results.json()).from(rssReader.lastNews()).serialize();
	}
}
