package com.sonar.vishal.logic;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.sonar.vishal.util.Constant;

public class Security {

	public static final String EMPTY = "";
	private static final String ISSUER = "hind";
	private static final String SUBJECT = "hind-query";
	private static final String QUERY = "query";
	private static final Algorithm ALGORITHM = Algorithm.HMAC512(Constant.CONFIG.getSecret());

	public String getToken(JsonObject claims) {
		if (claims == null) {
			return EMPTY;
		}
		return JWT.create()
				  .withIssuer(ISSUER)
				  .withSubject(SUBJECT)
				  .withIssuedAt(new Date())
				  .withClaim(QUERY, claims.toString())
				  .sign(ALGORITHM);
	}

	public String getQuery(String token) {
		String result = EMPTY;
		try {
			DecodedJWT decodedToken = JWT.decode(token);
			if (decodedToken.getIssuer().equals(ISSUER) || decodedToken.getSubject().equals(SUBJECT)) {
				result = decodedToken.getClaim(QUERY).asString();
			}
		} catch (Exception e) {
			// Do Nothing.
		}
		return result;
	}
}
