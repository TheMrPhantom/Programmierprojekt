package de.propro.backend;

//import org.jgrapht.util.*;
import org.teneighty.heap.*;
/**
 * 
 * This is the Min Heap used for the Dijkstra
 * 
 * @author Justin
 *
 */
import org.teneighty.heap.Heap.Entry;

public class MinHeap {

	/**
	 * The internal heap structure
	 */
	private FibonacciHeap<Integer, Integer> heap;
	/**
	 * This array represents the node we have
	 */
	// private FibonacciHeapNode<Integer>[] nodes;
	private Entry<Integer, Integer>[] nodes;
	/**
	 * Here is the index of the node which is inserted next
	 */
	private int counter;

	/**
	 * 
	 * Initializes the heap
	 * 
	 * @param capacity The amount of nodes that will be added
	 */
	public MinHeap(int capacity) {
		heap = new FibonacciHeap<Integer, Integer>();
		nodes = new Entry[capacity];
		counter = 0;
	}

	/**
	 * 
	 * Adds a node to the heap
	 * 
	 * @param nodeIdx The index of the id which is inserted
	 * @param value   The cost of the node
	 * 
	 */
	public void push(int nodeIdx, int value) {
		// FibonacciHeapNode<Integer> node = new FibonacciHeapNode<Integer>(nodeIdx);

		Entry e = heap.insert(value, nodeIdx);
		nodes[counter] = e;
		counter++;
	}

	/**
	 * 
	 * Removes the node with the smallest cost
	 * 
	 * @return The index of the node with the smallest cost
	 */
	public int pop() {
		Entry<Integer, Integer> value = heap.extractMinimum();
		// heap.removeMin();
		return value.getValue();
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

		heap.decreaseKey(nodes[nodeIdx], value);
	}

	/**
	 * 
	 * Checks if the heap is empty
	 * 
	 * @return True if the heap is empty, false if not
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	/**
	 * 
	 * Return the number of elements in the heap
	 * 
	 * @return The size
	 */
	public int getSize() {
		return heap.getSize();
	}
}
