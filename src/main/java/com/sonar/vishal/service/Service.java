package com.sonar.vishal.service;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sonar.vishal.pojo.Data;
import com.sonar.vishal.util.Constant;

public abstract class Service {

	protected String id;
	protected Document document;
	protected MongoCollection<Document> dataCollection;

	protected void init(Data data) {
		if (data == null) {
			return;
		}
		if (data.getCollectionName() == null || data.getDatabaseName() == null || data.getDataParameter() == null) {
			return;
		}
		MongoDatabase database = Constant.MONGO_CLIENT.getDatabase(data.getDatabaseName());
		dataCollection = database.getCollection(data.getCollectionName());
		JsonObject dataObject = data.getDataParameter().getAsJsonObject();
		if (dataObject.get(Constant.ID) != null) {
			id = dataObject.get(Constant.ID).getAsString();
			dataObject.remove(Constant.ID);
			document = new Document(Constant.ID, new ObjectId(id));
			document.putAll(Document.parse(data.getDataParameter().toString()));
		} else {
			document = Document.parse(dataObject.toString());
		}
	}

	protected JsonObject successResponse() {
		JsonObject json = new JsonObject();
		json.addProperty(Constant.RESPONSE_CODE_STRING, Constant.SUCCESS_RESPONSE_CODE);
		json.addProperty(Constant.RESPONSE_MESSAGE_STRING, Constant.SUCCESS_RESPONSE_MESSAGE);
		return json;
	}

	protected JsonObject failedResponse(String message) {
		JsonObject json = new JsonObject();
		json.addProperty(Constant.RESPONSE_CODE_STRING, Constant.FAILURE_RESPONSE_CODE);
		json.addProperty(Constant.RESPONSE_MESSAGE_STRING, message);
		return json;
	}

	public abstract JsonElement doService();
}
