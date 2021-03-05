package com.sonar.vishal.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.client.ClientSession;
import com.sonar.vishal.pojo.Data;
import com.sonar.vishal.util.Constant;

public class PUTService extends Service {

	public PUTService(Data data) {
		super.init(data);
	}

	@Override
	public JsonElement doService() {
		ClientSession session = null;
		JsonObject json = new JsonObject();
		try {
			session = Constant.MONGO_CLIENT.startSession();
			session.startTransaction(Constant.TRANSACTION_OPTION);
			dataCollection.insertOne(document);
			String id = dataCollection.find(document).first().get(Constant._ID).toString();
			session.commitTransaction();
			json = successGetResponse(id);
		} catch (Exception e) {
			if (session != null) {
				session.abortTransaction();
			}
			json = failedResponse(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return json;
	}

	private JsonObject successGetResponse(String id) {
		JsonObject json = successResponse();
		json.addProperty(Constant._ID, id);
		return json;
	}
}