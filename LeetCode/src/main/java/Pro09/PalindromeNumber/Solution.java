package Pro09.PalindromeNumber;

//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println("Please enter the number: ");
        Scanner scanner = new Scanner(System.in);
//        double x = scanner.nextDouble();
        int x = scanner.nextInt();
        scanner.close();
        Solution solution = new Solution();
        boolean result = solution.isPalindrome(x);
        System.out.println("result: " + result);
    }

    public boolean isPalindrome(int x)
    {
        String number = String.valueOf(x);
        int length = number.length();
        int l = length;


//        如果这个数是小数，或者小于0的话一定不是回文串
        if (x < 1 & x != 0)
        {
            return false;
        }
//        如果位数为偶数的话，形如1001的情况
        else if (length % 2 == 0 & length >= 2)
        {
            for (int i = 0; i < length/2; i++)
            {
                l--;
                if (number.charAt(i) != number.charAt(l))
                {
                    return false;
                }
            }
            return true;
        }

//        形如12121、123454321的情况
        else
        {
            for (int i = 0; i < (length + 1) / 2; i++)
            {
                l--;
                if (number.charAt(i) != number.charAt(l))
                {
                    return false;
                }
            }
            return true;
        }
    }
}
