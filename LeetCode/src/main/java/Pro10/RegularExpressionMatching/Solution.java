//package Pro10.RegularExpressionMatching;
//
///*给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
//
//        '.' 匹配任意单个字符。
//        '*' 匹配零个或多个前面的元素。*/
//
////1. 如果p为空，则只有s为空的情况下才匹配成功，否则匹配失败
////2. ru
//
//import java.util.HashMap;
//
//public class Solution
//{
//    public boolean isMatch(String s, String p) {
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
//    }
//}
