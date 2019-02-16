package Pro697.DegreeofanArray;

import java.util.Collections;
import java.util.HashMap;

/**
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 * <p>
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 * <p>
 * Example 1:
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 */
public class Solution
{
    public int findShortestSubArray(int[] nums)
    {
        int length = nums.length;
        HashMap<Integer, Integer> left = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> right = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i = 0; i <length ; i++)
        {
            int a = nums[i];
            if (left.get(a) == null)
            {
                left.put(a, i);
            }
            right.put(a, i);
            count.put(a, count.getOrDefault(a, 0) + 1);
        }
        int degree = Collections.max(count.values());
        for (int x :
                count.keySet())
        {
            if (count.get(x) == degree)
            {
                length = Math.min(length, right.get(x) - left.get(x) + 1);
            }
        }
        return length;
    }
}
