package de.propro.web.json.geojson;

import java.util.ArrayList;

import de.propro.web.json.Coordinate;

public class Feature {
	public String type;
	public Property properties;
	public Geometry geometry;
	
	public Feature(ArrayList<Coordinate> coordinates) {
		type="Feature";
		properties=new Property();
		geometry=new Geometry(coordinates);
	}
}
