package Pro766.ToeplitzMatrix;

/**
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 *
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 *
 *
 * Example 1:
 *
 * Input:
 * matrix = [
 *   [1,2,3,4],
 *   [5,1,2,3],
 *   [9,5,1,2]
 * ]
 * Output: True
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 */
public class Solution {
    public static void main(String[] args) {
        int[][] matrix ={{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        boolean res = isToeplitzMatrix(matrix);
        System.out.println(res);

    }
    public static boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        int col = matrix[0].length;
        for (int i = row - 1; i >= 0; --i) {
            int index = 0, j = i;
            while (j < row && index < col) {
                if (matrix[j++][index++] != matrix[i][0]) {
                    return false;
                }
            }
        }
        for (int i = 1; i < col; ++i) {
            int index = 0, j = i;
            while (j < col && index < row) {
                if (matrix[index++][j++] != matrix[0][i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
