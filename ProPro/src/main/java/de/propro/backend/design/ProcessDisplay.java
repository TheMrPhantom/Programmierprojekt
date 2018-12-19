package de.propro.backend.design;

public class ProcessDisplay extends Thread {

	private String textForDots;
	private int dots;

	private String printedOut;

	private boolean stop;

	/**
	 * 
	 * Initializes the ProcessDisplay
	 * 
	 * @param textForDots What text should be displayed before the dots
	 */
	public ProcessDisplay(String textForDots) {
		this.textForDots = textForDots;
		printedOut = textForDots;
		this.stop = false;
		System.out.print(textForDots);
	}

	/**
	 * Prints the process bar on the console continuously
	 */
	@Override
	public void run() {
		while (!stop) {
			dots++;
			dots %= 6;
			String temp = textForDots;
			for (int i = 0; i < dots; i++) {
				temp += ".";
			}
			printOut(temp);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}
		}
		for (int i = 0; i < printedOut.length(); i++) {
			System.out.print("\b");
		}
	}

	/**
	 * 
	 * Prints out the process bar
	 * 
	 * @param toBePrinted What text should be displayed before the dots
	 */
	private void printOut(String toBePrinted) {
		for (int i = 0; i < printedOut.length(); i++) {
			System.out.print("\b");
		}
		for (int i = 0; i < printedOut.length(); i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < printedOut.length(); i++) {
			System.out.print("\b");
		}
		printedOut = toBePrinted;
		System.out.print(printedOut);
	}

	/**
	 * Stops the thread from printing on the console. It will remove the remaining
	 * text.
	 */
	public void stopThread() {
		stop = true;
	}
}
