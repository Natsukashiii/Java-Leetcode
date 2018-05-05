package Pro46.Sum_Solution;

//求1+2+3+...+n，要求不能使用乘除法、
//        for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

import java.util.Scanner;

public class Solution
{
    public int Sum_Solution(int n)
    {
        int result = 0;
        int a = 1;
        boolean value = ((n != 0) && a == (result = Sum_Solution(n - 1)));
        result += n;
        return result;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        scanner.close();
        Solution solution = new Solution();
        sum = solution.Sum_Solution(n);
        System.out.println(n);

    }
}
