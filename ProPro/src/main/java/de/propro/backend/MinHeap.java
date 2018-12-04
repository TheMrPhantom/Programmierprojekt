package de.propro.backend;

import org.jgrapht.util.*;

public class MinHeap {

	private FibonacciHeap<Integer> heap;

	public MinHeap() {
		heap = new FibonacciHeap<Integer>();
	}

	public void push(int nodeIdx, int value) {
		heap.insert(new FibonacciHeapNode<Integer>(nodeIdx), value);
	}

	public int pop() {
		FibonacciHeapNode<Integer> value = heap.min();
		heap.removeMin();
		return value.getData();
	}
	
	public void decreaseValue(int nodeIdx, int value) {
		heap.decreaseKey(new FibonacciHeapNode<Integer>(value), nodeIdx);
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
}
