package de.propro.backend;

import java.util.ArrayList;
import java.util.Scanner;

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
		// main.testHeap();
		//main.initGraph("bw.fmi");
		//main.startToEnd(137, 138, main.reader);
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
				int x = Integer.parseInt(main.scan.nextLine());
				int y = Integer.parseInt(main.scan.nextLine());
				main.startToEnd(x, y, main.reader);
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
		long time=System.currentTimeMillis();
		Dijkstra dijkstra = new Dijkstra(reader);
		DijktraResult output = dijkstra.startToEnd(start, end);

		for (Integer i : output.path) {
			System.out.println(i);
		}

		System.out.println("The path is " + output.length + "m long");
		System.out.println("The path is " + output.path.size() + " nodes long");
		System.out.println("Time needed: "+(System.currentTimeMillis()-time)/1000+" Sekunden");
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

	public void testHeap() {
		MinHeap min = new MinHeap(4);
		min.push(1, 7);
		min.push(2, 5);
		min.push(3, 2);
		min.push(4, 8);

		min.decreaseValue(0, 3);

		while (!min.isEmpty()) {
			System.out.println(min.pop());
		}
		System.exit(0);
	}

}