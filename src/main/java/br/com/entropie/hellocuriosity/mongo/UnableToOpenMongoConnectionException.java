package br.com.entropie.hellocuriosity.mongo;

import br.com.entropie.hellocuriosity.HelloCuriosityException;

public class UnableToOpenMongoConnectionException extends
		HelloCuriosityException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2009968466925803232L;

	public UnableToOpenMongoConnectionException(String msg) {
		super(msg);
	}

	public UnableToOpenMongoConnectionException(String msg, Throwable e) {
		super(msg);
	}
}
