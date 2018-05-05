package Pro08.JumpFloor;

//一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
//n级台阶一种跳法，增加一级台阶（n+1），第n+1阶可以选择跳一阶或者直接跳两阶台阶
// 第一种方案，就是与n阶的方法次数一样，第二种方案，就是与n-1阶的方案一样
//还是一个斐波那契数列


public class Solution
{
    public int JumpFloor(int target)
    {
        if (target == 1)
        {
            return 1;
        }
        else if (target == 2)
        {
            return 2;
        }
        else
        {
            return JumpFloor(target - 1) + JumpFloor(target - 2);
        }
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int sum = solution.JumpFloor(5);
        System.out.println(sum);
    }

}
