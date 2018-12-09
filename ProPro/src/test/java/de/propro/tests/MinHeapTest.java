package de.propro.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.propro.backend.CustomMinHeap;
import de.propro.backend.MinHeap;

public class MinHeapTest {

	@Test
	public void testConstructor() {
		CustomMinHeap cmh = new CustomMinHeap(1000000);
		assertEquals(0, 0);
	}

	@Test
	public void testHeap1() {
		CustomMinHeap min = new CustomMinHeap(10);

		min.decreaseValue(3, 40);	
		min.decreaseValue(1, 100);	
		min.decreaseValue(2, 1000);	
		min.decreaseValue(0, 500);	
		min.decreaseValue(4, 10);	
		min.decreaseValue(8, 80);	
		min.decreaseValue(7, 700);	
		min.decreaseValue(9, 2000);	
		min.decreaseValue(5, 60);	
		min.decreaseValue(6, 400);	

		assertEquals(4, min.pop());
		assertEquals(3, min.pop());
		assertEquals(5, min.pop());
		assertEquals(8, min.pop());
		assertEquals(1, min.pop());
		assertEquals(6, min.pop());
		assertEquals(0, min.pop());
		assertEquals(7, min.pop());
		assertEquals(2, min.pop());
		assertEquals(9, min.pop());
	}
	
	@Test
	public void testHeap2() {
		CustomMinHeap min = new CustomMinHeap(4);

		min.decreaseValue(2, 0);
		min.decreaseValue(1, 20);
		min.decreaseValue(0, 10);
		min.decreaseValue(3, 3);

		assertEquals(2, min.pop());
		assertEquals(3, min.pop());
		assertEquals(0, min.pop());
		assertEquals(1, min.pop());

	}
	
	@Test
	public void testHeap3() {
		CustomMinHeap min = new CustomMinHeap(10);

		min.decreaseValue(2, 0);
		min.decreaseValue(1, 20);
		min.decreaseValue(0, 10);
		min.decreaseValue(3, 3);

		assertEquals(2, min.pop());
		assertEquals(3, min.pop());
		assertEquals(0, min.pop());
		assertEquals(1, min.pop());

	}
	
	@Test
	public void testHeap4() {
		CustomMinHeap min = new CustomMinHeap(10);

		min.decreaseValue(0, 0);
		min.decreaseValue(1, 20);
		min.decreaseValue(2, 10);
		min.decreaseValue(3, 40);
		min.decreaseValue(4, 50);
		min.decreaseValue(5, 100);
		min.decreaseValue(6, 60);
		min.decreaseValue(7, 80);
		
		assertEquals(0, min.pop());
		assertEquals(2, min.pop());
		assertEquals(1, min.pop());
		assertEquals(3, min.pop());
		assertEquals(4, min.pop());
		assertEquals(6, min.pop());
		assertEquals(7, min.pop());
		assertEquals(5, min.pop());

	}

}
