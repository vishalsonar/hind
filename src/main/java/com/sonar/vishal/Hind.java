package com.sonar.vishal;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.sonar.vishal.logic.HindLogic;
import com.sonar.vishal.pojo.Data;
import com.sonar.vishal.service.DELETEService;
import com.sonar.vishal.service.GETService;
import com.sonar.vishal.service.POSTService;
import com.sonar.vishal.service.PUTService;
import com.sonar.vishal.service.Service;

@Path("")
public class Hind {

	private Data data;
	private Service service;
	private String response;
	private HindLogic logic;

	public Hind() {
		logic = new HindLogic();
	}

	@GET
	@Path("/{databaseName}/{collectionName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getService(@Context UriInfo uriInfo, @QueryParam("query") String query) {
		data = new Data(uriInfo, logic.queryToJson(query));
		if (data.isPresent()) {
			service = new GETService(data);
			response = logic.getProcessedResponse(service);
		} else {
			response = logic.getFailedResponse();
		}
		return response;
	}

	@POST
	@Path("/{databaseName}/{collectionName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String postService(@Context UriInfo uriInfo, String postData) {
		data = new Data(uriInfo, logic.toJsonObject(postData));
		if (data.isPresent()) {
			service = new POSTService(data);
			response = logic.getProcessedResponse(service);
		} else {
			response = logic.getFailedResponse();
		}
		return response;
	}

	@PUT
	@Path("/{databaseName}/{collectionName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String putService(@Context UriInfo uriInfo, String putData) {
		data = new Data(uriInfo, logic.toJsonObject(putData));
		if (data.isPresent()) {
			service = new PUTService(data);
			response = logic.getProcessedResponse(service);
		} else {
			response = logic.getFailedResponse();
		}
		return response;
	}

	@DELETE
	@Path("/{databaseName}/{collectionName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteService(@Context UriInfo uriInfo, String deleteData) {
		data = new Data(uriInfo, logic.toJsonObject(deleteData));
		if (data.isPresent()) {
			service = new DELETEService(data);
			response = logic.getProcessedResponse(service);
		} else {
			response = logic.getFailedResponse();
		}
		return response;
	}
}
