package br.com.entropie.hellocuriosity.news.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.entropie.hellocuriosity.news.News;

public class CategoryFilter implements NewsFilter {

	//Press Releases    Feature Stories    Spotlights    Status Reports  
	private final static ArrayList<String> categories = new ArrayList<String>(Arrays.asList("status report","spotlights", "feature", "history"));
	
	public List<News> filterFrom(List<News> newsList) {
		List<News> filtredNews = new ArrayList<News>();
		
		for (News news : newsList){
			if (containsCategory(news))
				filtredNews.add(news);
		}
		
		return filtredNews;
	}
	private boolean containsCategory(News news) {
		
		for (String category : news.getCategories()){
			if (categories.contains(category.toLowerCase())){
				return true;
			}
		}
		return false;
	}

}
