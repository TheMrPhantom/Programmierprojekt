package de.propro.web.json.geojson;

import java.util.ArrayList;

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

			distance += distFrom(latA, lngA, latB, lngB);

		}
		
		this.lengthInKM = Math.round(distance/10.0) / 100.0;
	}

	private static double distFrom(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist;
	}

}
