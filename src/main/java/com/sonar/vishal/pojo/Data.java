package com.sonar.vishal.pojo;

import javax.ws.rs.core.UriInfo;

import com.google.gson.JsonElement;
import com.sonar.vishal.util.Constant;

public class Data {

	private String databaseName;
	private String collectionName;
	private boolean isPresent;
	private JsonElement dataParameter;

	public Data(String databaseName, String collectionName, JsonElement dataParameter) {
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		this.dataParameter = dataParameter;
	}

	public Data(UriInfo uriInfo, JsonElement dataParameter) {
		if (uriInfo != null) {
			String[] splitPath = uriInfo.getPath().split("/");
			if (splitPath.length == 2) {
				databaseName = splitPath[0];
				collectionName = splitPath[1];
				isPresent = Constant.MAP_LOGIC.isPresent(databaseName, collectionName);
			}
		}
		if (dataParameter != null) {
			this.dataParameter = dataParameter;
		}
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public JsonElement getDataParameter() {
		return dataParameter;
	}

	public void setDataParameter(JsonElement dataParameter) {
		this.dataParameter = dataParameter;
	}
}
