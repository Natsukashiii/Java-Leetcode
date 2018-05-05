package Pro37.GetNumberOfK;

import java.util.HashMap;

public class Solution
{
    //    hashMap
    public int GetNumberOfK(int[] array, int k)
    {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap = sumNum(array);
        int position = findPosition(array, k);
        return hashMap.get(array[position]);
    }

    private int findPosition(int[] array, int k)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (k == array[i])
            {
                return i;
            }
            else
            {
                return 0;
            }
        }
        return 0;
    }

    private HashMap<Integer, Integer> sumNum(int[] array)
    {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < array.length; i++)
        {
            if (hashMap.containsKey(array[i]))
            {
                int num = hashMap.get(array[i]);
                hashMap.put(array[i], ++num);
            }
            else
            {
                hashMap.put(array[i], 1);
            }
        }
        return hashMap;
    }

    //    二分查找
    public int GetNumberOfk1(int[] array, int k)
    {
        int length = array.length;
        if (length == 0)
        {
            return 0;
        }
        int firstK = getFirstK(array, k, 0, length - 1);
        int lastK = getLastK(array, k, 0, length - 1);
        if (firstK != -1 && lastK != -1)
        {
            return lastK - firstK + 1;
        }
        return 0;
    }

    private int getFirstK(int[] array, int k, int start, int end)
    {
        if (start > end)
        {
            return -1;
        }
        int mid = (start + end) >> 1;
        if (array[mid] > k)
        {
            return getFirstK(array, k, start, mid - 1);
        }
        else if (array[mid] < k)
        {
            return getFirstK(array, k, mid + 1, end);
        }
        else if (mid - 1 >= 0 && array[mid - 1] == k)
        {
            return getFirstK(array, k, start, mid - 1);
        }
        else
        {
            return mid;
        }
    }

    private int getLastK(int[] array, int k, int start, int end)
    {
        int length = array.length;
        int mid = (start + end) >> 1;
        while ((start <= end))
        {
            if (array[mid] > k)
            {
                end = mid - 1;
            }
            else if (array[mid] < k)
            {
                start = mid + 1;
            }
            else if (mid + 1 < length && array[mid + 1] == k)
            {
                start = mid + 1;
            }
            else
            {
                return mid;
            }
            mid = (start + end) >> 1;
        }
        return -1;
    }
}
