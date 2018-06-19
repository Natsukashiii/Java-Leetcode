package Pro14.LongestCommonPrefix;

//查找字符串数组中的最长公共前缀。
//https://blog.csdn.net/zsy112371/article/details/52433799
//        如果不存在公共前缀，返回空字符串 ""

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Solution
{

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        Scanner scanner = new Scanner(System.in);

        String[] strs = new String[3];
        System.out.println(strs.length);

        for (int i = 0; i < 3; i++)
        {
            strs[i] = scanner.next();
        }

        scanner.close();
        System.out.println(strs[0]);

        String res = solution.longestCommonPrefix(strs);
        System.out.println(res);

    }

    public String longestCommonPrefix(String[] strs)
    {
        if (strs == null || strs.length == 0)
            return "";


        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
        {
            String curr = strs[i];
            int len = Math.min(prefix.length(), curr.length());
            int j;
            for (j = 0; j < len; j++)
            {
                if (prefix.charAt(j) != curr.charAt(j))
                {
                    break;
                }
            }
            prefix = prefix.substring(0, j);
        }
        return prefix;
    }

}
