package br.com.entropie.hellocuriosity;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import br.com.entropie.hellocuriosity.news.Asset;
import br.com.entropie.hellocuriosity.news.News;
import br.com.entropie.hellocuriosity.repository.FakeDB;

public class FakeDBTest {

	private InputStream resourceAsStream;
	private List<String> historyCategories;
	@Before
	public void setUp() throws Exception {
		resourceAsStream = FakeDB.class.getResourceAsStream("/testHistory.csv");
		historyCategories = new ArrayList<String>();
		historyCategories.add("History");
	}

	@Test
	public void test() {
		FakeDB fakeDB = new FakeDB(resourceAsStream);
		
		Calendar fakeNews1Date = Calendar.getInstance();
		fakeNews1Date.set(2009, 3, 12);
		
		Asset fakeNews1Asset = new Asset("fake1imgsrc");
		
		News fakeNews1 = new News("meu headline", "uma descricao bonita", fakeNews1Date.getTime().toString(), fakeNews1Asset , historyCategories);
		
		Calendar fakeNews2Date = Calendar.getInstance();
		fakeNews2Date.set(2009, 3, 12);
		
		Asset fakeNews2Asset = new Asset("fake2imgsrc");
		
		News fakeNews2 = new News("meu outro headline", "uma descricao feia", fakeNews2Date.getTime().toString(), fakeNews2Asset , historyCategories);
		
		
		List<News> fakeNews = fakeDB.getFakeNews();
		
		
		assertThat(fakeNews, hasItem(fakeNews1));
		assertThat(fakeNews, hasItem(fakeNews2));
	}

}
