package br.com.entropie.hellocuriosity.news;

import java.util.List;


public class Timeline {

	private final String headline;
	private final String type;
	private final String text;
	private final String startDate;
	private final List<News> news;
	private final Asset asset;

	public Timeline(List<News> news) {
		this.headline = "Know Curiosity";
		this.type = "default";
		this.text = "<p>Mussum ipsum cacilds, vidis litro abertis. Consetis adipiscings elitis. Pra lá , depois divoltis porris, paradis. Paisis, filhis, espiritis santis. Mé faiz elementum girarzis, nisi eros vermeio, in elementis mé pra quem é amistosis quis leo. Manduma pindureta quium dia nois paga. Sapien in monti palavris qui num significa nadis i pareci latim. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.</p>";
		this.startDate = "2013,3,3";
		this.news = news;
		this.asset = new Asset(
				"http://upload.wikimedia.org/wikipedia/commons/0/07/Curiosity-_front.PNG");
	}

	public String getHeadline() {
		return headline;
	}

	public String getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public String getStartDate() {
		return startDate;
	}

	public List<News> getNews() {
		return news;
	}

	public Asset getAsset() {
		return asset;
	}

}
