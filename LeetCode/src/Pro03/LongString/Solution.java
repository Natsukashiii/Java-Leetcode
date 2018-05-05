package Pro03.LongString;

import java.util.HashMap;
import java.util.Map;

public class Solution
{
    //    果然重建map time太长了。。。
    public int lengthOfLongestSubstring(String s)
    {
        if (s == null || s.length() == 0)
        {
            return 0;
        }

        int len = 0;
        int res = 0;

//        建立一个新的map，key值存储string字符串的没一个字符，value存储字符串的位置
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
        {
//            如果hashmap已经存在这个字符了，就清空hashmap，重建一个hashmap，并且从第一次出现字符的下一个字符开始
            if (hashMap.containsKey(s.charAt(i)))
            {
                i = hashMap.get(s.charAt(i));
                hashMap.clear();
                len = 0;
                //所以这里continue循环回了这个if语句的开头，也就直接往下走了
                continue; //continue用于结束循环体中其后语句的执行，并跳回循环程序块的开头执行下一次循环，而不是立刻循环体。
            }
            len++;
            hashMap.put(s.charAt(i), i);
            res = Math.max(res, len);
        }
        return res;
    }

    //    不重建的话，可以用个flag值来记录每次字符串发生重复了的位置
    public int lengthOfLongestSubstring1(String s)
    {
        if (s == null || s.length() == 0)
        {
            return 0;
        }

        int flag = 0;
        int len = 0;
        int res = 0;

        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
        {
//            如果hashmap里存在这个字符，flag设置为这个字符的下一个字符的位置
            if (hashMap.containsKey(s.charAt(i))&&hashMap.get(s.charAt(i))>=flag)
            {
                flag=hashMap.get(s.charAt(i))+1;
            }
            len=i-flag+1;
            hashMap.put(s.charAt(i),i);
            res=Math.max(len,res);
        }
        return res;
    }
}
