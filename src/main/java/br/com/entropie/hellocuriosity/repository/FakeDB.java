package br.com.entropie.hellocuriosity.repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.entropie.hellocuriosity.news.Asset;
import br.com.entropie.hellocuriosity.news.News;
import br.com.entropie.hellocuriosity.utils.Dates;

public class FakeDB {

	private InputStream input;
	private int HEADLINE_COLUMN = 0;
	private int TEXT_COLUMN = 0;
	private int DATE_COLUMN = 0;
	private int ASSET_COLUMN = 0;
	private int CATEGORY_COLUMN = 0;
	
	public FakeDB(InputStream input) {
		this.input = input;
	}

	public List<News> getFakeNews() {

		
		Scanner sc = new Scanner(input);
		jumpHeader(sc);
	
		List<News> fakeNewsList = new ArrayList<News>();
		
		while (sc.hasNext()){
			String row = sc.nextLine();
			String[] newsInfo = row.split(",");
			
			String headline = newsInfo[HEADLINE_COLUMN];
			String text = newsInfo[TEXT_COLUMN];
			String date = Dates.formateFakeDate(newsInfo[DATE_COLUMN]);
			Asset asset = new Asset(newsInfo[ASSET_COLUMN]);
			String category = newsInfo[CATEGORY_COLUMN];
			List<String> categories = new ArrayList<String>();
			categories.add(category);
			
			News news = new News(headline, text, date, asset, categories);
			
			fakeNewsList.add(news);
			
		}
		return fakeNewsList;
		
	}

	private void jumpHeader(Scanner sc) {
		sc.nextLine();
	}
}
