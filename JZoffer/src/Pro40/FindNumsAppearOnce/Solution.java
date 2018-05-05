package Pro40.FindNumsAppearOnce;

//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果

/*一个整型数组里除了两个数字之外，其他的数字都出现了两次。
        请写程序找出这两个只出现一次的数字。*/

public class Solution
{
    //    异或：任何一个数字异或他自己都是0
//    先异或之后，剩下的数字是AB异或的结果，结果中二进制中的1是A和B的不同位
//    取第一个1所在的位数n，把原数组分为两组，分组标准是第n位是否是1
//    所以相同的数肯定在一个组，不同的数不在一个组
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[])
    {
        int length = array.length;
        if (length == 2)
        {
            num1[0] = array[0];
            num2[0] = array[1];
            return;
        }

        int bitResult = 0;
        for (int i = 0; i < length; i++)
        {
            bitResult ^= array[i];
        }

        int index = findFirst1(bitResult);
        for (int i = 0; i < length; i++)
        {
            if (isBit1(array[i], index))
            {
                num1[0] ^= array[i];
            }
            else
            {
                num2[0] ^= array[i];
            }
        }
    }

    private int findFirst1(int bitResult)
    {
        int index = 0;
        while (((bitResult & 1) == 0) && index < 32)
        {
            bitResult >>= 1;
            index++;
        }
        return index;
    }

    private boolean isBit1(int target, int index)
    {
        return ((target >> index) & 1) == 1;
    }
}
