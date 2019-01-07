package de.propro.web.json;

import java.util.ArrayList;

public class DijkstraResult {
	public int[] path;
	public int length;
	public double time;

	public DijkstraResult(double time, int length, ArrayList<Integer> path) {
		this.length = length;
		this.time = time;
		this.path = new int[path.size()];
		for (int i = 0; i < path.size(); i++) {
			this.path[i] = path.get(i);
		}
	}
}
