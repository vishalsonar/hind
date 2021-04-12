package com.sonar.vishal.service;

import org.bson.types.ObjectId;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.client.ClientSession;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.sonar.vishal.pojo.Data;
import com.sonar.vishal.util.Constant;

public class DELETEService extends Service {

	public DELETEService(Data data) {
		super.init(data);
	}

	@Override
	public JsonElement doService() {
		ClientSession session = null;
		JsonObject json = new JsonObject();
		try {
			session = Constant.MONGO_CLIENT.startSession();
			session.startTransaction(Constant.TRANSACTION_OPTION);
			DeleteResult result = dataCollection.deleteOne(Filters.eq(Constant.ID, new ObjectId(id)));
			session.commitTransaction();
			json = successDeleteResponse(result);
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

	private JsonObject successDeleteResponse(DeleteResult result) {
		JsonObject json = successResponse();
		json.addProperty(Constant.DELETE_COUNT, result.getDeletedCount());
		return json;
	}
}
