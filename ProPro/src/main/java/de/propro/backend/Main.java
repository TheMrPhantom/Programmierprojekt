package de.propro.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import de.propro.backend.dijkstra.Dijkstra;
import de.propro.backend.dijkstra.DijktraResult;
import de.propro.backend.reading.GraphReader;

/**
 * 
 * @author Elif Dilara Ayguen
 * @author Sven Dyhr
 * @author Justin Schiel
 *
 *         Class holding the entry point method of the program
 */
public class Main {

	public GraphReader reader;
	private Scanner scan;

	/**
	 * Initializes the scanner
	 */
	public Main() {
		scan = new Scanner(System.in);
	}

	/**
	 * 
	 * In this method the whole programm is executed
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {

		Main main = new Main();
		// main.initGraph("bw.fmi");

		if (args.length == 0) {
			printHelp();
			return;
		} else {
			if (args[0].equals("-h")) {
				printHelp();
				return;
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

		while (input != 4) {
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
				Dijkstra dijkstra = new Dijkstra(main.reader);
				dijkstra.oneToAll(Integer.parseInt(main.readLine("Enter the id of the start node")), main);
				break;
			case 2:
				try {
					main.queue();
				} catch (IOException e) {
					if (e instanceof FileNotFoundException)
						System.err.println("File does not exist");
					else
						System.err.println("Fehler beim Schreiben/Lesen");
				} catch (ArrayIndexOutOfBoundsException e) {
					System.err.println("File is not a valid .que file");

				}
				break;
			case 3:
				try {
					double startX = Double.parseDouble(main.readLine("Type the X-coordinate of the starting location"));
					double startY = Double.parseDouble(main.readLine("Type the Y-coordinate of the starting location"));
					int startNode = main.reader.findNearestNode(startX, startY);

					double endX = Double.parseDouble(main.readLine("Type the X-coordinate of the ending location"));
					double endY = Double.parseDouble(main.readLine("Type the Y-coordinate of the ending location"));
					int endNode = main.reader.findNearestNode(endX, endY);

					main.startToEnd(startNode, endNode, main.reader);
				} catch (IllegalStateException e) {
					System.err.println(e.getMessage());
				} catch (NumberFormatException e) {
					System.err.println("Input got wrong format\n" + "E.g. Not a number or out of the allowed range");
				}
				break;
			case 4:
				System.out.println("Programm exited");
				break;
			default:
				System.err.println("Invalid option");
				break;
			}
		}

	}

	/**
	 * 
	 * Executes a shortest path request with dijkstra algorithm
	 * 
	 * @param start  The start node id
	 * @param end    The end node id
	 * @param reader The reader representing the graph on which the dijkstra should
	 *               be executed
	 * @return All informations about the executed dijkstra
	 */
	public DijktraResult startToEnd(int start, int end, GraphReader reader) {

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
		return output;
	}

	/**
	 * 
	 * Executes many dijkstra algorithms based on a *.que file which path is asked
	 * from the user
	 * 
	 * @throws IOException                    If the *.que file does not exist or
	 *                                        reading errors happen
	 * @throws ArrayIndexOutOfBoundsException If the *.que file is malformed
	 */
	private void queue() throws IOException, ArrayIndexOutOfBoundsException {
		String input = readLine("Type the path of the .que file (with file ending)");
		String output = readLine("Type the name of the .sol file (without file ending)");
		FileReader fr = new FileReader(input);
		BufferedReader buffi = new BufferedReader(fr);
		ArrayList<String> starts = new ArrayList<String>();
		ArrayList<String> ends = new ArrayList<String>();

		String fileInput;
		while ((fileInput = buffi.readLine()) != null) {
			starts.add(fileInput.split(" ")[0]);
			ends.add(fileInput.split(" ")[1]);
		}

		buffi.close();

		ArrayList<String> outputs = new ArrayList<String>();
		Dijkstra d = new Dijkstra(reader);

		for (int i = 0; i < starts.size(); i++) {
			int s = Integer.parseInt(starts.get(i));
			int e = Integer.parseInt(ends.get(i));

			String temp = d.startToEnd(s, e).length + "";
			outputs.add(temp);
			System.out.println("Length: " + temp);
			d.reset();
			System.out.println(i + "/" + starts.size());
			System.out.println();
		}

		FileWriter writer = new FileWriter(output + ".sol");
		BufferedWriter buffiWriter = new BufferedWriter(writer);

		for (String out : outputs) {
			buffiWriter.write(out + "\n");
		}
		buffiWriter.close();
	}

	/**
	 * Prints the help menu so the user know how to use the programm
	 */
	private static void printHelp() {
		System.out.println("Usage:");
		System.out.println("\tParam -h:\tShows how to start the programm");
		System.out.println("\tParam -f:\tSpecify the file to load");
		System.out.println("\t\t\tExample: java -jar ProPro.jar -f germany.fmi");
	}

	/**
	 * Signals the user that he inputed wrong arguments and exits with error code 1
	 */
	private void printIllegalArgumentsMessage() {
		System.err.println("Invalid arguments");
		System.exit(1);
	}

	/**
	 * 
	 * Initializes the graph with the given graph file as path
	 * 
	 * @param filename The path of the graph the read (Path must include file
	 *                 extension)
	 */
	public void initGraph(String filename) {
		this.reader = new GraphReader(filename);
		this.reader.readDataFast();
	}

	/**
	 * 
	 * Prints the main menu so the user knows what to choose from
	 * 
	 * @return The index of the menu point which was selected
	 * @throws NumberFormatException If the user did not type a number
	 */
	private int printMainMenu() throws NumberFormatException {
		System.out.println("Ready for input...");
		System.out.println("\t(0) Start -> End");
		System.out.println("\t(1) Node -> All");
		System.out.println("\t(2) Process Start -> End file");
		System.out.println("\t(3) Location -> Location");
		System.out.println("\t(4) Exit");

		int output = Integer.parseInt(scan.nextLine());
		return output;
	}

	/**
	 * 
	 * Reads a from the reader of this object
	 * 
	 * @param message A question which should be send to the user before waiting for
	 *                input
	 * @return The inputed line
	 */
	public String readLine(String message) {
		System.out.println(message);
		return scan.nextLine();
	}
}