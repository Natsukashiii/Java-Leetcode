package Pro31.NumberOf1Between1AndN_Solution;

/*求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
        为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
        ,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
        可以很快的求出任意非负整数区间中1出现的次数。*/

public class Solution
{
//    常规方法，对每个数除10，判断个位数是不是1
    public int NumberOf1Between1AndN_Solution(int n)
    {
        int sum = 0;
        if (n == 0)
        {
            return sum;
        }
        for (int i = 0; i <= n; ++i)
        {
            sum += numOf1(i);
        }
        return sum;
    }

    public int numOf1(int n)
    {
        int num = 0;
        while (n > 0)
        {
            if (n % 10 == 1)
            {
                num++;
            }
            n = n / 10;
        }
        return num;
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int sum = solution.NumberOf1Between1AndN_Solution(13);
        System.out.println(sum);
    }
}
