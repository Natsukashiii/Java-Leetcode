package Pro12.Power;

public class Solution
{

    //    复杂度O(n)
    public double Power(double base, int exponent)
    {
        double result = 1;
        for (int i = 0; i < Math.abs(exponent); i++)
        {
            result *= base;
        }
        if (exponent < 0)
        {
            result = 1 / result;
        }
        return result;
    }

    //    递归
    public double Power1(double base, int exponent)
    {
        int n = Math.abs(exponent);
        if (n == 0)
        {
            return 1;
        }
        if (n == 1)
        {
            return base;
        }

        double result = Power(base, n >> 1);
        result *= result;

        if ((n & 1) == 1)
        {
            result *= base;
        }
        if (exponent < 0)
        {
            result = 1 / result;
        }
        return result;
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        double sum = solution.Power1(5.2, 4);
        System.out.println(sum);
    }
}
