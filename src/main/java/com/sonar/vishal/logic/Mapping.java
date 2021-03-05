package com.sonar.vishal.logic;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sonar.vishal.util.Constant;

public class Mapping {

	private Map<String, String> mapping;

	public Mapping() {
		String databaseName = null;
		String collections = null;
		mapping = new HashMap<String, String>();
		JsonArray array = new File().readConfig(Constant.CONFIG.getConfigFilePath());
		for (JsonElement element : array) {
			try {
				JsonObject innerObject = element.getAsJsonObject();
				databaseName = innerObject.get(Constant.DATABASE_STRING).getAsString();
				collections = innerObject.get(Constant.COLLECTIONS_STRING).getAsJsonArray().toString();
				collections = collections.split("\\[")[1].split("\\]")[0];
				mapping.put(databaseName, collections);
			} catch (Exception e) {
				// Do Nothing.
			}
		}
	}

	public boolean isPresent(String databaseName, String collectionName) {
		boolean state = false;
		if (mapping.containsKey(databaseName)) {
			String arrayCollection = mapping.get(databaseName);
			String[] array = arrayCollection.split(",");
			for (String unit : array) {
				unit = unit.replaceAll("\"", "");
				if (collectionName.equals(unit)) {
					state = true;
					break;
				}
			}
		}
		return state;
	}
}
