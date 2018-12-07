package de.propro.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import de.propro.backend.*;

public class TestKlasse {

	@Test
	public void testDijktra() {
		Main main = new Main();
		main.initGraph("bw.fmi");
		int[][] input = new int[10][10];
		input[0][0] = 1200173;
		input[0][1] = 2400346;
		input[1][0] = 1200173;
		input[1][1] = 2400347;
		input[2][0] = 1200173;
		input[2][1] = 2400348;
		input[3][0] = 1200173;
		input[3][1] = 2400349;
		input[4][0] = 1200173;
		input[4][1] = 2400350;
		input[5][0] = 1200173;
		input[5][1] = 2400351;
		input[6][0] = 1200173;
		input[6][1] = 2400352;
		input[7][0] = 1200173;
		input[7][1] = 2400353;
		input[8][0] = 1200173;
		input[8][1] = 2400354;
		input[9][0] = 1200173;
		input[9][1] = 2400354;

		int[] output = new int[10];
		output[0] = 383894;
		output[1] = 384088;
		output[2] = 384191;
		output[3] = 384352;
		output[4] = 384544;
		output[5] = 386095;
		output[6] = 384651;
		output[7] = 383727;
		output[8] = 383635;
		output[9] = 534279;

		for (int i = 0; i < 10; i++) {
			DijktraResult r = main.startToEnd(input[i][0], input[i][1], main.reader);
			assertEquals(output[i], r.length);
		}
	}
}
