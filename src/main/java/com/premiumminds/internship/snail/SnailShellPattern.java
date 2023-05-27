package com.premiumminds.internship.snail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

class SnailShellPattern implements ISnailShellPattern {

	public Future<int[]> getSnailShell(int[][] matrix) {
		CompletableFuture<int[]> future = new CompletableFuture<>();

		// initial checks
		if (matrix.length == 0) { // if it is empty
			future.complete(new int[0]);
			return future;
		} else if (matrix.length == 1) { // if it is only horizontal
			future.complete(matrix[0]);
			return future;
		} else if (matrix[0].length == 1) { // if it is only vertical
			// TODO
			return future;
		}

		int resultItems = 0;
		int[] result = new int[matrix.length * matrix[0].length];
		for (int i = 0; i < countIterations(matrix); i++) {
			resultItems = getTop(matrix, i, result, resultItems);
			resultItems = getRight(matrix, i, result, resultItems);
			resultItems = getBottom(matrix, i, result, resultItems);
			resultItems = getLeft(matrix, i, result, resultItems);
		}

		// if the matrix has odd dimensions, there will be an incomplete iteration left
		// at the end
		getRemainder(matrix, result, resultItems);

		future.complete(result);
		return future;
	}

	private int countIterations(int[][] matrix) {
		return Math.min(matrix.length / 2, matrix[0].length / 2);
	}

	private int getTop(int[][] matrix, int iteration, int[] result, int resultItems) {
		for (int i = iteration; i < matrix[iteration].length - iteration; i++) {
			result[resultItems] = matrix[iteration][i];
			resultItems++;
		}
		return resultItems;
	}

	private int getRight(int[][] matrix, int iteration, int[] result, int resultItems) {
		for (int i = iteration + 1; i < matrix.length - iteration - 1; i++) {
			result[resultItems] = matrix[i][matrix[i].length - 1 - iteration];
			resultItems++;
		}
		return resultItems;
	}

	private int getBottom(int[][] matrix, int iteration, int[] result, int resultItems) {
		for (int i = matrix[iteration].length - iteration - 1; i >= iteration; i--) {
			result[resultItems] = matrix[matrix.length - 1 - iteration][i];
			resultItems++;
		}
		return resultItems;
	}

	private int getLeft(int[][] matrix, int iteration, int[] result, int resultItems) {
		for (int i = matrix.length - 2 - iteration; i > iteration; i--) {
			result[resultItems] = matrix[i][iteration];
			resultItems++;
		}
		return resultItems;
	}

	private void getRemainder(int[][] matrix, int[] result, int resultItems) {
		if (matrix.length % 2 == 1) {
			int row = matrix.length / 2;
			for (int i = countIterations(matrix); resultItems != result.length; i++) {
				result[resultItems] = matrix[row][i];
				resultItems++;
			}
		} else if (matrix[0].length % 2 == 1) {
			int col = matrix[0].length / 2;
			for (int i = countIterations(matrix); resultItems != result.length; i++) {
				result[resultItems] = matrix[i][col];
				resultItems++;
			}
		}
	}
}
