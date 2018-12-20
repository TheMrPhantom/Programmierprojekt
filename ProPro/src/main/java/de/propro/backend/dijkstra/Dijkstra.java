package de.propro.backend.dijkstra;

import java.util.ArrayList;
import java.util.Stack;

import de.propro.backend.Main;
import de.propro.backend.design.ProcessDisplay;
import de.propro.backend.reading.GraphReader;

public class Dijkstra {

	private CustomMinHeap priorityQueue;
	private GraphReader reader;

	private int[] lastNode;
	private int[] nodeCost;

	/**
	 * 
	 * Initializing all attributes needed for the dijkstra
	 * 
	 * @param reader The inputed graph
	 */
	public Dijkstra(GraphReader reader) {
		System.out.println("Initialising Dijkstra");
		this.reader = reader;
		int nodeCount = reader.getIndices().length;
		priorityQueue = new CustomMinHeap(nodeCount);
		lastNode = new int[nodeCount];
		nodeCost = new int[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			lastNode[i] = Integer.MAX_VALUE;
			nodeCost[i] = Integer.MAX_VALUE;
		}
		System.out.println("Finished initialising Dijkstra");
	}

	/**
	 * 
	 * Executes the dijkstra from a start node to an end node
	 * 
	 * @param start The start node index
	 * @param end   The end node index
	 * @return The result of the dijkstra (length and path)
	 */
	public DijktraResult startToEnd(int start, int end) {
		System.out.println("Starting start to end");

		try {
			priorityQueue.decreaseValue(start, 0);
		} catch (ArrayIndexOutOfBoundsException e) {

			System.err.println("Startknoten existiert nicht");

			return null;
		}
		int popedNode = priorityQueue.pop();
		nodeCost[popedNode] = 0;

		int newNode;
		int costOldPlusEdge;
		int costOnlyNewNode;
		int[] indices = reader.getIndices();
		int[] edges = reader.getEdges();
		int costForViewedNode;
		long time = System.currentTimeMillis();
		while (popedNode != end) {
			/* Get the index in the edge list for the node we look at */
			int init = indices[popedNode];

			/* If the node is not reachable the index is -1 */
			if (init == -1) {
				/* Then skip */
				popedNode = priorityQueue.pop();

				continue;
			}
			try {
				costForViewedNode = this.nodeCost[popedNode];
				/* For all edges of the active node */
				for (int i = init; edges[i] == popedNode; i += 3) {
					newNode = edges[i + 1];

					costOldPlusEdge = edges[i + 2] + costForViewedNode;
					costOnlyNewNode = this.nodeCost[newNode];

					/* If the edge is better take it */
					if (costOldPlusEdge < costOnlyNewNode) {
						priorityQueue.decreaseValue(newNode, costOldPlusEdge);
						nodeCost[newNode] = costOldPlusEdge;
						if (costOldPlusEdge < 0) {
							throw new IllegalStateException("Node is not reachable");
						}
						lastNode[newNode] = popedNode;

					}

				}

			} catch (ArrayIndexOutOfBoundsException e) {

			} catch (IllegalArgumentException e) {

			}

			/* If there are more node repeat as long as we are not at the start */
			if (!priorityQueue.isEmpty()) {

				popedNode = priorityQueue.pop();

			} else {
				popedNode = end;
			}
		}

		System.out.printf("Time needed: %.2f Seconds\n", (System.currentTimeMillis() - time) / 1000.0);
		System.out.println("Finished start to end Dijkstra");
		System.out.println("Starting to collect path");

		Stack<Integer> rawPath = new Stack<Integer>();
		int temp = end;
		rawPath.add(temp);
		try {
			while (temp != start) {
				temp = lastNode[temp];
				rawPath.add(temp);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Der Endknoten existiert nicht");
			return null;
		}
		ArrayList<Integer> outputPath = new ArrayList<Integer>();
		while (!rawPath.isEmpty()) {
			outputPath.add(rawPath.pop());
		}
		System.out.println("Finished collecting path");
		DijktraResult result = new DijktraResult();
		result.path = outputPath;
		result.length = nodeCost[end];
		return result;
	}

	/**
	 * Implementation of an one-to-all Dijkstra
	 * 
	 * @param start The start node index
	 * @param main  The main object for reading input
	 */
	public void oneToAll(int start, Main main) {
		System.out.println("Starting start to end");
		
		long time = System.currentTimeMillis();
		try {
			priorityQueue.decreaseValue(start, 0);
		} catch (ArrayIndexOutOfBoundsException e) {
			
			System.err.println("Startknoten existiert nicht");

			return;
		}
		int popedNode = priorityQueue.pop();
		nodeCost[popedNode] = 0;

		int newNode;
		int costOldPlusEdge;
		int costOnlyNewNode;

		int[] indices = reader.getIndices();
		int[] edges = reader.getEdges();
		int costForViewedNode;
		
		/*Start of the dijkstra*/
		while (!priorityQueue.isEmpty()) {
			/* Get the index in the edge list for the node we look at */
			int init = indices[popedNode];

			/* If the node is not reachable the index is -1 */
			if (init == -1) {
				/* Then skip */
				popedNode = priorityQueue.pop();

				continue;
			}
			try {
				costForViewedNode = this.nodeCost[popedNode];
				/* For all edges of the active node */
				for (int i = init; edges[i] == popedNode; i += 3) {
					newNode = edges[i + 1];

					costOldPlusEdge = edges[i + 2] + costForViewedNode;
					costOnlyNewNode = this.nodeCost[newNode];

					/* If the edge is better take it */
					if (costOldPlusEdge < costOnlyNewNode) {
						if (costOldPlusEdge >= 0) {
							nodeCost[newNode] = costOldPlusEdge;
							priorityQueue.decreaseValue(newNode, costOldPlusEdge);
						}
						lastNode[newNode] = popedNode;
					}
				}

			} catch (ArrayIndexOutOfBoundsException e) {

			} catch (IllegalArgumentException e) {

			}

			/* If there are more node repeat as long as we are not at the start */
			if (!priorityQueue.isEmpty()) {

				popedNode = priorityQueue.pop();

			}
		}

		/*End of the dijkstra*/
		
		long time2 = System.currentTimeMillis();


		System.out.println("Finished one to all Dijkstra");

		System.out.println((time2 - time) / 1000 + "s needed");
		do {
			try {
				int node = Integer.parseInt(main.readLine("Cost to what node?"));
				System.out.println(nodeCost[node]);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("Node does not exist");
			}
		} while (main.readLine("New request? (y/n)").equals("y"));

	}

	/**
	 * Resets the dijkstra object so it can be used for another dijkstra algorithm.
	 * Must be called before every dijkstra if dijkstra was executed once.
	 */
	public void reset() {
		int nodeCount = reader.getIndices().length;
		priorityQueue = new CustomMinHeap(nodeCount);
		lastNode = new int[nodeCount];
		nodeCost = new int[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			lastNode[i] = Integer.MAX_VALUE;
			nodeCost[i] = Integer.MAX_VALUE;
		}
		System.out.println("Finished reseting Dijkstra");
	}

}
