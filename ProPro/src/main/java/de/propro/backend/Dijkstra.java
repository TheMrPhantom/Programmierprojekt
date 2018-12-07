package de.propro.backend;

import java.util.ArrayList;
import java.util.Stack;

import org.jgrapht.util.FibonacciHeapNode;

public class Dijkstra {

	private MinHeap priorityQueue;
	private GraphReader reader;

	int[] lastNode;
	int[] nodeCost;

	public Dijkstra(GraphReader reader) {
		System.out.println("Initialising Dijkstra");
		this.reader = reader;
		int nodeCount = reader.getIndices().length;
		priorityQueue = new MinHeap(nodeCount);
		lastNode = new int[nodeCount];
		nodeCost = new int[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			priorityQueue.push(i, Integer.MAX_VALUE);
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
		int popedNode = start;
		nodeCost[popedNode] = 0;

		int newNode;
		int costNodeNewEdge;
		int costNodeOld;
		int newEdge;
		int[] indices = reader.getIndices();
		int[] edges = reader.getEdges();
		int cost;
		FibonacciHeapNode<Integer>[] nodeCost = priorityQueue.nodes;
		while (popedNode != end) {

			/* Get the index in the edge list for the node we look at */
			int init = indices[popedNode];

			/* If the node ist not reachable the index is -1 */
			if (init == -1) {
				/* Then skip */
				popedNode = priorityQueue.pop();

				continue;
			}
			try {
				/* For all edges of the active node */
				for (int i = init; edges[i] == popedNode; i += 3) {
					newNode = edges[i + 1];
					newEdge = edges[i + 2];
					cost = (int) nodeCost[popedNode].getKey();
					costNodeNewEdge = newEdge + cost;
					costNodeOld = (int) nodeCost[newNode].getKey();

					/* If the edge is better take it */
					if (costNodeNewEdge < costNodeOld) {
						// nodeCost[newNode] = newEdge + nodeCost[popedNode];
						priorityQueue.decreaseValue(newNode, newEdge + cost);
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

		System.out.println("Push " + (priorityQueue.push / 1000) / 1000);
		System.out.println("Pop " + (priorityQueue.pop / 1000) / 1000);
		System.out.println("DV " + (priorityQueue.decreaseValue / 1000) / 1000);

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
		result.length = (int) nodeCost[end].getKey();
		return result;
	}

}
