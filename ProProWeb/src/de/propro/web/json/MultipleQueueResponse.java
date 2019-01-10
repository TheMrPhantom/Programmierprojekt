package de.propro.web.json;

public class MultipleQueueResponse {
	
	public MultipleQueueResponse(int capacity) {
		this.requests=new DijkstraResult[capacity];
	}
	
	public DijkstraResult[] requests;
}
