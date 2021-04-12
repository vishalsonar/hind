package com.sonar.vishal.logic;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sonar.vishal.service.Service;
import com.sonar.vishal.util.Constant;

public class HindLogic {

	public String getDateAndTime() {
		return new SimpleDateFormat(Constant.DATE_TIME_FORMAT).format(new Date());
	}

	public String getFailedResponse() {
		JsonObject response = new JsonObject();
		response.addProperty(Constant.RESPONSE_CODE_STRING, Constant.FAILURE_RESPONSE_CODE);
		response.addProperty(Constant.RESPONSE_MESSAGE_STRING, Constant.GENERIC_RESPONSE);
		response.addProperty(Constant.DATE_TIME_STRING, getDateAndTime());
		return response.toString();
	}

	public String getProcessedResponse(Service service) {
		JsonObject response = service.doService().getAsJsonObject();
		String responseCode = response.get(Constant.RESPONSE_CODE_STRING).getAsString();
		if (responseCode.equals(Constant.FAILURE_RESPONSE_CODE)) {
			response.addProperty(Constant.RESPONSE_MESSAGE_STRING, Constant.GENERIC_RESPONSE);
		}
		response.addProperty(Constant.DATE_TIME_STRING, getDateAndTime());
		return response.toString();
	}

	public JsonObject toJsonObject(String jsonString) {
		JsonObject json = null;
		try {
			json = JsonParser.parseString(jsonString).getAsJsonObject();
		} catch (Exception e) {
			// Do Nothing.
		}
		return json;
	}

	public JsonObject queryToJson(String query) {
		JsonObject queryObject = null;
		query = new Security().getQuery(query);
		if (!Security.EMPTY.equals(query)) {
			queryObject = toJsonObject(query);
		}
		return queryObject;
	}
}
