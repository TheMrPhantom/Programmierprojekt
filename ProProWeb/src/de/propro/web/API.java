package de.propro.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import de.propro.backend.dijkstra.Dijkstra;
import de.propro.backend.dijkstra.DijkstraOneToAllResult;
import de.propro.web.json.OneToAllInput;
import de.propro.web.util.ServerSetup;

@Path(ServerSetup.BASE_URL)
public class API {

	@GET
	@Path("test")
	public Response test() {

		ResponseBuilder response = Response.ok("dffd", MediaType.APPLICATION_JSON);
		Response output = response.build();
		return output;
	}

	@GET
	@Path("test2")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response test2(@QueryParam("tt") Double d) {

		Dijkstra dijkstra = new Dijkstra(ServerSetup.reader);
		DijkstraOneToAllResult dResult = dijkstra.oneToAll(23);

		ResponseBuilder response = Response.ok("gg: " + d, MediaType.APPLICATION_JSON);
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
	@Consumes(MediaType.TEXT_PLAIN)
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
	 * @param input The latitude and longitude from the start node
	 * @return The time for the calculation and all results as json
	 */
	@GET
	@Path("oneToAllSearch")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response oneToAllDijkstra2(String input) {

		Gson jInput = new Gson();
		OneToAllInput otai=jInput.fromJson(input, OneToAllInput.class);
		Dijkstra dijkstra = new Dijkstra(ServerSetup.reader);
		int nodeIdx = ServerSetup.reader.findNearestNode(otai.lat, otai.lng);
		DijkstraOneToAllResult dResult = dijkstra.oneToAll(nodeIdx);

		ResponseBuilder response;
		String jsonOutput;
		if (dResult != null) {
			jsonOutput = jInput.toJson(dResult);
			response = Response.ok(jsonOutput, MediaType.APPLICATION_JSON);
		} else {
			response = Response.status(412);
		}

		Response output = response.build();
		return output;
	}

}
