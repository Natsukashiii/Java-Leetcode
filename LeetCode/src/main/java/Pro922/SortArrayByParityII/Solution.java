package Pro922.SortArrayByParityII;

public class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int tmp = 0;
        int i = 0;
        int j = A.length - 1;
        for (i = 0; i < A.length; i++) {
            if (i % 2 == A[i] % 2) {
                continue;
            }
            for (j = i + 1; j < A.length; j++) {
                if (A[i] % 2 != A[j] % 2) {
                    tmp = A[i];
                    A[i] = A[j];
                    A[j] = tmp;
                    break;
                }
            }
        }
        return A;
    }
}
