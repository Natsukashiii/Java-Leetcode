package Pro07.Fibonacci;
//输出Fibonacci的第n个数

public class Solution
{
    public int Fibonacci(int n)
    {
        if (n == 0)
        {
            return 0;
        }
        if (n == 1)
        {
            return 1;
        }
        int f1 = 0;
        int f2 = 1;
        int num = 0;
        for (int i = 2; i <= n; i++)
        {
            num = f1 + f2;
            f1 = f2;
            f2 = num;
        }
        return num;
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int num = solution.Fibonacci(5);
        System.out.println(num);
    }
}
