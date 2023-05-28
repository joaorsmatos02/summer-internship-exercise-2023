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

		// while result has less elements than matrix
		while (resultIndex < result.length) {

			// top boundary
			resultIndex = traverseTopBoundary(matrix, result, resultIndex, top, left, right);
			top++;

			// right boundary
			resultIndex = traverseRightBoundary(matrix, result, resultIndex, right, top, bottom);
			right--;

			// bottom boundary
			if (top <= bottom) {
				resultIndex = traverseBottomBoundary(matrix, result, resultIndex, bottom, left, right);
				bottom--;
			}

			// left boundary
			if (left <= right) {
				resultIndex = traverseLeftBoundary(matrix, result, resultIndex, left, top, bottom);
				left++;
			}
		}

		future.complete(result);
		return future;
	}

	/**
	 * Traverses the top boundary of the matrix and adds the elements to the result
	 * array.
	 *
	 * @param matrix      the input matrix
	 * @param result      the result array
	 * @param resultIndex the current index in the result array
	 * @param top         the current top boundary index
	 * @param left        the current left boundary index
	 * @param right       the current right boundary index
	 * @return the updated result index after traversing the top boundary
	 */
	private int traverseTopBoundary(int[][] matrix, int[] result, int resultIndex, int top, int left, int right) {
		for (int i = left; i <= right; i++) {
			result[resultIndex++] = matrix[top][i];
		}
		return resultIndex;
	}

	/**
	 * Traverses the right boundary of the matrix and adds the elements to the
	 * result array.
	 *
	 * @param matrix      the input matrix
	 * @param result      the result array
	 * @param resultIndex the current index in the result array
	 * @param right       the current right boundary index
	 * @param top         the current top boundary index
	 * @param bottom      the current bottom boundary index
	 * @return the updated result index after traversing the right boundary
	 */
	private int traverseRightBoundary(int[][] matrix, int[] result, int resultIndex, int right, int top, int bottom) {
		for (int i = top; i <= bottom; i++) {
			result[resultIndex++] = matrix[i][right];
		}
		return resultIndex;
	}

	/**
	 * Traverses the bottom boundary of the matrix and adds the elements to the
	 * result array.
	 *
	 * @param matrix      the input matrix
	 * @param result      the result array
	 * @param resultIndex the current index in the result array
	 * @param bottom      the current bottom boundary index
	 * @param left        the current left boundary index
	 * @param right       the current right boundary index
	 * @return the updated result index after traversing the bottom boundary
	 */
	private int traverseBottomBoundary(int[][] matrix, int[] result, int resultIndex, int bottom, int left, int right) {
		for (int i = right; i >= left; i--) {
			result[resultIndex++] = matrix[bottom][i];
		}
		return resultIndex;
	}

	/**
	 * Traverses the left boundary of the matrix and adds the elements to the result
	 * array.
	 *
	 * @param matrix      the input matrix
	 * @param result      the result array
	 * @param resultIndex the current index in the result array
	 * @param left        the current left boundary index
	 * @param top         the current top boundary index
	 * @param bottom      the current bottom boundary index
	 * @return the updated result index after traversing the left boundary
	 */
	private int traverseLeftBoundary(int[][] matrix, int[] result, int resultIndex, int left, int top, int bottom) {
		for (int i = bottom; i >= top; i--) {
			result[resultIndex++] = matrix[i][left];
		}
		return resultIndex;
	}

}