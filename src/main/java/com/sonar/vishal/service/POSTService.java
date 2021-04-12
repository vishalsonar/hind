package com.sonar.vishal.service;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.client.ClientSession;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import com.sonar.vishal.pojo.Data;
import com.sonar.vishal.util.Constant;

public class POSTService extends Service {

	public POSTService(Data data) {
		super.init(data);
	}

	@SuppressWarnings("deprecation")
	@Override
	public JsonElement doService() {
		ClientSession session = null;
		JsonObject json = new JsonObject();
		try {
			session = Constant.MONGO_CLIENT.startSession();
			session.startTransaction(Constant.TRANSACTION_OPTION);
			Bson filter = Filters.eq(Constant.ID, new ObjectId(id));
			UpdateResult result = dataCollection.replaceOne(filter, document, Constant.UPDATE_POLICY);
			session.commitTransaction();
			json = successPostResponse(result);
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

	private JsonObject successPostResponse(UpdateResult result) {
		JsonObject json = successResponse();
		json.addProperty(Constant.MODIFIED_COUNT, result.getModifiedCount());
		json.addProperty(Constant.MATCH_COUNT, result.getMatchedCount());
		if (result.getUpsertedId() != null) {
			json.addProperty(Constant.UPSERTED_ID, result.getUpsertedId().toString());
		}
		return json;
	}
}
