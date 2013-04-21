package br.com.entropie.hellocuriosity;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import jxl.read.biff.BiffException;

import org.junit.Before;
import org.junit.Test;

import br.com.entropie.hellocuriosity.news.Asset;
import br.com.entropie.hellocuriosity.news.News;
import br.com.entropie.hellocuriosity.repository.FakeDB;
import br.com.entropie.hellocuriosity.utils.Dates;

public class FakeDBTest {

	private InputStream resourceAsStream;
	private List<String> historyCategories;
	@Before
	public void setUp() throws Exception {
		resourceAsStream = FakeDB.class.getResourceAsStream("/testHistory.xls");
		historyCategories = new ArrayList<String>();
		historyCategories.add("history");
	}

	@Test
	public void test() throws BiffException, IOException {
		
		String fakeNews1Date = "2009,3,12";
		
		Asset fakeNews1Asset = new Asset("fake1imgsrc");
		
		News fakeNews1 = new News("meu headline", "uma descricao bonita", null, fakeNews1Date, fakeNews1Asset , historyCategories);
		
		String fakeNews2Date = "2010,3,12";
		
		Asset fakeNews2Asset = new Asset("fake2imgsrc");
		
		News fakeNews2 = new News("meu outro headline", "uma descricao feia", null, fakeNews2Date, fakeNews2Asset , historyCategories);
		FakeDB fakeDB = new FakeDB();
		
		
		List<News> fakeNews = fakeDB.getFakeNews(resourceAsStream);
		
		fakeNews1.equals(fakeNews.get(0));
		
		assertThat(fakeNews, hasItem(fakeNews1));
		assertThat(fakeNews, hasItem(fakeNews2));
	}

}
