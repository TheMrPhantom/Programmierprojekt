package de.propro.backend.reading;

import java.io.File;

/**
 * 
 * Abstract class for the reader classes NodeReader and EdgeReader
 *
 */
public abstract class CustomReader implements Runnable {

	protected final File file;
	protected boolean isFinished;
	protected boolean isSuccessfull;
	
	/**
	 * 
	 * Initializes the attributes
	 * 
	 * @param file The graph file
	 */
	public CustomReader(File file) {
		this.file = file;
		this.isFinished = false;
		this.isSuccessfull=false;

		if (this.file == null || !this.file.isFile()) {
			System.err.println("File does not exist or is a directory");
		} else {
			System.out.println("File found: " + this.file.getAbsolutePath());
			System.out.println("Graph reader initialized");
			System.out.println();
		}
	}

	/**
	 * Abstract method for the reading thread
	 */
	@Override
	public abstract void run();

}
