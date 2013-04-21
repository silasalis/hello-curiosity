package br.com.entropie.hellocuriosity.news.readers;

import java.util.List;

import br.com.entropie.hellocuriosity.news.News;

public interface Reader {

	public List<News> lastNews();
}
