package Pro12.ContainerwithMostWater;

/*
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
画 n 条垂直线，使得垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，
使得它们与 x 轴共同构成的容器可以容纳最多的水。

注意：你不能倾斜容器，n 至少是2。
*/

// when i<j res=Max(j-i)*|aj-ai|
// 容器的体积即为容器的底（在x轴上）和容器的高（即ai和aj中的最小值）

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] array = new int[count];
        for (int i = 0; i < count; i++)
        {
            array[i] = scanner.nextInt();
        }
        scanner.close();
        Solution solution = new Solution();
        int max = solution.maxArea(array);
        System.out.println(max);
    }


    public int maxArea(int[] height)
    {
//        设置两个下标来遍历，最左边为left，最右边为数组长度
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right)
        {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right])
            {
                left++;
            }
            else
            {
                right--;
            }
        }
        return max;
    }
}
