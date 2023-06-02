package com.premiumminds.internship.snail;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2023.
 */
@RunWith(JUnit4.class)
public class SnailShellPatternTest {

	/**
	 * The corresponding implementations to test.
	 *
	 * If you want, you can make others :)
	 *
	 */
	public SnailShellPatternTest() {
	};

	@Test
	public void ScreenLockinPatternTestFirst3Length2Test()
			throws InterruptedException, ExecutionException, TimeoutException {
		int[][] matrix = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
		Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
		int[] result = count.get(10, TimeUnit.SECONDS);
		int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void TestEmpty() throws InterruptedException, ExecutionException, TimeoutException {
		int[][] matrix = { {} };
		Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
		int[] result = count.get(10, TimeUnit.SECONDS);
		int[] expected = {};
		assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void Test1by1() throws InterruptedException, ExecutionException, TimeoutException {
		int[][] matrix = { { 1 } };
		Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
		int[] result = count.get(10, TimeUnit.SECONDS);
		int[] expected = { 1 };
		assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void Test1by3() throws InterruptedException, ExecutionException, TimeoutException {
		int[][] matrix = { { 1, 2, 3 } };
		Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
		int[] result = count.get(10, TimeUnit.SECONDS);
		int[] expected = { 1, 2, 3 };
		assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void Test3by1() throws InterruptedException, ExecutionException, TimeoutException {
		int[][] matrix = { { 1 }, { 2 }, { 3 } };
		Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
		int[] result = count.get(10, TimeUnit.SECONDS);
		int[] expected = { 1, 2, 3 };
		assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void Test2by3() throws InterruptedException, ExecutionException, TimeoutException {
		int[][] matrix = { { 1, 2, 3 }, { 6, 5, 4 } };
		Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
		int[] result = count.get(10, TimeUnit.SECONDS);
		int[] expected = { 1, 2, 3, 4, 5, 6 };
		assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void Test3by4() throws InterruptedException, ExecutionException, TimeoutException {
		int[][] matrix = { { 1, 2, 3, 4 }, { 10, 11, 12, 5 }, { 9, 8, 7, 6 } };
		Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
		int[] result = count.get(10, TimeUnit.SECONDS);
		int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void TestLargeMatrix() throws InterruptedException, ExecutionException, TimeoutException {
		int[][] matrix = new int[1000][1000];
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				if ((i + j) % 2 == 0)
					matrix[i][j] = 1;
				else
					matrix[i][j] = 0;
			}
		}
		Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
		int[] result = count.get(10, TimeUnit.SECONDS);
		int[] expected = new int[1000000];
		for (int i = 0; i < expected.length; i++) {
			if (i % 2 == 0)
				expected[i] = 1;
			else
				expected[i] = 0;
		}
		assertTrue(Arrays.equals(result, expected));
	}

}