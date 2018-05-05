package Pro11.NumberOf1;
//输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
public class Solution
{
    public int NumberOf1(int target)
    {
        int count = 0;
        int flag = 1;
        while (flag != 0)
        {
            if ((target & flag) != 0)
            {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    public static void main(String[] args)
    {
        Solution solution=new Solution();
        int n=-10;
        System.out.println(solution.NumberOf1(n));
    }

}
