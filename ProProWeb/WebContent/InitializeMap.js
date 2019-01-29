/**
 * Initializes the map centered to the university of stuttgart
 */

function initResponse(temp) {

}

httpGetAsync(
		"http://localhost:8080/ProProWeb/api/internal/initGraph?path=C:/Users/Justin/OneDrive - bwedu/GitHub/Programmierprojekt/ProProWeb/stgt.fmi",
		initResponse);

var mymap = L.map('propromap').setView([ 48.7451668, 9.1066026 ], 13);

L.tileLayer(
				'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}',
				{
					attribution : 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
					maxZoom : 18,
					id : 'mapbox.streets',
					accessToken : 'pk.eyJ1IjoidGhlbXJwaGFudG9tIiwiYSI6ImNqcmdkcHdlajAwNnM0YXU3aHg3dzV2N20ifQ.9MHrEbB-mS6aLCHuLfFuPQ'
				}).addTo(mymap);

function httpGetAsync(theUrl, callback) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
			callback(xmlHttp.responseText);
	}
	xmlHttp.open("GET", theUrl, true);
	xmlHttp.send();
}