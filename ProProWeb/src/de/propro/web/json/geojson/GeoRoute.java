package de.propro.web.json.geojson;

import java.util.ArrayList;

import de.propro.web.json.Coordinate;

public class GeoRoute {
	public String type;
	public Feature[] features;

	public GeoRoute(ArrayList<Coordinate> coordinates) {
		features = new Feature[1];
		type = "FeatureCollection";
		features[0] = new Feature(coordinates);
	}
}
