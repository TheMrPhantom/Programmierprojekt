package de.propro.web.json;

import java.util.ArrayList;

import de.propro.backend.dijkstra.DijktraResult;

public class MultipleQueueResponse {
	
	ArrayList<DijktraResult> requests;
	
	public MultipleQueueResponse(int capacity) {
		this.requests=new ArrayList<DijktraResult>(capacity);
	}
	
}
