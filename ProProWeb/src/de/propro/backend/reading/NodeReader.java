package de.propro.backend.reading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * Class using a Buffered Reader to read over the coordinates of given nodes and
 * storing these in an array. Additional basic functions like returning the read
 * coordinates for later processing
 *
 */
public class NodeReader extends CustomReader {

	private double[] coordinates;
	private final int numOfNodes;

	public NodeReader(File file, int numOfNodes) {
		super(file);
		this.coordinates = new double[2 * numOfNodes];
		this.numOfNodes = numOfNodes;
	}

	/**
	 * Reads the coordinates and stores it in the array
	 */
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
			isSuccessfull=true;
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
		} catch (IOException | NullPointerException e) {
			System.out.println("Error while reading file");
		}

	}

	/**
	 * 
	 * Return the read coordinates
	 * 
	 * @return The coordinates as array
	 * @throws IllegalStateException If the thread has not finished reading the file
	 */
	public double[] getCoordinates() {
		if (!isFinished) {
			throw new IllegalStateException("The thread has not finished it's work or the reading failed!");
		}
		return coordinates;
	}

}
