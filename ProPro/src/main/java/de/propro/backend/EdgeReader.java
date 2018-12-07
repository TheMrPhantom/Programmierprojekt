package de.propro.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EdgeReader extends CustomReader {

	private int[] edges;
	private final int numOfEdges;
	private final int numOfNodes;
	private int[] indices;

	public EdgeReader(File file, int numOfNodes, int numOfEdges) {
		super(file);
		this.edges = new int[3 * numOfEdges];
		this.numOfEdges = numOfEdges;
		this.numOfNodes = numOfNodes;
		this.indices = new int[numOfNodes];
	}

	/**
	 * Reads the edges and stores it in the array
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
			int index = -1;
			int lastID = -1;
			int sID = 0;
			for (int i = 0; i < numOfEdges; i++) {
				sArr = br.readLine().split(" ");
				// Source ID
				sID = Integer.parseInt(sArr[0]);
				edges[i * 3 + 0] = sID;
				// index++;
				if (lastID != sID) {
					indices[sID] = i*3;
				}
				lastID = sID;
				// Target ID
				edges[i * 3 + 1] = Integer.parseInt(sArr[1]);
				// Costs
				edges[i * 3 + 2] = Integer.parseInt(sArr[2]);
			}
			System.out.println("Finished reading Edges");
			isFinished = true;
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
		} catch (IOException e) {
			System.out.println("Error while reading file");
		}
	}

	/**
	 * 
	 * Return the read edges
	 * 
	 * @return The edges as array
	 * @throws IllegalStateException If the thread has not finished reading the file
	 */
	public int[] getEdges() {
		if (!isFinished) {
			throw new IllegalStateException("The thread has not finished it's work or the reading failed!");
		}
		return edges;
	}

	public int[] getIndices() {
		if (!isFinished) {
			throw new IllegalStateException("The thread has not finished it's work or the reading failed!");
		}
		return indices;
	}

}
