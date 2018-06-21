package Pro15.Sum3;

import java.util.*;

public class Solution
{
    public static void main(String[] args)
    {
        Solution solution = new Solution();
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[6];
        for (int i = 0; i < 6; i++)
        {
            nums[i] = scanner.nextInt();
        }
        scanner.close();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res = solution.threeSum(nums);
        for (List<Integer> resNum : res)
        {
            System.out.println(resNum);
        }
    }

    public List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (nums != null && nums.length > 2)
        {
            // 先对数组进行排序
            Arrays.sort(nums);
            // i表示如果取第i个数作为结果
            for (int i = 0; i < nums.length - 2; )
            {
                // 第二个数可能的起始位置
                int j = i + 1;
                // 第三个数可能是结束位置
                int k = nums.length - 1;

                while (j < k)
                {
                    // 如果找到满足条件的解
                    if (nums[j] + nums[k] == -nums[i])
                    {
                        // 将结果加入到结果含集中
                        List<Integer> list = new ArrayList<Integer>(3);
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);

                        // 移动到下一个位置。找下一组解
                        k--;
                        j++;

                        // 从左向右找第一个与之前处理的数不同的数的下标
                        while (j < k && nums[j] == nums[j - 1])
                        {
                            j++;
                        }
                        // 从右向左找第一个与之前处理的数不同的数的下标
                        while (j < k && nums[k] == nums[k + 1])
                        {
                            k--;
                        }
                    }
                    // 和大于0
                    else if (nums[j] + nums[k] > -nums[i])
                    {
                        k--;
                        // 从右向左找第一个与之前处理的数不同的数的下标
                        while (j < k && nums[k] == nums[k + 1])
                        {
                            k--;
                        }
                    }
                    // 和小于0
                    else
                    {
                        j++;
                        // 从左向右找第一个与之前处理的数不同的数的下标
                        while (j < k && nums[j] == nums[j - 1])
                        {
                            j++;
                        }
                    }
                }

                // 指向下一个要处理的数
                i++;
                // 从左向右找第一个与之前处理的数不同的数的下标
                while (i < nums.length - 2 && nums[i] == nums[i - 1])
                {
                    i++;
                }
            }
        }

        return result;
    }
}
