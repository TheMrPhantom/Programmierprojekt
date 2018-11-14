package de.propro.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class GraphReader {

	private int[] indices;
	private double[] coordinates;
	private int[] edges;
	private File file;

	/**
	 * Constructor of GraphReader
	 * 
	 * @param f
	 *            is a File object for the graph data file
	 */
	public GraphReader(File f) {
		if (f == null || !f.isFile()) {
			System.err.println("File does not exist or is a directory");
			System.exit(1);
		}
		this.file = f;
		System.out.println("File found: " + this.file.getAbsolutePath());
		System.out.println("Graph reader initialized");
	}

	/**
	 * Reads data of given path and saves them into respective arrays.
	 */
	public void readData() {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			System.out.println("Started reading");
			long time = System.nanoTime();

			for (int i = 0; i < 5; i++) {
				br.readLine();
			}
			int numOfNodes = Integer.parseInt(br.readLine());
			int numOfEdges = Integer.parseInt(br.readLine());
			indices = new int[numOfNodes];
			coordinates = new double[2 * numOfNodes];
			edges = new int[3 * numOfEdges];

			String[] sArr;
			for (int i = 0; i < numOfNodes; i++) {
				sArr = br.readLine().split(" ");
				indices[i] = i;
				// latitude
				coordinates[i * 2 + 0] = Double.parseDouble(sArr[2]);
				// longitude
				coordinates[i * 2 + 1] = Double.parseDouble(sArr[3]);
			}

			for (int i = 0; i < numOfEdges; i++) {
				sArr = br.readLine().split(" ");
				// Source ID
				edges[i * 3 + 0] = Integer.parseInt(sArr[0]);
				// Target ID
				edges[i * 3 + 1] = Integer.parseInt(sArr[1]);
				// Costs
				edges[i * 3 + 2] = Integer.parseInt(sArr[2]);
			}

			System.out.println("Finished reading. Time elapsed: " + (double) (System.nanoTime() - time) / 1000000000f);

		} catch (Exception e) {
			System.out.println("Input failed");
		}

	}

	/**
	 * Reads data of given path and saves them into respective arrays. This method is faster due multiple threads.
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
			for (int i = 0; i < numOfNodes; i++) {
				indices[i] = i;
			}
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
		this.coordinates = nodes.getCoordinates();

		System.out.println("Finished reading. Time elapsed: " + (double) (System.nanoTime() - time) / 1000000000f);

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
}