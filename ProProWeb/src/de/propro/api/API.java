package de.propro.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("api")
public class API {

	@GET
	@Path("internal/initGraph")
	public String initGraph(@QueryParam("path") String path) {
		ServerSetup.initializeGraph(path);
		System.out.println("The path is: "+path);
		return "OK";
	}

}
