package de.propro.frontend;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.propro.backend.reading.GraphReader;
 
@Path("/test")
public class TestClass {
	@GET
	@Produces("application/xml")
	public String testMethod() {
 GraphReader r=new GraphReader("ss");
		return "<Test>Hello World!</Test>";
	}
 
	
}