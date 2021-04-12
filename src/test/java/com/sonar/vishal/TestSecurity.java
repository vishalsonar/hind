package com.sonar.vishal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.gson.JsonObject;
import com.sonar.vishal.logic.HindLogic;
import com.sonar.vishal.logic.Security;

@RunWith(JUnit4.class)
public class TestSecurity {

	/**
	 * Test JWT token classes (Security.class) and HindLogic class
	 */
	@Test
	public void testGetToken() {
		Security security = new Security();
		HindLogic hindLogic = new HindLogic();
		String jwtQuery = security.getToken(new JsonObject());
		JsonObject query = hindLogic.queryToJson(jwtQuery);
		assertNotNull(hindLogic.getFailedResponse());
		assertNotNull(jwtQuery);
		assertNotNull(query);
		assertEquals(query, new JsonObject());
	}
}
