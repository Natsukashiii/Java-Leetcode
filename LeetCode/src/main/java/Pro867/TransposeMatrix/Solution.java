package Pro867.TransposeMatrix;

public class Solution {
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
