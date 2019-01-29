/**
 * This maps handles the events on the map
 */

var lastRequest = null;

function mapWasClicked(response) {

	var response = JSON.parse(response);

	var popup = L.popup().setLatLng([ response.lat, response.lng ]).setContent(
			"You clicked the map at:</br>" + "Latitude: " + response.lat
					+ "</br>" + "Longitude: " + response.lng + "</br>"
					+ "Node-ID: " + response.nodeID).openOn(mymap);

	var inputStart = document.getElementById("inputStart");
	var inputEnd = document.getElementById("inputEnd");

	if (inputStart.value == "") {
		inputStart.value = response.nodeID;
		inputStart.onchange();
	} else if (inputEnd.value == "") {
		inputEnd.value = response.nodeID;
		inputEnd.onchange();
	}
}

function displayRoute(response) {
	if (lastRequest != null) {
		mymap.removeLayer(lastRequest);
	}
	
	var layerPostalcodes = L.geoJSON().addTo(mymap);
	layerPostalcodes.addData(JSON.parse(response));

	lastRequest = new L.LayerGroup();
	lastRequest.addTo(mymap);
	lastRequest.addLayer(layerPostalcodes);

	var resp = JSON.parse(response);

	var popup = L.popup().setLatLng([ resp.avgNode.lat, resp.avgNode.lng ])
			.setContent(
					"The calculated path is " + resp.lengthInKM + " km long")
			.openOn(mymap);

}

function onMapClick(e) {

	httpGetAsync("http://localhost:8080/ProProWeb/api/public/nearestNode?lat="
			+ e.latlng.lat + "&long=" + e.latlng.lng, mapWasClicked);

}

function checkRoute() {
	var inputStart = document.getElementById("inputStart");
	var inputEnd = document.getElementById("inputEnd");

	if (inputStart.value != "" && inputEnd.value != "") {
		httpGetAsync(
				"http://localhost:8080/ProProWeb/api/public/startToEndGeo?start="
						+ inputStart.value + "&end=" + inputEnd.value,
				displayRoute);
	}
}

mymap.on('click', onMapClick);