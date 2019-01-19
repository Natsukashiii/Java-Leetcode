package Pro922.SortArrayByParityII;

/**
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 *
 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 *
 * You may return any answer array that satisfies this condition.
 *
 *
 *
 * Example 1:
 *
 * Input: [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 */
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
