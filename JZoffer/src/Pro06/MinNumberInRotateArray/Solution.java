package Pro06.MinNumberInRotateArray;

import java.util.Arrays;

//找到旋转数组的最小数

public class Solution
{
    //    直接sort了emmm
    public int minNumberInRotateArray(int[] array)
    {
        if (array.length == 0)
        {
            return 0;
        }
        else
        {
            Arrays.sort(array);
        }
        return array[0];
    }

//        二分法
//    第一个序列>=第二个序列的值
//    最小值位于第二个序列的开头
    public int minNumberInRotateArray2(int[] array)
    {
        if (array.length == 0)
        {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right)
        {
            int mid = left + (right - left) / 2;
            if (array[mid] > array[right])
            {
                left = mid + 1;
            }
            else if (array[mid] == array[right])
            {
                right = right - 1;
            }
            else
            {
                right = mid;
            }
        }
        return array[left];

    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int[] array = {3, 4, 5, 1, 2};
        int a = solution.minNumberInRotateArray2(array);
        System.out.println(a);
    }
}
