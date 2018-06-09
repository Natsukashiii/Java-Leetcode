package Pro09.PalindromeNumber;

//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        int x=scanner.nextInt();
    }
    public boolean isPalindrome(int x) {
        String number=String.valueOf(x);
        int length=number.length();

        if (x<1||length%2==0){
            return false;
        }
        else {
            for (int i = 0; i <(length+1)/2 ; i++)
            {
                for (int j = length; j >(length+1)/2 ; j--)
                {
                    
                }
            }
        }




    }
}
