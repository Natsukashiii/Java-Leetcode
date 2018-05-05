package Pro33.GetUglyNumber_Solution;



/*
把只包含因子2、3和5的数称作丑数（Ugly Number）。
        例如6、8都是丑数，但14不是，因为它包含因子7。
        习惯上我们把1当做是第一个丑数。
        求按从小到大的顺序的第N个丑数。
*/

import javax.sound.midi.Soundbank;

public class Solution
{
    public int GetUglyNumber_Solution(int index)
    {
        if (index == 0 || index == 1)
        {
            return index;
        }
        int number = 0;
        int foundIndex = 0;
        if (foundIndex < index)
        {
            number++;
            if (isUgly(number))
            {
                foundIndex++;
            }
        }
        return number;
    }

    boolean isUgly(int num)
    {
        if (num % 2 == 0)
        {
            num /= 2;
        }
        if (num % 3 == 0)
        {
            num /= 3;
        }
        if (num % 5 == 0)
        {
            num /= 5;
        }
        return (num == 1) ? true : false;
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int isUglyNumber = solution.GetUglyNumber_Solution(25);
        System.out.println(isUglyNumber);

    }
}
