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
        int length = nums.length;
        int num1 = 0, num2 = 0, num3 = 0;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> resList = new ArrayList<Integer>();
        List<Integer> numsList = new ArrayList<Integer>();
        List<Integer> tempnumsList = new ArrayList<Integer>();

        for (int i = 0; i < length; i++)
        {
            numsList.add(nums[i]);
//            System.out.println(nums[i]);
        }


        for (int i = 0; i < length; i++)
        {
            num1 = nums[i];
            for (int j = i + 1; j < length; j++)
            {
                num2 = nums[j];
                num3 = 0 - num1 - num2;
                tempnumsList = numsList.subList(j + 1, length);
                System.out.println("temp: "+tempnumsList);
                if (tempnumsList.contains(num3))
                {
                    resList.clear();
                    resList.add(num1);
                    resList.add(num2);
                    resList.add(num3);
                    System.out.println("num1: " + num1);
                    System.out.println("num2: " + num2);
                    System.out.println("num3: " + num3);
                    res.add(resList);
                    System.out.println("res: "+res);

                }
            }
        }
        for (List<Integer> resNum : res)
        {
            System.out.println("resNum: "+resNum);
        }

        //去重
        Set<List<Integer>> set = new HashSet<List<Integer>>(res);
        System.out.println("Set: "+set);
        res.clear();
        res.addAll(set);

        return res;
    }
}
