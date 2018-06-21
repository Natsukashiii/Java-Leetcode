package Pro16.ThreeSumClosest;

import java.util.Arrays;

public class Solution
{
    public int threeSumClosest(int[] nums, int target)
    {
        if (nums.length < 3)
        {
            return 0;
        }
        Arrays.sort(nums);
        int i, j, k, result = 0, reserve;
        int min = Integer.MAX_VALUE;
        for (i = 0; i < nums.length - 2; i++)
        {
            j = i + 1;
            k = nums.length - 1;
            reserve = target - nums[i];
            while (j < k)
            {
                if (Math.abs(reserve - nums[j] - nums[k]) < min)
                {
                    result = nums[i] + nums[j] + nums[k];
                    min = Math.abs(reserve - nums[j] - nums[k]);
                }
                if (nums[j] + nums[k] < reserve)
                {
                    j++;
                }
                else
                {
                    k--;
                }
            }
        }
        return result;

    }
}
