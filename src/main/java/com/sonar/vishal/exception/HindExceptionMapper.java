package com.sonar.vishal.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.sonar.vishal.logic.HindLogic;

public class HindExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		String response = new HindLogic().getFailedResponse();
		return Response.ok().entity(response).build();
	}

}
