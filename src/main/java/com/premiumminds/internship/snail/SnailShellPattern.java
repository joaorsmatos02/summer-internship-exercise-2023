package com.premiumminds.internship.snail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {

	/**
	 * Method to get snailshell pattern
	 * 
	 * @param matrix matrix of numbers to go through
	 * @return order array of values thar represent a snail shell pattern
	 */
	public Future<int[]> getSnailShell(int[][] matrix) {
		CompletableFuture<int[]> future = new CompletableFuture<>();

		int[] result = new int[matrix.length * matrix[0].length];
		while (matrix.length != 0) {
			// add the first array in the matrix to the result
			// get the last element from each array and add to result
			// reverse the last array in the matrix and add to result
			// get the first element from each array, reverse and add to result
		}

		future.complete(result);
		return future;
	};
}
