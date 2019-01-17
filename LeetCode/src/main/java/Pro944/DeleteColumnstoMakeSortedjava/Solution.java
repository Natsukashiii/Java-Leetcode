package Pro944.DeleteColumnstoMakeSortedjava;

public class Solution {
    public static void main(String[] args) {
        String[] A ={"zyx","wvu","tsr"};
        System.out.println(minDeletionSize(A));
    }

    public static int minDeletionSize(String[] A) {
        int len = A[0].length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < A.length; j++) {
                if (A[j - 1].charAt(i) > A[j].charAt(i)) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
