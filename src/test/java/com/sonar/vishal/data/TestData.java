package com.sonar.vishal.data;

import java.util.Random;

import com.google.gson.JsonObject;
import com.sonar.vishal.util.Constant;

public class TestData {

	public static final String dataBaseName = "test";
	public static final String collection = "data";
	public static final JsonObject json = initJson();

	public static JsonObject initJson() {
		JsonObject json = new JsonObject();
		json.addProperty("test", "value");
		return json;
	}

	public static JsonObject updateJson(String id) {
		JsonObject json = new JsonObject();
		json.addProperty(Constant._ID, id);
		json.addProperty("test", new Random().nextDouble());
		return json;
	}

	public static JsonObject deleteJson(String id) {
		JsonObject json = new JsonObject();
		json.addProperty(Constant._ID, id);
		json.addProperty("test", "value");
		return json;
	}
}
