package com.sonar.vishal.logic;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sonar.vishal.util.Constant;

public class Mapping {

	private Map<String, String> table;

	public Mapping() {
		String databaseName = null;
		String collections = null;
		table = new HashMap<>();
		JsonArray array = new File().readConfig(Constant.CONFIG.getConfigFilePath());
		for (JsonElement element : array) {
			try {
				JsonObject innerObject = element.getAsJsonObject();
				databaseName = innerObject.get(Constant.DATABASE_STRING).getAsString();
				collections = innerObject.get(Constant.COLLECTIONS_STRING).getAsJsonArray().toString();
				collections = collections.split(Constant.OPENING_BRACKET)[1].split(Constant.CLOSING_BRAKET)[0];
				table.put(databaseName, collections);
			} catch (Exception e) {
				// Do Nothing.
			}
		}
	}

	public boolean isPresent(String databaseName, String collectionName) {
		boolean state = false;
		if (table.containsKey(databaseName)) {
			String arrayCollection = table.get(databaseName);
			String[] array = arrayCollection.split(Constant.COMMA);
			for (String unit : array) {
				unit = unit.replaceAll(Constant.BACK_SLASH_REGEX, Constant.EMPTY);
				if (collectionName.equals(unit)) {
					state = true;
					break;
				}
			}
		}
		return state;
	}
}
