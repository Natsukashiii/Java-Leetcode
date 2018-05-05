package Pro45.LastRemaining_Solution;

/*每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,
        今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
        其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
        然后,他随机指定一个数m,让编号为0的小朋友开始报数。
        每次喊到m-1的那个小朋友要出列唱首歌,
        然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,
        从他的下一个小朋友开始,继续0...m-1报数....这样下去....
        直到剩下最后一个小朋友,可以不用表演,
        并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
        请你试着想下,哪个小朋友会 得到这份礼品呢？(注：小朋友的编号是从0到n-1)*/

public class Solution
{
    public int LastRemaining_Solution(int n, int m)
    {
        if (n < 1 || m < 1)
        {
            return -1;
        }
        int[] array = new int[n];
        int i = -1, step = 0, count = n;
//        最后一个元素也设为了-1
        while (count > 0)
        {
            i++; //指向上一个被删除对象的下一个元素
            if (i >= n) //环
            {
                i = 0;
            }
            if (array[i] == -1)
            {
                continue; //跳过被删除的对象
            }
            step++;   //记录已经走过的对象
            if (step == m)  //找到待删除的对象
            {
                array[i] = -1;
                step = 0;
                count--;
            }
        }
        return i;
    }

}
