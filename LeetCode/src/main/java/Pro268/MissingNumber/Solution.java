package Pro268.MissingNumber;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 */
public class Solution
{
    public int missingNumber(int[] nums)
    {
        int length = nums.length;
        int count = (length + 1) * length / 2;
        int current_count = 0;
        for (int i : nums)
        {
            current_count += i;
        }
        int missingNum = count - current_count;
        return missingNum;
    }
}
