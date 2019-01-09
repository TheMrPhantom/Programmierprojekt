package de.propro.web;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import de.propro.web.util.ServerSetup;

@Path("/internal")
public class Internal {
	
	@POST
	@Path("initGraph")
	public String initGraph(@QueryParam("path") String path) {
		ServerSetup.initializeGraph(path);
		System.out.println("The path is: "+path);
		return "OK";
	}
	
}
