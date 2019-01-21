package Pro977.SquaresofaSortedArray;

import java.util.Arrays;

/**
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Example 2:
 * <p>
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 */
public class Solution {
    public static void main(String[] args) {
        int[] A = {-7, -3, 2, 3, 11};
        int[] res = sortedSquares(A);
        for (int i = 0; i <res.length ; i++) {
            System.out.println(res[i]);
        }
    }

    public static int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int tmp = A[i] * A[i];
            res[i] = tmp;
        }
        Arrays.sort(res);
        return res;
    }

}
