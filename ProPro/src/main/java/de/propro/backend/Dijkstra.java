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
			priorityQueue.push(i, Double.POSITIVE_INFINITY);
			lastNode[i] = Integer.MAX_VALUE;
			nodeCost[i] = Integer.MAX_VALUE;
		}
		System.out.println("Finished initialising Dijkstra");
	}

	public ArrayList<Integer> startToEnd(int start, int end) {
		System.out.println("Starting start to end");
		priorityQueue.decreaseValue(start, 0);
		int popedNode = start;
		nodeCost[popedNode] = 0;

		while (popedNode != end) {
			int init = reader.getIndices()[popedNode];
			if (init == -1) {
				popedNode = priorityQueue.pop();
				continue;
			}
			for (int i = init; reader.getEdges()[i] == popedNode; i += 3) {
				if (reader.getEdges()[i + 2] + nodeCost[popedNode] < nodeCost[reader.getEdges()[i + 1]]) {
					nodeCost[reader.getEdges()[i + 1]] = reader.getEdges()[i + 2] + nodeCost[popedNode];
					priorityQueue.decreaseValue(reader.getEdges()[i + 1],
							reader.getEdges()[i + 2] + nodeCost[popedNode]);
					lastNode[reader.getEdges()[i + 1]] = popedNode;
				}

			}

			popedNode = priorityQueue.pop();
		}

		System.out.println("Finished start to end Dijkstra");
		System.out.println("Starting to collect path");
		Stack<Integer> rawPath = new Stack<Integer>();
		int temp = end;
		int counter = 0;
		rawPath.add(temp);
		while (temp != start) {
			temp = lastNode[temp];
			rawPath.add(temp);
			counter++;
			System.out.println(counter + " " + temp);
		}

		System.out.println(counter);

		ArrayList<Integer> outputPath = new ArrayList<Integer>();
		while (!rawPath.isEmpty()) {
			outputPath.add(rawPath.pop());
		}
		System.out.println("Finished collecting path");
		return outputPath;
	}

}
