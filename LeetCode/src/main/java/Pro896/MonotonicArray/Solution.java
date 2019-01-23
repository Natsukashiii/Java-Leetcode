package Pro896.MonotonicArray;

/**
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * <p>
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
 * <p>
 * Return true if and only if the given array A is monotonic.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,2,3]
 * Output: true
 * Example 2:
 * <p>
 * Input: [6,5,4,4]
 * Output: true
 * Example 3:
 * <p>
 * Input: [1,3,2]
 * Output: false
 * Example 4:
 * <p>
 * Input: [1,2,4,5]
 * Output: true
 */
public class Solution {

    public boolean isMonotonic(int[] A) {
        return isCreasing(A) || isDecreasing(A);
    }

    public boolean isCreasing(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isDecreasing(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] < A[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
