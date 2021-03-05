package com.sonar.vishal.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.model.UpdateOptions;
import com.sonar.vishal.logic.JvmArg;
import com.sonar.vishal.logic.Mapping;
import com.sonar.vishal.pojo.Config;

public class Constant {

	public static final String URL_TOKEN = "-Durl=";
	public static final String CONFIG_PATH_TOKEN = "-DconfigPath=";
	public static final String SECRET = "-Dsecret=";
	public static final String CONFIG_STRING = "config";
	public static final String DATABASE_STRING = "database";
	public static final String COLLECTIONS_STRING = "collections";

	public static final String RESPONSE_CODE_STRING = "ResponseCode";
	public static final String SUCCESS_RESPONSE_CODE = "Success";
	public static final String FAILURE_RESPONSE_CODE = "Error";
	public static final String RESPONSE_MESSAGE_STRING = "ResponseMessage";
	public static final String SUCCESS_RESPONSE_MESSAGE = "Success";

	public static final String MODIFIED_COUNT = "ModifiedCount";
	public static final String MATCH_COUNT = "MatchCount";
	public static final String UPSERTED_ID = "UpsertedId";
	public static final String DELETE_COUNT = "DeleteCount";
	
	public static final String OBJECT_ID = "$oid";
	public static final String _ID = "_id";
	public static final String DOCUMENT = "Document";
	public static final String DATE_TIME_STRING = "DateTime";

	public static final String DATE_TIME_FORMAT = "ddMMyyyyHHmmss";
	public static final String GENERIC_RESPONSE = "unable to process request";
	public static final String UTF = "UTF-8";
	
	public static final Config CONFIG = new JvmArg().read();
	public static final Mapping MAP_LOGIC = new Mapping();
	public static final MongoClientURI URI = new MongoClientURI(CONFIG.getConnectionUrl());
	public static final MongoClient MONGO_CLIENT = new MongoClient(URI);

	public static final UpdateOptions UPDATE_POLICY = new UpdateOptions().upsert(true);
	public static final TransactionOptions TRANSACTION_OPTION = TransactionOptions.builder()
																				  .writeConcern(WriteConcern.MAJORITY)
																				  .build();
}
