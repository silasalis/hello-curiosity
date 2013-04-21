package br.com.entropie.hellocuriosity.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import br.com.entropie.hellocuriosity.news.Asset;
import br.com.entropie.hellocuriosity.news.News;
import br.com.entropie.hellocuriosity.utils.Dates;

public class FakeDB {

	private int HEADLINE_COLUMN = 0;
	private int TEXT_COLUMN = 1;
	private int DATE_COLUMN = 2;
	private int ASSET_COLUMN = 3;
	private int CATEGORY_COLUMN = 4;
	

	public List<News> getFakeNews(InputStream input) throws BiffException, IOException {

		
		List<News> fakeNewsList = new ArrayList<News>();
		//jumping hearder
		Workbook workbook = Workbook.getWorkbook(input);
		Sheet sheet = workbook.getSheet(0);
		
		for (int r = 1; r < sheet.getRows(); r++) {
			
			Cell[] newsInfo = sheet.getRow(r);
		
			String headline = newsInfo[HEADLINE_COLUMN].getContents().trim();
			String text = newsInfo[TEXT_COLUMN].getContents().trim();
			String date = newsInfo[DATE_COLUMN].getContents().trim();
			Asset asset = new Asset(newsInfo[ASSET_COLUMN].getContents().trim());
			String category = newsInfo[CATEGORY_COLUMN].getContents().trim();
			List<String> categories = new ArrayList<String>();
			categories.add(category);
			
			News news = new News(headline, null, text, date, asset, categories);
			
			fakeNewsList.add(news);
			
		}
		return fakeNewsList;
		
	}

}
