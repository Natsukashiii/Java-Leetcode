package Pro12.IntToRoman;

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.close();
        Solution solution = new Solution();
        System.out.println(solution.intToRoman(num));

    }


    public String intToRoman(int num)
    {
        if (num < 1 || num > 3999)
        {
            return "You have entered a wrong number";
        }
        String digit[] = {"", "I", "II", "III", "IV", "V", "VI", "VII",
                "VIII", "IX"};
        String ten[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX",
                "XC"};
        String hundred[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC",
                "DCCC", "CM"};
        String thousand[] = {"", "M", "MM", "MMM"};
        String str = "";
        return str = thousand[num / 1000] + hundred[num % 1000 / 100] + ten[num % 100 / 10] + digit[num % 10];

    }
}
