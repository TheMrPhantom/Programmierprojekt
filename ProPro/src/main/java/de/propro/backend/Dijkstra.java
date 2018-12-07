package de.propro.backend;

import java.util.ArrayList;
import java.util.Stack;

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
		while (popedNode != end) {
			int init = reader.getIndices()[popedNode];
			if (init == -1) {
				popedNode = priorityQueue.pop();
				continue;
			}
			try {

				for (int i = init; reader.getEdges()[i] == popedNode; i += 3) {
					newNode = reader.getEdges()[i + 1];
					newEdge = reader.getEdges()[i + 2];
					costNodeNewEdge = newEdge + nodeCost[popedNode];
					costNodeOld = nodeCost[newNode];

					if (costNodeNewEdge < costNodeOld) {
						nodeCost[newNode] = newEdge + nodeCost[popedNode];
						priorityQueue.decreaseValue(newNode, newEdge + nodeCost[popedNode]);
						lastNode[newNode] = popedNode;
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {

			} catch (IllegalArgumentException e) {

			}

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
