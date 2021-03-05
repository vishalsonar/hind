package com.sonar.vishal.logic;

import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sonar.vishal.util.Constant;

public class File {

	public String read(String path) {
		Scanner sc = null;
		StringBuffer buffer = new StringBuffer();
		try {
			java.io.File file = new java.io.File(path);
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				buffer.append(sc.nextLine());
			}
		} catch (Exception e) {
			// Do Nothing.
		} finally {
			if (sc != null)
				sc.close();
		}
		return buffer.toString();
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
