package de.propro.backend.reading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class using a Buffered Reader to read over the edges of given nodes and
 * storing these in an array. Additional basic functions like returning the read
 * nodes and their indices for later processing
 *
 */
public class EdgeReader extends CustomReader {

	private int[] edges;
	private final int numOfEdges;
	private final int numOfNodes;
	private int[] indices;

	/**
	 * 
	 * Initializes the edge reader with the given file
	 * 
	 * @param file       The file of the graph
	 * @param numOfNodes The number of Node which should be skipped
	 * @param numOfEdges The number of edges which should be read
	 */
	public EdgeReader(File file, int numOfNodes, int numOfEdges) {
		super(file);
		this.edges = new int[3 * numOfEdges];
		this.numOfEdges = numOfEdges;
		this.numOfNodes = numOfNodes;
		this.indices = new int[numOfNodes];
	}

	/**
	 * Reads the edges and stores them in an array
	 */
	@Override
	public void run() {
		System.out.println("Started reading Edges");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			for (int i = 0; i < numOfNodes; i++) {
				indices[i] = -1;
			}

			String[] sArr;
			for (int i = 0; i < numOfNodes + 7; i++) {
				br.readLine();
			}

			int lastID = -1;
			int sID = 0;
			for (int i = 0; i < numOfEdges; i++) {
				sArr = br.readLine().split(" ");
				// Source ID
				sID = Integer.parseInt(sArr[0]);
				edges[i * 3 + 0] = sID;
				// index++;
				if (lastID != sID) {
					indices[sID] = i * 3;
				}
				lastID = sID;
				// Target ID
				edges[i * 3 + 1] = Integer.parseInt(sArr[1]);
				// Costs
				edges[i * 3 + 2] = Integer.parseInt(sArr[2]);
			}
			System.out.println("Finished reading Edges");
			isFinished = true;
			isSuccessfull=true;
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
		} catch (IOException | NullPointerException e) {
			System.out.println("Error while reading file");
		}
	}

	/**
	 * 
	 * Return the read edges
	 * 
	 * @return The edges as an array
	 * @throws IllegalStateException If the thread has not finished reading the file
	 */
	public int[] getEdges() {
		if (!isFinished) {
			throw new IllegalStateException("The thread has not finished it's work or the reading failed!");
		}
		return edges;
	}

	/**
	 * Return the indices of read edges
	 * 
	 * @return The indices as an array
	 */
	public int[] getIndices() {
		if (!isFinished) {
			throw new IllegalStateException("The thread has not finished it's work or the reading failed!");
		}
		return indices;
	}

}
