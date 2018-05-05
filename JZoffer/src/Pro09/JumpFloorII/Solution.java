package Pro09.JumpFloorII;

//一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
//归纳法，第n阶台阶的次数f(n)=2*f(n-1)

public class Solution
{
    public int JumpFloorII(int target)
    {
        if (target == 0)
        {
            return 0;
        }
        else if (target == 1)
        {
            return 1;
        }
        else
        {
            return 2 * JumpFloorII(target - 1);
        }
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int num = solution.JumpFloorII(20);
        System.out.println(num);
    }
}
