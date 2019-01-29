package de.propro.web.json.geojson;

import java.util.ArrayList;

import org.apache.tomcat.util.file.Matcher;

import de.propro.web.json.Coordinate;

public class GeoRoute {
	public String type;
	public Feature[] features;
	public Coordinate startNode;
	public Coordinate endNode;
	public Coordinate avgNode;
	public Style style;
	public double lengthInKM;

	public GeoRoute(ArrayList<Coordinate> coordinates) {
		features = new Feature[1];
		type = "FeatureCollection";
		features[0] = new Feature(coordinates);
		startNode = coordinates.get(0);
		endNode = coordinates.get(coordinates.size() - 1);
		avgNode = new Coordinate();
		avgNode.lat = startNode.lat + (endNode.lat - startNode.lat) / 2;
		avgNode.lng = startNode.lng + (endNode.lng - startNode.lng) / 2;
		style = new Style("#ff0000", 5, 0.65);
		double distance = 0;
		for (int i = 0; i < coordinates.size() - 1; i++) {
			double latA = coordinates.get(i).lat;
			double lngA = coordinates.get(i).lng;

			double latB = coordinates.get(i + 1).lat;
			double lngB = coordinates.get(i + 1).lng;

			double latR = latB - latA;
			double lngR = lngB - lngA;

			distance += Math.sqrt(Math.pow(latR, 2) + Math.pow(lngR, 2));
		}
		this.lengthInKM = Math.round(distance * 10000) / 100.0;
	}
}
