package de.propro.backend.reading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class GraphReader {

	private int[] indices;
	private double[] coordinates;
	private int[] edges;
	private File file;

	/**
	 * Constructor of GraphReader
	 * 
	 * @param f is a File object for the graph data file
	 */
	public GraphReader(String name) {
		File f = new File(name);
		if (f == null || !f.isFile()) {
			System.err.println("File does not exist or is a directory");
			System.exit(1);
		}
		this.file = f;
		System.out.println("File found: " + this.file.getAbsolutePath());
		System.out.println("Graph reader initialized");
		System.out.println();
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
	 * TODO find nearest node to given node  
	 * @param lat latitude of given node
	 * @param long longtitude of given node
	 * @return index i of nearest node
	 */
	public int findNearestNode(double latitude, double longitude) {
		int num = coordinates.length;
		int bestNodeIndex = 0;
		double bestDistance = Double.MAX_VALUE;
		
		double currentLat, currentLong, currentDistance;
		
		for (int i = 0; i < num; i+=2) {
			currentLat = coordinates[i];
			currentLong = coordinates[i + 1];
			
			currentDistance = Math.sqrt(Math.pow(latitude - currentLat, 2) + Math.pow(longitude - currentLong, 2));
			if (currentDistance < bestDistance) {
				bestDistance = currentDistance;
				bestNodeIndex = i;
			}
		}
		return bestNodeIndex;
	}
}