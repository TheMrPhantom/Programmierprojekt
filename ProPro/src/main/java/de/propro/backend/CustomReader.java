package de.propro.backend;

import java.io.File;

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
