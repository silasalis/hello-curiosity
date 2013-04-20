package br.com.entropie.hellocuriosity.rss;

import java.util.List;

import br.com.entropie.hellocuriosity.News;

public interface NewsFilter {

	List<News> filterFrom(List<News> lastNews);
	
}
