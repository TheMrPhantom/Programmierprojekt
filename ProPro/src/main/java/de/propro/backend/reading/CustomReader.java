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

	public CustomReader(File file) {
		this.file = file;
		this.isFinished = false;
	}

	@Override
	public abstract void run();

}
