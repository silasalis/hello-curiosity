package br.com.entropie.hellocuriosity.news;

public class Asset {

	private final String media;
	private final String credit;
	private final String caption;

	public Asset(String media) {
		this.media = media;
		this.credit = "";
		this.caption = "";
	}

	public Asset(String media, String credit, String caption) {
		this.media = media;
		this.credit = credit;
		this.caption = caption;
	}

	public String getMedia() {
		return media;
	}

	public String getCredit() {
		return credit;
	}

	public String getCaption() {
		return caption;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result + ((credit == null) ? 0 : credit.hashCode());
		result = prime * result + ((media == null) ? 0 : media.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
		if (credit == null) {
			if (other.credit != null)
				return false;
		} else if (!credit.equals(other.credit))
			return false;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		return true;
	}
}
