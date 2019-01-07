package de.propro.api;

import de.propro.backend.reading.GraphReader;

public class ServerSetup {
	public static GraphReader reader;

	public static void initializeGraph(String path) {
		reader = new GraphReader(path);
		reader.readDataFast();
	}

}
