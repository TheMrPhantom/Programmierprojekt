package de.propro.tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import de.propro.backend.*;

public class DijktraTest {
	static Main main;

	@BeforeClass
	public static void init() {
		main = new Main();
		//main.initGraph("bw.fmi");
	}

	@Test
	public void checkDijktra0() {
		//testDijktra(21);
	}
/*
	@Test
	public void checkDijktra1() {
		testDijktra(20);
	}

	@Test
	public void checkDijktra2() {
		testDijktra(22);
	}

	@Test
	public void checkDijktra3() {
		testDijktra(23);
	}

	@Test
	public void checkDijktra4() {
		testDijktra(24);
	}

	@Test
	public void checkDijktra5() {
		testDijktra(25);
	}

	@Test
	public void checkDijktra6() {
		testDijktra(26);
	}

	@Test
	public void checkDijktra7() {
		testDijktra(27);
	}

	@Test
	public void checkDijktra8() {
		testDijktra(28);
	}

	@Test
	public void checkDijktra9() {
		testDijktra(29);
	}

	@Test
	public void testAll() {
		for (int i = 0; i < 100; i++) {
			System.out.println("TEST: "+i);
			testDijktra(i);
		}
	}
*/
	public void testDijktra(int i) {
		try {
			FileReader r = new FileReader("bw.que");
			BufferedReader buffi = new BufferedReader(r);

			for (int x = 0; x < i - 1; x++) {
				buffi.readLine();
			}
			String queue = buffi.readLine();
			String x = queue.split(" ")[0];
			String y = queue.split(" ")[1];

			buffi.close();

			r = new FileReader("bw.sol");
			buffi = new BufferedReader(r);

			for (int z = 0; z < i - 1; z++) {
				buffi.readLine();
			}

			String res = buffi.readLine();
			buffi.close();
			DijktraResult dr = main.startToEnd(Integer.parseInt(x), Integer.parseInt(y), main.reader);
			assertEquals(Integer.parseInt(res), dr.length);
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
}
