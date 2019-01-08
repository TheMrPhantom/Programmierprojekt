package de.propro.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	@GET
	@Path("oneToAll")
	public Response oneToAllDijkstra(@QueryParam("nodeID") String input) {

		Gson jInput = new Gson();
		int nodeID = jInput.fromJson(input, Integer.class);

		Dijkstra dijkstra = new Dijkstra(ServerSetup.reader);
		DijkstraOneToAllResult dResult = dijkstra.oneToAll(nodeID);

		jInput.toJson(dResult);
		
		ResponseBuilder response = Response.ok(dResult, MediaType.APPLICATION_JSON);
		Response output = response.build();
		return output;
	}

}
