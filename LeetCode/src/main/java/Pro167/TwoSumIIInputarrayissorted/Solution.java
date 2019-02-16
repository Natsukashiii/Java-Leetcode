package Pro167.TwoSumIIInputarrayissorted;

import java.util.HashMap;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 * <p>
 * Note:
 * <p>
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Example:
 * <p>
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */
public class Solution
{
    public static void main(String[] args)
    {
        int[] numbers = {2, 7, 11, 15};
        int[] res = twoSum(numbers, 9);
        for (int i : res)
        {
            System.out.println(i);
        }
    }

    public static int[] twoSum(int[] numbers, int target)
    {
        if (numbers[0] > target || numbers == null || numbers.length == 0)
        {
            return null;
        }
        int[] res = new int[2];
        int l = 0;
        int r = numbers.length - 1;
        while (l < r)
        {
            if (numbers[l] + numbers[r] == target)
            {
                res = new int[]{l + 1, r + 1};
                return res;
            }
            else if ((numbers[l] + numbers[r]) > target)
            {
                r--;
            }
            else
            {
                l++;
            }
        }
        return res;
    }
}
