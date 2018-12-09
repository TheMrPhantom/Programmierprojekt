package de.propro.backend;

import java.util.ArrayList;
import java.util.Stack;

import org.jgrapht.util.FibonacciHeapNode;

public class Dijkstra {

	private CustomMinHeap priorityQueue;
	private GraphReader reader;

	private int[] lastNode;
	private int[] nodeCost;

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
		int newEdge;
		int[] indices = reader.getIndices();
		int[] edges = reader.getEdges();
		int costForViewedNode;
int counter1=0;
		while (popedNode != end) {
counter1++;
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
					newEdge = edges[i + 2];

					costOldPlusEdge = newEdge + costForViewedNode;
					costOnlyNewNode = this.nodeCost[newNode];

					/* If the edge is better take it */
					if (costOldPlusEdge < costOnlyNewNode) {
						priorityQueue.decreaseValue(newNode, costOldPlusEdge);
						nodeCost[newNode] = costOldPlusEdge;
						if (costOldPlusEdge < 0) {
							throw new IllegalStateException(counter1+"");
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

		System.out.println("Finished start to end Dijkstra");
		System.out.println("Starting to collect path");

		Stack<Integer> rawPath = new Stack<Integer>();
		int temp = end;
		int counter = 0;
		rawPath.add(temp);
		try {
			while (temp != start) {
				temp = lastNode[temp];
				rawPath.add(temp);
				counter++;
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

}
