package de.propro.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import de.propro.web.util.ServerSetup;

@Path("/internal")
public class Internal {

	// http://localhost:8080/ProProWeb/api/internal/initGraph?path=/home/kingfisher/Git/Programmierprojekt/ProProWeb/germany.fmi

	@GET
	@Path("initGraph")
	public Response initGraph(@QueryParam("path") String path) {
		ResponseBuilder response;
		try {
			ServerSetup.initializeGraph(path);
			System.out.println("The path is: " + path);
			response = Response.ok();
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
			response = Response.status(412);
		}

		Response output = response.build();

		return output;
	}

}
