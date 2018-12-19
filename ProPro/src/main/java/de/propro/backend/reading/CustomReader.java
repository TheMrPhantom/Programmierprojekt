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

	/**
	 * 
	 * Initializes the attributes
	 * 
	 * @param file The graph file
	 */
	public CustomReader(File file) {
		this.file = file;
		this.isFinished = false;
	}

	/**
	 * Abstract method for the reading thread
	 */
	@Override
	public abstract void run();

}
