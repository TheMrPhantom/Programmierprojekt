package de.propro.backend;

import java.io.File;

class Main {

	private GraphReader reader;

	public static void main(String[] args) {
		// germany.fmi
		// stgtregbz.fmi
		// bw.fmi

		Main main = new Main();

		args = new String[1];
		args[0] = "-h";
		if (args.length == 0) {
			System.err.print("Invalid amount of arguments!");
		} else {
			if (args[0].equals("-h")) {
				System.out.println("Usage:");
				System.out.println("\tParam -h:\tShows how to start the programm");
				System.out.println("\tParam -f:\tSpecify the file to load");
				System.out.println("\t\t\tExample: java -jar graphProgramm -f germany.fmi");
			} else if (args[0].equals("-f")) {
				if (args.length > 1) {
					main.initGraph(args[1]);
				} else {
					main.printIllegalArgumentsMessage();
				}
			} else {
				main.printIllegalArgumentsMessage();
			}
		}

	}

	private void printIllegalArgumentsMessage() {
		System.err.print("Invalid arguments");
		System.exit(1);
	}

	private void initGraph(String filename) {
		this.reader = new GraphReader(new File(filename));
		this.reader.readDataFast();
	}
}