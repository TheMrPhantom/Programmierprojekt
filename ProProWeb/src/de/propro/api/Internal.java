package de.propro.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import de.propro.web.util.ServerSetup;

@Path(ServerSetup.BASE_URL+"/internal")
public class Internal {
	@GET
	@Path("initGraph")
	public String initGraph(@QueryParam("path") String path) {
		ServerSetup.initializeGraph(path);
		System.out.println("The path is: "+path);
		return "OK";
	}
}
