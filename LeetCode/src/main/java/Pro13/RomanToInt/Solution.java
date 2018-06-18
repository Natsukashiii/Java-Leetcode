package Pro13.RomanToInt;

import java.util.HashMap;
import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        scanner.close();
        Solution solution = new Solution();
        System.out.println(solution.romanToInt(s));
    }

    private int romanToInt(String s)
    {
        if (s == null || s.length() < 1)
            return -1;
        char[] ch = s.toCharArray();
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
        int res = 0;
        int temp = 0; // 临时变量，保存的是当前遍历的上一个数值的值
        int value = 0; // 当前罗马值的大小
        for (int i = ch.length - 1; i >= 0; i--)
        {
            value = hm.get(ch[i]);
            if (temp <= value)  // 左加
            {
                res += value;
                temp = value;
            }
            else    // 右减
            {
                res -= value;
                temp = value;
            }
        }
        return res;
    }
}
