package de.propro.web.json.geojson;

import java.util.ArrayList;

import de.propro.web.json.Coordinate;

public class Geometry {
	public String type;
	public double[][] coordinates;

	public Geometry(ArrayList<Coordinate> coordinates) {
		type = "LineString";
		this.coordinates = new double[coordinates.size()][2];

		for (int i = 0; i < coordinates.size(); i++) {
			this.coordinates[i][0] = coordinates.get(i).lng;
			this.coordinates[i][1] = coordinates.get(i).lat;
		}

	}
}
