package de.propro.backend.reading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

/**
 * 
 * This class iterates over a given graph and saves the information into
 * multiple arrays alowing multithreading. It also calculates the nearest node
 * to a given node which is relevant for Dijkstras algorithm. The class combines
 * the methods of NodeReader and EdgeReader to a full GraphReader
 *
 */
public class GraphReader {

	private int[] indices;
	private double[] coordinates;
	private int[] edges;
	private File file;

	/**
	 * Constructor of GraphReader
	 * 
	 * @param name The path to the graph which should be read
	 */
	public GraphReader(String name) {
		name = name.replaceFirst("~", System.getProperty("user.home"));
		File f = new File(name);
		this.file = f;
		if (f == null || !f.isFile()) {
			System.err.println("File does not exist or is a directory");
		} else {
			System.out.println("File found: " + this.file.getAbsolutePath());
			System.out.println("Graph reader initialized");
			System.out.println();
		}
	}

	/**
	 * Reads data of given path and saves them into respective arrays. This method
	 * is faster due multiple threads.
	 */
	public void readDataFast() {
		int numOfNodes = 0;
		int numOfEdges = 0;

		System.out.println("Started fastReading...");
		long time = System.nanoTime();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			for (int i = 0; i < 5; i++) {
				br.readLine();
			}
			numOfNodes = Integer.parseInt(br.readLine());
			numOfEdges = Integer.parseInt(br.readLine());
			indices = new int[numOfNodes];
		} catch (IOException e) {
			System.out.println("Error while detecting number of edges and nodes");
		}

		NodeReader nodes = new NodeReader(file, numOfNodes);
		EdgeReader edges = new EdgeReader(file, numOfNodes, numOfEdges);

		/*
		 * Start edges first because thread need to do more stuff because line skipping
		 */
		Thread t1 = new Thread(edges);
		Thread t2 = new Thread(nodes);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			System.out.println("Internal threading error");
		}

		if (!edges.isSuccessfull || !nodes.isSuccessfull) {
			throw new IllegalStateException("The reading failed");
		}

		this.edges = edges.getEdges();
		this.indices = edges.getIndices();
		this.coordinates = nodes.getCoordinates();

		System.out.println("Finished reading. Time elapsed: " + (double) (System.nanoTime() - time) / 1000000000f);
		System.out.println();
	}

	/**
	 * 
	 * Gets the indices of the nodes
	 * 
	 * @return An array with the indices
	 */
	public int[] getIndices() {
		return this.indices;
	}

	/**
	 * 
	 * Gets the coordinates list
	 * 
	 * @return An Array of coordinates always 2 indices forming one coordinate
	 */
	public double[] getCoordinates() {
		return this.coordinates;
	}

	/**
	 * 
	 * Gets the Edges
	 * 
	 * @return An array with the form [n]=src, [n+1]=trgt, [n+2]=costs
	 */
	public int[] getEdges() {
		return this.edges;
	}

	/**
	 * Find nearest node to given node
	 * 
	 * @param latitude  Latitude of given node
	 * @param longitude Longtitude of given node
	 * @return Index of nearest node
	 */
	public int findNearestNode(double latitude, double longitude) {
		int num = coordinates.length;
		int bestNodeIndex = -1;
		double bestDistance = Double.MAX_VALUE;

		double currentLat, currentLong, currentDistance;

		for (int i = 0; i < num; i += 2) {
			currentLat = coordinates[i];
			currentLong = coordinates[i + 1];

			currentDistance = Math.sqrt(Math.pow(latitude - currentLat, 2) + Math.pow(longitude - currentLong, 2));
			if (currentDistance < bestDistance) {
				bestDistance = currentDistance;
				bestNodeIndex = i / 2;
			}
		}
		return bestNodeIndex;
	}
}