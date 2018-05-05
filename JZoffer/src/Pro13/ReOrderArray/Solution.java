package Pro13.ReOrderArray;
/*输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
        使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
        并保证奇数和奇数，偶数和偶数之间的相对位置不变。*/

public class Solution
{
//    插入排序
    public void reOrderArray(int[] array)
    {
        if (array == null || array.length == 0)
        {
            return;
        }

        for (int i = 0; i < array.length; i++)
        {
            if (!isEven(array[i]))
            {
                int temp = array[i];
                int j = i - 1;
                while (j >= 0 && isEven(array[j]))
                {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = temp;
            }
        }


    }

    boolean isEven(int n)
    {
        if (n % 2 == 0)
        {
            return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int[] array = {1, 2, 4, 3, 6, 5, 7, 8};
        solution.reOrderArray(array);
        for (int i = 0; i < array.length; i++)
        {
            System.out.println(array[i]);
        }
    }
}
