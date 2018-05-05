package Pro34.FirstNotRepeatingChar;

/*
在一个字符串(1<=字符串长度<=10000，全部由字母组成)
        中找到第一个只出现一次的字符,
        并返回它的位置
*/

import java.util.HashMap;

public class Solution
{
    public int FirstNotRepeatingChar(String str)
    {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++)
        {
            if (map.containsKey(str.charAt(i)))
            {
                int timeOfChar = map.get(str.charAt(i));
                map.put(str.charAt(i), ++timeOfChar);
            }
            else
            {
                map.put(str.charAt(i), 1);
            }
        }
        int position = -1;
        int i = 0;
        for (; i < str.length(); i++)
        {

            char c = str.charAt(i);
            if (map.get(c) == 1)
            {
                return i;
            }
        }
        return position;
    }
}