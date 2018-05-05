package Pro28.MoreThanHalfNum_Solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*数组中有一个数字出现的次数超过数组长度的一半，
        请找出这个数字。
        例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
        由于数字2在数组中出现了5次，超过数组长度的一半，
        因此输出2。如果不存在则输出0。*/

public class Solution
{

    //    hashmap的方式
    public int MoreThanHalfNum_Solution(int[] array)
    {
        if (array.length == 0)
        {
            return 0;
        }
        HashMap<Integer, Integer> numList = new HashMap<>();
        for (int i = 0; i < array.length; i++)
        {
            if (!numList.containsKey(array[i]))
            {
                numList.put(array[i], 1);
            }
            else
            {
                int count = numList.get(array[i]);
                numList.put(array[i], count++);
            }
            Iterator iter = numList.entrySet().iterator();
            while (iter.hasNext())
            {
                Map.Entry entry = (Map.Entry) iter.next();
                Integer key = (Integer) entry.getKey();
                Integer val = (Integer) entry.getValue();
                if (val > array.length / 2)
                {
                    return key;
                }
            }
        }
        return 0;
    }

    //    排序的方式，如果存在这样一个数，一定出现在数组中间
    public int MoreThanHalfNum_Solution1(int[] array)
    {
        Arrays.sort(array);
        int count = 0;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == array[array.length / 2])
            {
                count++;
            }
        }
        if (count > array.length / 2)
        {
            return array[array.length / 2];
        }
        return 0;
    }
}
