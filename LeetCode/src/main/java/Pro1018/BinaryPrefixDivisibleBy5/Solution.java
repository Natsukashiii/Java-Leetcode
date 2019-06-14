package Pro1018.BinaryPrefixDivisibleBy5;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A of 0s and 1s, consider N_i: the i-th subarray from A[0] to A[i] interpreted as a binary number (from most-significant-bit to least-significant-bit.)
 * <p>
 * Return a list of booleans answer, where answer[i] is true if and only if N_i is divisible by 5.
 * <p>
 * Example 1:
 * <p>
 * Input: [0,1,1]
 * Output: [true,false,false]
 * Explanation:
 * The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.  Only the first number is divisible by 5, so answer[0] is true.
 * Example 2:
 * <p>
 * Input: [1,1,1]
 * Output: [false,false,false]
 * Example 3:
 * <p>
 * Input: [0,1,1,1,1,1]
 * Output: [true,false,false,false,true,false]
 * Example 4:
 * <p>
 * Input: [1,1,1,0,1]
 * Output: [false,false,false,false,false]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 30000
 * A[i] is 0 or 1
 */
public class Solution
{
    public List<Boolean> prefixesDivBy5(int[] A)
    {
        List<Boolean> result = new ArrayList<>();
        if (A == null || A.length == 0)
        {
            return result;
        }
        int num = A[0];
        if (A[0] == 0)
        {
            result.add(true);
        }
        else
        {
            result.add(false);
        }
        for (int i = 1; i < A.length; i++)
        {
            num = A[i] == 0 ? num * 2 : num * 2 + 1;
            num = num % 10;
            if (num == 0 || num == 5)
            {
                result.add(true);
            }
            else
            {
                result.add(false);
            }
        }
        return result;
    }
}
