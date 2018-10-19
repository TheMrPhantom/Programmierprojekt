package de.test.uni_stuttgart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EdgeReader extends CustomReader {

	private int[] edges;
	private final int numOfEdges;
	private final int numOfNodes;

	public EdgeReader(File file, int numOfNodes, int numOfEdges) {
		super(file);
		this.edges = new int[3 * numOfEdges];
		this.numOfEdges = numOfEdges;
		this.numOfNodes = numOfNodes;
	}

	@Override
	public void run() {
		System.out.println("Started reading Edges");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String[] sArr;
			for (int i = 0; i < numOfNodes + 7; i++) {
				br.readLine();
			}
			System.out.println("Finished skipping Edges");
			for (int i = 0; i < numOfEdges; i++) {
				sArr = br.readLine().split(" ");
				// Source ID
				edges[i * 3 + 0] = Integer.parseInt(sArr[0]);
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

	public int[] getEdges() {
		if (!isFinished) {
			throw new IllegalStateException("The thread has not finished it's work or the reading failed!");
		}
		return edges;
	}

}
