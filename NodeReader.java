package de.test.uni_stuttgart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NodeReader extends CustomReader {

	private double[] coordinates;
	private final int numOfNodes;

	public NodeReader(File file, int numOfNodes) {
		super(file);
		this.coordinates = new double[2 * numOfNodes];
		this.numOfNodes = numOfNodes;
	}

	@Override
	public void run() {
		System.out.println("Started reading Nodes");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			for (int i = 0; i < 7; i++) {
				br.readLine();
			}
			String[] sArr;
			for (int i = 0; i < numOfNodes; i++) {
				sArr = br.readLine().split(" ");
				// latitude
				coordinates[i * 2 + 0] = Double.parseDouble(sArr[2]);
				// longitude
				coordinates[i * 2 + 1] = Double.parseDouble(sArr[3]);
			}
			System.out.println("Finished reading Nodes");
			isFinished = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public double[] getCoordinates() {
		if (!isFinished) {
			throw new IllegalStateException("The thread has not finished it's work or the reading failed!");
		}
		return coordinates;
	}

}
