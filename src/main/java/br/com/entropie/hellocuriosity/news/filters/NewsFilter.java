package br.com.entropie.hellocuriosity.news.filters;

import java.util.List;

import br.com.entropie.hellocuriosity.news.News;

public interface NewsFilter {

	List<News> filterFrom(List<News> lastNews);
	
}
