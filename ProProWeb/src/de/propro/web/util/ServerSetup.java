package de.propro.web.util;

import de.propro.backend.reading.GraphReader;

public class ServerSetup {
	public static final String BASE_URL="api";
	
	public static GraphReader reader;

	public static void initializeGraph(String path) throws IllegalStateException{
		reader = new GraphReader(path);
		reader.readDataFast();
	}

}
