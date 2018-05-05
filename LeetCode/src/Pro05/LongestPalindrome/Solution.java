package Pro05.LongestPalindrome;

public class Solution
{
    //    找出每一个子串，判断是不是回文子串，最后返回最长的，目测时间复杂度n3方，有点高
    public String longestPalindrome(String s)
    {
        int length = s.length();
        int start = 0; //记录最长子串的开始下标
        int maxlength = 0; //记录最长子串的长度

//        找到string里的每一个子串
        for (int i = 0; i < length; i++)
        {
            for (int j = i + 1; j < length; j++)
            {
                int index1 = 0;
                int index2 = 0;
//                判断是不是回文串，从子串的头尾往中间遍历
                for (index1 = i, index2 = j; index1 < index2; index1++, index2--)
                {
//                    如果头尾不相等，直接退出循环
                    if (s.charAt(index1) != s.charAt(index2))
                    {
                        break;
                    }
                }

//                index1=index2的话判断子串的格式是ababa，或是abaaba的形式,记录最大回文子串的长度
                if (index1 >= index2 && j - i >= maxlength)
                {
                    maxlength = j - i + 1;
                    start = i;
                }
            }
        }
        if (maxlength > 0)
        {
            return s.substring(start, start + maxlength);
        }
        return null;
    }

    //    动态规划,因为回文子串的子串也是回文子串
    public String longestPalindrome1(String s)
    {
        if (s.length() < 2 || s == null)
        {
            return null;
        }

        int length = s.length();
        String longest = null;
        int maxLength = 0;
        boolean[][] table = new boolean[length][length];

//        找到单个字符都是回文的
        for (int i = 0; i < length - 1; i++)
        {
            table[i][i] = true;
            longest = s.substring(i, i + 1);
            maxLength = 1;
        }

//        判断两个字符是否是回文
        for (int i = 0; i < length - 1; i++)
        {
            if (s.charAt(i) == s.charAt(i + 1))
            {
                table[i][i + 1] = true;
                longest = s.substring(i, i + 2);
                maxLength = 2;
            }
        }

//        判断长度大于2的子串是否是回文串
        for (int len = 0; len <= length; len++)
        {
            for (int i = 0, j; (j = i + len - 1) <= length - 1; i++)
            {
                if (s.charAt(i) == s.charAt(j))
                {
                    table[i][j] = table[i + 1][j - 1];
                    if (table[i][j] && maxLength < len)
                    {
                        longest = s.substring(i, j + 1);
                        maxLength = len;
                    }
                }
                else
                {
                    table[i][j] = false;
                }
            }
        }
        return longest;
    }
}
