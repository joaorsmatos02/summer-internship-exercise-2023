package com.premiumminds.internship.snail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

class SnailShellPattern implements ISnailShellPattern {

	@Override
	public Future<int[]> getSnailShell(int[][] matrix) {
		CompletableFuture<int[]> future = new CompletableFuture<>();

		// Create a result array and an index pointer
		int[] result = new int[matrix.length * matrix[0].length];
		int resultIndex = 0;

		// Count the number of iterations (completed laps around the matrix)
		int totalIterations = Math.min(matrix.length / 2, matrix[0].length / 2);
		for (int iterationCount = 0; iterationCount < totalIterations; iterationCount++) {

			// Traverse top row
			for (int i = iterationCount; i < matrix[iterationCount].length - iterationCount; i++) {
				result[resultIndex] = matrix[iterationCount][i];
				resultIndex++;
			}

			// Traverse right column
			for (int i = iterationCount + 1; i < matrix.length - iterationCount - 1; i++) {
				result[resultIndex] = matrix[i][matrix[i].length - 1 - iterationCount];
				resultIndex++;
			}

			// Traverse bottom row
			for (int i = matrix[iterationCount].length - iterationCount - 1; i >= iterationCount; i--) {
				result[resultIndex] = matrix[matrix.length - 1 - iterationCount][i];
				resultIndex++;
			}

			// Traverse left column
			for (int i = matrix.length - 2 - iterationCount; i > iterationCount; i--) {
				result[resultIndex] = matrix[i][iterationCount];
				resultIndex++;
			}
		}

		// Handle incomplete iterations
		int remainingRows = matrix.length - 2 * totalIterations;
		int remainingCols = matrix[0].length - 2 * totalIterations;
		if (remainingRows == 1) {
			int row = matrix.length / 2;
			for (int i = totalIterations; resultIndex != result.length; i++) {
				result[resultIndex] = matrix[row][i];
				resultIndex++;
			}
		} else if (remainingCols == 1) {
			int col = matrix[0].length / 2;
			for (int i = totalIterations; resultIndex != result.length; i++) {
				result[resultIndex] = matrix[i][col];
				resultIndex++;
			}
		}

		future.complete(result);
		return future;
	}

}