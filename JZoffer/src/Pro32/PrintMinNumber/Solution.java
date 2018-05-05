package Pro32.PrintMinNumber;

/*输入一个正整数数组，
        把数组里所有数字拼接起来排成一个数，
        打印能拼接出的所有数字中最小的一个。
        例如输入数组{3，32，321}，
        则打印出这三个数字能排成的最小数字为321323。*/


import java.util.ArrayList;
import java.util.Collections;

public class Solution
{

//    自定义一个比较大小的函数，比较s1，s2大小，先拼接
    public String PrintMinNumber(int[] numbers)
    {
        String string = "";
        for (int i = 0; i < numbers.length; i++)
        {
            for (int j = i + 1; j < numbers.length; j++)
            {
                int a = Integer.valueOf(numbers[i] + "" + numbers[j]);
                int b = Integer.valueOf(numbers[j] + "" + numbers[i]);
                if (a > b)
                {
                    int t = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = t;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++)
        {
            string += String.valueOf(numbers[i]);
        }
        return string;
    }

//    public static void arrange(String[] strings, int st, int len)
//    {
//        if (st == len - 1)
//        {
//            for (int i = 0; i < len; i++)
//            {
//
//            }
//        }
//        else {
//            for (int i = st; i <len ; i++)
//            {
//                swap(strings,st,i);
//                arrange(strings,st+1,len);
//                swap(strings,st,i);
//            }
//        }
//    }

//    public static void swap(String[] strings, int i, int j)
//    {
//        String temp = new String();
//        temp = strings[i];
//        strings[i] = strings[j];
//        strings[j] = temp;
//    }
}
