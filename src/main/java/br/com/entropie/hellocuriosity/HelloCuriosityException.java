package br.com.entropie.hellocuriosity;

public class HelloCuriosityException extends RuntimeException {

	public HelloCuriosityException(String msg) {
		super(msg);
	}
	
	public HelloCuriosityException(String msg, Throwable e) {
		super(msg, e);
	}
	
	

}
