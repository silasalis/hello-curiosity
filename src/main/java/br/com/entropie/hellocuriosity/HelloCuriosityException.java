package br.com.entropie.hellocuriosity;

public class HelloCuriosityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5125004537554757985L;

	public HelloCuriosityException(String msg) {
		super(msg);
	}
	
	public HelloCuriosityException(String msg, Throwable e) {
		super(msg, e);
	}
}
