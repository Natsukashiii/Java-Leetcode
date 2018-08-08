package Pro18.FourSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和转换为三数之和
 */
public class Solution
{
    public List<List<Integer>> fourSum(int[] nums, int target)
    {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums.length < 4)
        {
            return null;
        }
        for (int i = 0; i < nums.length - 3; i++)
        {
            for (int j = i + 1; j < nums.length - 2; j++)
            {
                int p = j + 1;
                int q = nums.length - 1;
                while (p < q)
                {
                    if (nums[i] + nums[j] + nums[p] + nums[q] == target)
                    {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[p]);
                        list.add(nums[q]);
                        res.add(list);
                        while (p < q && nums[p] == nums[p + 1])
                        {
                            i++;
                        }
                        while (p < q && nums[q] == nums[q - 1])
                        {
                            q--;
                        }
                    }
                    if (nums[i] + nums[j] + nums[p] + nums[q] < target)
                    {
                        p++;
                    }
                    else
                    {
                        q--;
                    }
                }
                while (j < nums.length - 2 && nums[j] == nums[j + 1])
                {
                    j++;
                }
            }
            while (i < nums.length - 3 && nums[i] == nums[i + 1])
            {
                i++;
            }
        }
        return res;
    }
}