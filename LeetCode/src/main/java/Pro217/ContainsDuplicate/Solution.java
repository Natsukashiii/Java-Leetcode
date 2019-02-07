package Pro217.ContainsDuplicate;

import java.util.HashMap;

/**
 * Given an array of integers, find if the array contains any duplicates.
 * <p>
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,1]
 * Output: true
 * Example 2:
 * <p>
 * Input: [1,2,3,4]
 * Output: false
 * Example 3:
 * <p>
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class Solution
{
    public static void main(String[] args)
    {
        int[] nums = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println(containsDuplicate(nums));

    }

    public static boolean containsDuplicate(int[] nums)
    {
        HashMap map = new HashMap();
        for (int i = 0; i < nums.length; i++)
        {
            map.put(nums[i], nums[i]);
        }
        if (map.size() != nums.length)
        {
            return true;
        }
        return false;
    }
}
