package de.propro.frontend;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
 
@Path("/test")
public class TestClass {
	@GET
	@Produces("application/xml")
	public String testMethod() {
 
		return "<Test>Hello World!</Test>";
	}
 
	
}