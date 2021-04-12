package com.sonar.vishal.logic;

import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sonar.vishal.util.Constant;

public class File {

	public String read(String path) {
		StringBuilder builder = new StringBuilder();
		try {
			Scanner sc = new Scanner(new java.io.File(path));
			while (sc.hasNextLine()) {
				builder.append(sc.nextLine());
			}
		} catch (Exception e) {
			// Do Nothing.
		}
		return builder.toString();
	}

	public JsonArray readConfig(String path) {
		JsonArray jsonArray = new JsonArray();
		String json = read(path);
		JsonElement element = JsonParser.parseString(json);
		if (element.isJsonObject()) {
			jsonArray = element.getAsJsonObject().get(Constant.CONFIG_STRING).getAsJsonArray();
		}
		return jsonArray;
	}
}
