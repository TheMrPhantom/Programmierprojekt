package de.propro.backend.design;

public class ProcessDisplay extends Thread {

	private String textForDots;
	private int dots;

	private String printedOut;

	private boolean stop;

	public ProcessDisplay(String textForDots) {
		this.textForDots = textForDots;
		printedOut = textForDots;
		this.stop = false;
		System.out.print(textForDots);
	}

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

	public void stopThread() {
		stop = true;
	}
}
