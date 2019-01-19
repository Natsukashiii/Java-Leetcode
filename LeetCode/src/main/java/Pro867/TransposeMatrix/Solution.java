package Pro867.TransposeMatrix;

/**
 * Given a matrix A, return the transpose of A.
 * <p>
 * The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.
 * <p>
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[1,4,7],[2,5,8],[3,6,9]]
 */
public class Solution {
    public static void main(String[] args) {

    }

    public int[][] transpose(int[][] A) {
        if (A == null) {
            return null;
        }
        int len_A = A.length;
        int len_B = A[0].length;
        int[][] res = new int[len_B][];
        for (int i = 0; i < len_B; i++) {
            res[i] = new int[len_A];
        }
        for (int i = 0; i < len_A; i++) {
            int len = A[i].length;
            for (int j = 0; j < len; j++) {
                res[j][i] = A[i][j];
            }
        }
        return res;
    }

}
