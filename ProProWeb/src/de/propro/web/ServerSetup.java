package de.propro.web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;

import de.propro.backend.dijkstra.Dijkstra;
import de.propro.backend.reading.GraphReader;


@Path("interface")
public class ServerSetup {

	@GET
	@Path("test")
    @Produces(MediaType.TEXT_PLAIN)
	public String getSomething() {
		try {
		Dijkstra s=new Dijkstra(new GraphReader(""));
		}catch(Exception e) {
			
		}
        return "Response from Jersey Restful Webservice";	
        
	}

}
