package de.propro.backend;

import java.util.PriorityQueue;

import org.jgrapht.util.FibonacciHeapNode;
import org.jgrapht.util.*;
//import org.teneighty.heap.*;
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
	private FibonacciHeap<Integer> heap;
	private PriorityQueue<Integer> i;
	/**
	 * This array represents the node we have
	 */
	FibonacciHeapNode<Integer>[] nodes;
	// private Entry<Integer, Integer>[] nodes;
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
		// heap = new FibonacciHeap<Integer, Integer>();
		// nodes = new Entry[capacity];
		heap = new FibonacciHeap<Integer>();
		nodes = new FibonacciHeapNode[capacity];
		counter = 0;

	}

	/**
	 * Empty constructor for sub classes
	 */
	protected MinHeap() {
		
	}
	
	protected long push, pop, decreaseValue;

	/**
	 * 
	 * Adds a node to the heap
	 * 
	 * @param nodeIdx The index of the id which is inserted
	 * @param value   The cost of the node
	 * 
	 */
	public void push(int nodeIdx, int value) {
		long time = System.nanoTime();
		FibonacciHeapNode<Integer> node = new FibonacciHeapNode<Integer>(nodeIdx);
		nodes[counter] = node;
		heap.insert(node, value);
		// Entry e = heap.insert(value, nodeIdx);
		// nodes[counter] = e;
		counter++;
		push += (System.nanoTime() - time);
	}

	/**
	 * 
	 * Removes the node with the smallest cost
	 * 
	 * @return The index of the node with the smallest cost
	 */
	public int pop() {
		long time = System.nanoTime();
		// Entry<Integer, Integer> value = heap.
		int i = (int) heap.removeMin().getData();
		pop += (System.nanoTime() - time);
		return i;// return value.getValue();

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
		long time = System.nanoTime();
		heap.decreaseKey(nodes[nodeIdx], value);
		decreaseValue += (System.nanoTime() - time);
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
		// return heap.getSize();
		return heap.size();
	}
}
