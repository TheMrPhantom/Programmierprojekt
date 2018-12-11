package de.propro.backend;

import java.util.Scanner;
import de.propro.backend.dijkstra.Dijkstra;
import de.propro.backend.dijkstra.DijktraResult;
import de.propro.backend.reading.GraphReader;

public class Main {

	public GraphReader reader;
	private Scanner scan;

	public Main() {
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		// germany.fmi
		// stgtregbz.fmi
		// bw.fmi

		Main main = new Main();

		if (args.length == 0) {
			printHelp();
			return;
		} else {
			if (args[0].equals("-h")) {
				printHelp();
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

		int input = 0;

		while (input != 3) {
			try {
				input = main.printMainMenu();
			} catch (NumberFormatException ex) {
				input = 4;
			}
			switch (input) {
			case 0:
				try {
					int x = Integer.parseInt(main.readLine("Type the start node index"));
					int y = Integer.parseInt(main.readLine("Type the end node index"));

					main.startToEnd(x, y, main.reader);
				} catch (IllegalStateException e) {
					System.err.println(e.getMessage());
				} catch (NumberFormatException e) {
					System.err.println("Input got wrong format\n"
							+ "E.g. Not a number or out of the allowed range ([0, nodeCount-1])");
				}
				break;
			case 1:

				break;
			case 2:

				break;
			case 3:
				System.out.println("Programm exited");
				break;
			default:
				System.err.println("Invalid option");
				break;
			}
		}

	}

	public DijktraResult startToEnd(int start, int end, GraphReader reader) {
		long time = System.currentTimeMillis();
		Dijkstra dijkstra = new Dijkstra(reader);
		DijktraResult output = dijkstra.startToEnd(start, end);

		int counter = 0;
		for (Integer i : output.path) {
			counter++;
			if (counter < 10) {
				System.out.print(i.toString() + "->");
			} else {
				counter = 0;
				System.out.println(i.toString() + "->");
			}
		}
		System.out.println();
		System.out.println("The path is " + output.length + "m long");
		System.out.println("The path is " + output.path.size() + " nodes long");
		System.out.println("Time needed: " + (System.currentTimeMillis() - time) / 1000 + " Sekunden");
		return output;
	}

	private static void printHelp() {
		System.out.println("Usage:");
		System.out.println("\tParam -h:\tShows how to start the programm");
		System.out.println("\tParam -f:\tSpecify the file to load");
		System.out.println("\t\t\tExample: java -jar ProPro.jar -f germany.fmi");

	}

	private void printIllegalArgumentsMessage() {
		System.err.println("Invalid arguments");
		System.exit(1);
	}

	public void initGraph(String filename) {
		this.reader = new GraphReader(filename);
		this.reader.readDataFast();
	}

	private int printMainMenu() throws NumberFormatException {
		System.out.println("Ready for input...");
		System.out.println("\t(0) Start -> End");
		System.out.println("\t(1) Node -> All");
		System.out.println("\t(2) Process Start -> End file");
		System.out.println("\t(3) Exit");

		int output = Integer.parseInt(scan.nextLine());
		return output;
	}

	private String readLine(String message) {
		System.out.println(message);
		return scan.nextLine();
	}

}