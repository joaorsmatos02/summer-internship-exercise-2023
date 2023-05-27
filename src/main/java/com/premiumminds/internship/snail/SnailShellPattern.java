package com.premiumminds.internship.snail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

class SnailShellPattern implements ISnailShellPattern {

	@Override
	public Future<int[]> getSnailShell(int[][] matrix) {
		CompletableFuture<int[]> future = new CompletableFuture<>();

		// create a result array and an index pointer
		int[] result = new int[matrix.length * matrix[0].length];
		int resultIndex = 0;

		// define initial boundaries
		int top = 0;
		int bottom = matrix.length - 1;
		int left = 0;
		int right = matrix[0].length - 1;

		while (top <= bottom && left <= right) {

			// top boundary
			for (int i = left; i <= right; i++) {
				result[resultIndex] = matrix[top][i];
				resultIndex++;
			}
			top++;

			// right boundary
			for (int i = top; i <= bottom; i++) {
				result[resultIndex] = matrix[i][right];
				resultIndex++;
			}
			right--;

			// bottom boundary
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					result[resultIndex] = matrix[bottom][i];
					resultIndex++;
				}
				bottom--;
			}

			// left boundary
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					result[resultIndex] = matrix[i][left];
					resultIndex++;
				}
				left++;
			}
		}

		future.complete(result);
		return future;
	}

}