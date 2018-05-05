package Pro35.InversePairs;

/*在数组中的两个数字，如果前面一个数字大于后面的数字，
        则这两个数字组成一个逆序对。输入一个数组,
        求出这个数组中的逆序对的总数P。
        并将P对1000000007取模的结果输出。
        即输出P%1000000007*/

public class Solution
{

//    数组分为前后两个数组，递归到最后每个数字只有一个数据，
//    合并的似乎，前面的数组数值比后面数组数值大时，前面的数组都是大于后面的
    public int InversePairs(int[] array)
    {
        if (array == null || array.length == 0)
        {
            return 0;
        }
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            copy[i] = array[i];
        }
        int count = InversePairsCore(array,copy,0,array.length-1);
        return count;
    }

    private int InversePairsCore(int[] array, int[] copy, int low, int high)
    {
        if (low == high)
        {
            return 0;
        }
        int mid = (low + high) >> 1;
        int leftCount = InversePairsCore(array, copy, low, mid) % 100000007;
        int rightCount = InversePairsCore(array, copy, mid + 1, high) % 100000007;
        int count = 0;
        int i = mid;
        int j = high;
        int locCopy = high;
        while (i >= low && j > mid)
        {
            if (array[i] > array[j])
            {
                count += j - mid;
                copy[locCopy--] = array[i--];
                if (count >= 100000007)
                {
                    count %= 100000007;
                }
            }
            else
            {
                copy[locCopy--] = array[j--];
            }
        }
        for (; i >= low; i--)
        {
            copy[locCopy--] = array[i];
        }
        for (; j > mid; j--)
        {
            copy[locCopy--] = array[j];
        }
        for (int s = low; s <= high; s++)
        {
            array[s] = copy[s];
        }
        return (leftCount + rightCount + count) % 100000007;
    }
}
