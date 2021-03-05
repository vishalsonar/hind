package com.sonar.vishal.service;

import org.bson.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoCursor;
import com.sonar.vishal.pojo.Data;
import com.sonar.vishal.util.Constant;

public class GETService extends Service {

	public GETService(Data data) {
		super.init(data);
	}

	@Override
	public JsonElement doService() {
		JsonObject json = new JsonObject();
		JsonArray documentArray = new JsonArray();
		try {
			JsonObject element = null;
			JsonObject idObj = null;
			MongoCursor<Document> documentCursor = dataCollection.find(document).iterator();
			while (documentCursor.hasNext()) {
				element = JsonParser.parseString(documentCursor.next().toJson()).getAsJsonObject();
				idObj = element.getAsJsonObject().get(Constant._ID).getAsJsonObject();
				element.addProperty(Constant._ID, idObj.get(Constant.OBJECT_ID).getAsString());
				documentArray.add(element);
			}
			json = successResponse();
			json.add(Constant.DOCUMENT, documentArray);
		} catch (Exception e) {
			json = failedResponse(e.getMessage());
		}
		return json;
	}
}
