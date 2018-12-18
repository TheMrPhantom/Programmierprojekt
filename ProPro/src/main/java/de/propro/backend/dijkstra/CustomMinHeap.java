package de.propro.backend.dijkstra;

import java.util.Arrays;

public class CustomMinHeap {

	private int[] indices;
	private int[] node;
	private int[] values;

	private int size;

	public CustomMinHeap(int capacity) {
		indices = new int[capacity];
		values = new int[capacity];
		node = new int[capacity];

		size = capacity;

		for (int i = 0; i < capacity; i++) {
			indices[i] = i;
			values[i] = Integer.MAX_VALUE;

			node[i] = i;

		}

	}

	/**
	 * 
	 * Removes the node with the smallest cost
	 * 
	 * @return The index of the node with the smallest cost
	 */
	public int pop() {

		if (size <= 0)
			throw new IllegalStateException("There are no more elements to pop");
		if (size == 1) {
			size--;
			return indices[0];
		}

		// Store the minimum value, and remove it from heap
		int root = indices[0];

		node[indices[0]] = -1;
		node[indices[size - 1]] = 0;

		indices[0] = indices[size - 1];
		values[0] = values[size - 1];

		size--;
		heapify(0);

		return root;

	}

	/**
	 * 
	 * Decreases the cost of a node
	 * 
	 * @param nodeIdx The node index of which the cost should be removed
	 * @param value   The new cost
	 * @throws IllegalArgumentException If the new cost is greater than the old
	 */
	public void decreaseValue(int nodeIdx, int value) {

		nodeIdx = node[nodeIdx];
		values[nodeIdx] = value;

		int parentNodeIndex;

		while (nodeIdx != 0 && values[parent(nodeIdx)] > values[nodeIdx]) {
			parentNodeIndex = parent(nodeIdx);

			node[indices[nodeIdx]] = parentNodeIndex;
			node[indices[parentNodeIndex]] = nodeIdx;

			swapIndicesElements(nodeIdx, parentNodeIndex);
			swapValuesElements(nodeIdx, parentNodeIndex);

			nodeIdx = parentNodeIndex;
		}

	}

	/**
	 * 
	 * Checks if the heap is empty
	 * 
	 * @return True if the heap is empty, false if not
	 */
	public boolean isEmpty() {

		return size <= 0;
	}

	/**
	 * 
	 * Return the number of elements in the heap
	 * 
	 * @return The size
	 */
	public int getSize() {

		return size;
	}

	private void heapify(int i) {
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < size && values[l] < values[i])
			smallest = l;
		if (r < size && values[r] < values[smallest])
			smallest = r;
		if (smallest != i) {

			node[indices[i]] = smallest;
			node[indices[smallest]] = i;

			swapIndicesElements(i, smallest);
			swapValuesElements(i, smallest);

			heapify(smallest);
		}
	}

	private void swapIndicesElements(int a, int b) {
		int valueTemp = indices[a];
		indices[a] = indices[b];
		indices[b] = valueTemp;
	}

	private void swapValuesElements(int a, int b) {
		int valueTemp = values[a];
		values[a] = values[b];
		values[b] = valueTemp;
	}

	private static int parent(int i) {
		return (i - 1) / 2;
	}

	private static int left(int i) {
		return (2 * i + 1);
	}

	private static int right(int i) {
		return (2 * i + 2);
	}

	public int[] getIndicesCopy() {
		return Arrays.copyOf(indices, indices.length);
	}

	public int[] getCostCopy() {
		return Arrays.copyOf(values, values.length);
	}
}
