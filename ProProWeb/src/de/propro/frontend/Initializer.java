package de.propro.frontend;

import javax.servlet.http.HttpServlet;

import de.propro.backend.reading.GraphReader;

public class Initializer extends HttpServlet {

	private static final long serialVersionUID = 4051868071986073431L;

	public void init() {
		Dijkstra.reader = new GraphReader("../ProPro/bw.fmi");
		Dijkstra.reader.readDataFast();
	}

}
