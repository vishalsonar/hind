package com.sonar.vishal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sonar.vishal.data.TestData;
import com.sonar.vishal.logic.HindLogic;
import com.sonar.vishal.pojo.Data;
import com.sonar.vishal.service.DELETEService;
import com.sonar.vishal.service.GETService;
import com.sonar.vishal.service.POSTService;
import com.sonar.vishal.service.PUTService;
import com.sonar.vishal.service.Service;
import com.sonar.vishal.util.Constant;

@RunWith(JUnit4.class)
public class TestService {

	private Data data;
	private Service service;
	private JsonElement response;
	private String responseCode;

	/**
	 * Test to simulate null parameter.
	 */
	@Test
	public void testNull() {
		response = new PUTService(null).doService();
		responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.FAILURE_RESPONSE_CODE, responseCode);

		response = new PUTService(new Data(null, null, null)).doService();
		responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.FAILURE_RESPONSE_CODE, responseCode);

		response = new PUTService(new Data(TestData.dataBaseName, null, null)).doService();
		responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.FAILURE_RESPONSE_CODE, responseCode);

		response = new PUTService(new Data(TestData.dataBaseName, TestData.collection, null)).doService();
		responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.FAILURE_RESPONSE_CODE, responseCode);

		response = new PUTService(new Data(null, null)).doService();
		responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.FAILURE_RESPONSE_CODE, responseCode);
	}

	/**
	 * Test service for document insert, Test service for document update, Test
	 * service for document delete
	 */
	@Test
	public void testService() {
		data = new Data(TestData.dataBaseName, TestData.collection, TestData.json);
		service = new PUTService(data);
		JsonObject response = service.doService().getAsJsonObject();
		String responseCode = response.get(Constant.RESPONSE_CODE_STRING).getAsString();
		String id = response.get(Constant.ID).getAsString();
		assertEquals(Constant.SUCCESS_RESPONSE_CODE, responseCode);

		data = new Data(TestData.dataBaseName, TestData.collection, TestData.updateJson(id));
		service = new POSTService(data);
		response = service.doService().getAsJsonObject();
		responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.SUCCESS_RESPONSE_CODE, responseCode);

		data = new Data(TestData.dataBaseName, TestData.collection, TestData.deleteJson(id));
		service = new DELETEService(data);
		response = service.doService().getAsJsonObject();
		responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.SUCCESS_RESPONSE_CODE, responseCode);
	}

	/**
	 * Test service to get document
	 */
	@Test
	public void testGetService() {
		data = new Data(TestData.dataBaseName, TestData.collection, new JsonObject());
		service = new GETService(data);
		JsonElement response = service.doService();
		String responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.SUCCESS_RESPONSE_CODE, responseCode);

		data = new Data(TestData.dataBaseName, TestData.collection, TestData.json);
		service = new GETService(data);
		response = service.doService();
		responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.SUCCESS_RESPONSE_CODE, responseCode);

		HindLogic hindLogic = new HindLogic();
		String stringResponse = hindLogic.getProcessedResponse(service);
		response = hindLogic.toJsonObject(stringResponse);
		responseCode = response.getAsJsonObject().get(Constant.RESPONSE_CODE_STRING).getAsString();
		assertEquals(Constant.SUCCESS_RESPONSE_CODE, responseCode);
	}
}
