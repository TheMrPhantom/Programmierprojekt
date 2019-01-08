package de.propro.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.tomcat.jni.Status;

import com.google.gson.Gson;

import de.propro.backend.dijkstra.Dijkstra;
import de.propro.backend.dijkstra.DijkstraOneToAllResult;
import de.propro.web.util.ServerSetup;

@Path(ServerSetup.BASE_URL)
public class API {

	@GET
	@Path("test")
	public Response test() {

		ResponseBuilder response = Response.ok("", MediaType.APPLICATION_JSON);
		Response output = response.build();
		return output;
	}

	/**
	 * 
	 * Request for the one to all Dijkstra
	 * 
	 * @param input The index of the node to start from
	 * @return The time for the calculation and all results as json
	 */
	@GET
	@Path("oneToAll")
	public Response oneToAllDijkstra(@QueryParam("nodeID") int input) {

		Dijkstra dijkstra = new Dijkstra(ServerSetup.reader);
		DijkstraOneToAllResult dResult = dijkstra.oneToAll(input);

		Gson jInput = new Gson();
		jInput.toJson(dResult);

		ResponseBuilder response = Response.ok(dResult, MediaType.APPLICATION_JSON);
		Response output = response.build();
		return output;
	}

	/**
	 * 
	 * Request for the one to all Dijkstra
	 * 
	 * @param input The index of the node to start from
	 * @return The time for the calculation and all results as json
	 */
	@GET
	@Path("oneToAll")
	public Response oneToAllDijkstra(@QueryParam("lat") double lat, @QueryParam("long") double lng) {

		Gson jInput = new Gson();

		Dijkstra dijkstra = new Dijkstra(ServerSetup.reader);
		int nodeIdx = ServerSetup.reader.findNearestNode(lat, lng);
		DijkstraOneToAllResult dResult = dijkstra.oneToAll(nodeIdx);
		
		ResponseBuilder response;
		String jsonOutput;
		if (dResult != null) {
			jsonOutput=jInput.toJson(dResult);
			response = Response.ok(jsonOutput, MediaType.APPLICATION_JSON);		
		}else {
			response = Response.status(412);
		}
		
		
		Response output = response.build();
		return output;
	}

}
