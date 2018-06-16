package Pro10.RegularExpressionMatching;

/*给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。

        '.' 匹配任意单个字符。
        '*' 匹配零个或多个前面的元素。
        s为包含a-z的字符串，p包含'.'和'*'
        */



/*
        可以用贪心算法，，从p尾部向前扫，对于p中的每个字符，
        在s中找到其所能匹配的最长子串，扫描完毕后判断是否在s的头部
        但是对于aa*b*c的 在第二个a就回判断到达了头部

        仍然从待检字符串尾部向前扫描，设0≤j<s.length()，考虑对于子串s[j..s.length()-1]能够在
        正则表达式p找到匹配（match[j]）的条件为s[j+1...s.length()-1]匹配且s[j]也能够在pattern中找到匹配。
        “s[j]也能够在pattern中找到匹配”
        设i为pattern索引，第一种情况：若p[i]不为'*'，则进行单字符判断，当p[i]=='.'或p[i]==s[j]时match[j]成立；
        第二种情况：p[i]为"*"，则match[j]成立的条件为p[i-1]=='.'或p[i-1]==p[j]。另外，在这种情况下若match[j]已经被置为true，
        就算p[i-1]=='.'||p[i-1]==p[j]不成立也应将其值保持，因为*出现时，其之前元素可以为0个。

        动态规划
        */

import java.util.HashMap;
import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String p = scanner.next();
        scanner.close();
        boolean res = isMatch(s, p);
        System.out.println(res);
    }

    public static boolean isMatch(String s, String p)
    {

        boolean[] match = new boolean[s.length() + 1];
        for (int i = 0; i < match.length; i++)
        {
            match[i] = false;
        }
        match[s.length()] = true;
        for (int i = p.length() - 1; i >= 0; i--)
        {
            if (p.charAt(i) == '*')
            {
                for (int j = s.length() - 1; j >= 0; j--)
                {
                    match[j] = match[j] || match[j + 1] && (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j));
                }
                i--;
            }
            else
            {
                for (int j = 0; j < s.length(); j++)
                {
                    match[j] = match[j + 1] && (p.charAt(i) == '.' || p.charAt(i) == s.charAt(j));
                }
                match[s.length()] = false;
            }
        }
        return match[0];

//        String string1=s;
//        String string2=p;
//        int length1=string1.length();
//        int length2=string2.length();
//
////        如果两个都为空串，返回true
//        if (string2==null){
//            if (string1==null){
//            return true;}
//            else return false;
//        }
//
////        取一个MAP记录*的位置，和字符
//        HashMap<Integer,Character> mapLocation=new HashMap<Integer, Character>();
//
//        for (int i = 0; i <length2 ; i++)
//        {
//            if (string2.charAt(i)==42){
//                mapLocation.put(i,string2.charAt(i-1));
//            }
//
//        }
    }
}
