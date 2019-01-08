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

	/**
	 * 
	 * Handles a request for the one to all Dijkstra. You can either pass a nodeID
	 * 
	 * @param nodeID    The index of the node to start from
	 * @param latitude  The latitude of the coordinate. It's not required that the
	 *                  latitude is a valid coordinate of a node.
	 * @param longitude The latitude of the coordinate. It's not required that the
	 *                  latitude is a valid coordinate of a node.
	 * @return The processing time and the distance to all nodes from the start node
	 */
	@GET
	@Path("oneToAll")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response oneToAllDijkstra(@QueryParam("nodeID") Integer nodeID, @QueryParam("lat") Double latitude,
			@QueryParam("long") Double longitude) {

		ResponseBuilder response = null;
		Dijkstra dijkstra = null;
		DijkstraOneToAllResult dResult = null;
		Response output = null;
		Gson jsonHandler = new Gson();

		if (nodeID != null) {
			/* Use node index */
			dijkstra = new Dijkstra(ServerSetup.reader);
			dResult = dijkstra.oneToAll(nodeID);

			jsonHandler.toJson(dResult);

			response = Response.ok(dResult, MediaType.APPLICATION_JSON);
			output = response.build();
		} else if (longitude != null && latitude != null) {
			/* Use coordiantes */
			output = oneToAllDijkstra(ServerSetup.reader.findNearestNode(latitude, longitude), null, null);

		} else {
			/* Report an error */
			response = Response.status(412);
			output = response.build();
		}

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
	public Response oneToAllDijkstraSearch(String input) {

		Gson jInput = new Gson();
		OneToAllInput otai = jInput.fromJson(input, OneToAllInput.class);
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
