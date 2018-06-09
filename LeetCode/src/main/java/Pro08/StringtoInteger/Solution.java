package Pro08.StringtoInteger;

//    如果全为数字，返回数字，但若数字大于
//    如果开头第一个不为空的字符是+或—号，返回正负
//    如果开头不是数字，返回0
//写的太丑陋了。。。。。。
import javax.sound.midi.Soundbank;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println("Please enter the word:");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        scanner.close();
        Solution solution = new Solution();
        int res = solution.myAtoi(str);
        System.out.println(res);
    }

    public int myAtoi(String str)
    {
        int number = 1;
        int length = str.length();
//        此时的头部第一位字符是还没有判断为空的情况
        char topStr = str.charAt(0);
        int flag = 0;
        int negativeFlag = 1;
        double doublenumber;

        BigInteger bigNumber = null;
        BigInteger INT_MAX = BigInteger.valueOf((long) (Math.pow(2, 31) - 1));
        BigInteger INT_MIN = BigInteger.valueOf((long) ((-1) * Math.pow(2, 31)));

//        判断首位字符是否为空
//        loop1:
//        if (topStr == 0)
//        {
//            for (int i = 0; i < length; i++)
//            {
//                topStr = str.charAt(i);
//                if (topStr != 0)
//                {
//                    flag = i;
//                    break loop1;
//                }
//            }
//        }

//        直接trim，在第一位字符为空的情况下取出不为空的字符串
        String string = str.trim();
        length = string.length();
        topStr = string.charAt(0);

        if (topStr == 43)
        {
            string = string.substring(1, length);
            length = string.length();
            topStr = string.charAt(0);
        }
        else if (topStr == 45)
        {
            negativeFlag = -1;
            string = string.substring(1, length);
            length = string.length();
            topStr = string.charAt(0);
        }

//        标记第几位开始不是数字
        int numFlag = 0;

//        如果第一位数字的ASCII码大于58，则第一位字符不是正负号，无法判断该字符串的值，则返回0
//        最后一种情况是整个字符串为空串的情况下。
        if (topStr >= 65 & topStr <= 90 || topStr >= 97 & topStr <= 122 || length == 0)
        {
            return 0;
        }


//        对第一位数是数字的，但可能一整行并不是数字的进行字符转换
        else if (topStr >= 48 & topStr <= 57)
        {
            //        对首位判断是+号或者负号的，进行运算
//            如果第一位是数字，最末位也是数字的话，直接输出str-9ing转成int类
            if (string.charAt(length - 1) >= 48 & string.charAt(length - 1) <= 57)
            {
                try
                {
                    number=(int)Double.parseDouble(string);
                    bigNumber = BigInteger.valueOf(Long.parseLong(string.trim()));
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                    doublenumber=Double.parseDouble(string);
                }
            }
            else
            {
                loop2:
                for (int i = 1; i < length; i++)
                {
                    char temp = string.charAt(i);
                    if (temp > 57 || temp < 48)
                    {
                        numFlag = i;
                        break;
                    }
                }
                String numString = string.substring(0, numFlag);
                try
                {
                    bigNumber = BigInteger.valueOf(Long.parseLong(numString.trim()));
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
        }

        if (negativeFlag == -1)
        {
            BigInteger bigNegativeFlag = new BigInteger("-1");
            bigNumber = bigNumber.multiply(bigNegativeFlag);
        }
        if (bigNumber.compareTo(INT_MAX) > 0)
        {
            bigNumber = INT_MAX;
        }
        if (bigNumber.compareTo(INT_MIN) < 0)
        {
            bigNumber = INT_MIN;
        }
        number = bigNumber.intValue();
        return number;
    }

}
