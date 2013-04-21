package br.com.entropie.hellocuriosity.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd");
	
	public static String format(Date date) {
		return sdf.format(date);
	}
	
}
