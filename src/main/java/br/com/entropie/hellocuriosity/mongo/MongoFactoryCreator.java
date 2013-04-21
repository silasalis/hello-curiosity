package br.com.entropie.hellocuriosity.mongo;

import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import br.com.caelum.vraptor.ioc.ComponentFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class MongoFactoryCreator implements ComponentFactory<DBCollection> {

	private DBCollection coll;

	@PostConstruct
	public void create() {
		try {
			MongoClient mongoClient = new MongoClient("ds039017.mongolab.com",
					39017);

			DB db = mongoClient.getDB("heroku_app15113882");
			db.authenticate("root", "12345".toCharArray());
			mongoClient.setWriteConcern(WriteConcern.SAFE);
			this.coll = db.getCollection("news");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new UnableToOpenMongoConnectionException(
					"Não foi possível estabelecer uma conexão com o mongodb!",
					e);
		}
	}

	@Override
	public DBCollection getInstance() {
		return coll;
	}

	@PreDestroy
	public void destroy() {
		this.coll = null;
	}
	
	public static void main(String[] args) {
		DBCollection collection = new MongoFactoryCreator().getInstance();
		BasicDBObject doc = new BasicDBObject();
		doc.put("body", "");
		collection.insert(doc);
	}
}
