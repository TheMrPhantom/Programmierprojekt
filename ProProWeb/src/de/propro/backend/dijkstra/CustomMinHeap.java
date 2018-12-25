package de.propro.backend.dijkstra;

public class CustomMinHeap {

	private int[] indices;
	private int[] node;
	private int[] values;

	private int size;

	private int swapIndices;
	private int swapValues;

	/**
	 * 
	 * Initializes the min heap for any dijkstra
	 * 
	 * @param capacity The maximum amount of elements which are contained in the min
	 *                 heap
	 */
	public CustomMinHeap(int capacity) {
		indices = new int[capacity];
		values = new int[capacity];
		node = new int[capacity];
		size = 0;

		for (int i = 0; i < capacity; i++) {
			indices[i] = i;
			node[i] = i;
			values[i] = Integer.MAX_VALUE;
		}

	}

	/**
	 * 
	 * Adds a node to the heap 
	 * 
	 * @param nodeIdx The index of the node to insert
	 * @param cost The distance to the node from the start node
	 */
	public void push(int nodeIdx, int cost) {

		size++;
		node[nodeIdx] = size - 1;

		int fIndex = size - 1;
		indices[fIndex] = nodeIdx;
		values[fIndex] = cost;

		while (fIndex != 0 && values[parent(fIndex)] > values[fIndex]) {
			swap(fIndex, parent(fIndex));
			fIndex = parent(fIndex);
		}

	}

	/**
	 * 
	 * Removes the node with the smallest cost
	 * 
	 * @return The index of the node with the smallest cost
	 */
	public int pop() {

		// if (size <= 0)
		// throw new IllegalStateException("There are no more elements to pop");

		if (size == 1) {
			size--;
			return indices[0];
		}

		// Store the minimum value, and remove it from heap
		int root = indices[0];

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

		while (nodeIdx != 0 && values[parent(nodeIdx)] > values[nodeIdx]) {
			swap(nodeIdx, (nodeIdx = parent(nodeIdx)));
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

	/**
	 * 
	 * Restores the heap property if broken
	 * 
	 * @param i The root node from which the min heap should be healed
	 */
	private void heapify(int i) {

		int left = left(i);
		int right = right(i);

		int smallest = i;

		if (left < size && values[left] < values[i])
			smallest = left;
		if (right < size && values[right] < values[smallest])
			smallest = right;

		if (smallest != i) {

			swap(i, smallest);

			heapify(smallest);
		}

	}

	/**
	 * 
	 * Swaps the elements in the lists so the heap is not destroyed
	 * 
	 * @param a The first index
	 * @param b The second index
	 */
	private void swap(int a, int b) {
		node[indices[a]] = b;
		node[indices[b]] = a;

		swapIndices = indices[a];
		indices[a] = indices[b];
		indices[b] = swapIndices;

		swapValues = values[a];
		values[a] = values[b];
		values[b] = swapValues;

	}

	/**
	 * 
	 * To get the parent node of a node
	 * 
	 * @param i The node to get the parent node from
	 * @return The index of the parent node
	 */
	private static int parent(int i) {
		return (i - 1) / 2;

	}

	/**
	 * 
	 * To get the left node of a node
	 * 
	 * @param i The node to get the left node from
	 * @return The index of the left node
	 */
	private static int left(int i) {
		return (2 * i + 1);

	}

	/**
	 * 
	 * To get the right node of a node
	 * 
	 * @param i The node to get the right node from
	 * @return The index of the right node
	 */
	private static int right(int i) {
		return (2 * i + 2);
		
	}

	/**
	 * Resets the min heap, equal to remove all nodes
	 */
	public void reset() {
		size = 0;
	}

}
