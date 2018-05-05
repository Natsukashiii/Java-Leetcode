package Pro23.VerifySquenceOfBST;

/*输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
        如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。*/

public class Solution
{
    public boolean VerifySquenceOfBST(int[] sequence)
    {
        if (sequence.length == 0)
        {
            return false;
        }

//        如果是后序序列，最后一个元素是根，除去根，序列的前一段左子树小于后一段右子树，且分别都是后序序列
        if (sequence.length == 1)
        {
            return true;
        }
        return judge(sequence, 0, sequence.length - 1);

    }

    public boolean judge(int[] a, int start, int root)
    {
//        只有右子树,是一个后序序列
        if (start >= root)
        {
            return true;
        }

        int i = root;

//        由于后序序列 左子树 右子树 根，且左子树<根<右子树 ，用i找到比根小的数的第一个坐标
        while (i > start && a[i - 1] > a[root]) i--;

//        如果在左子树这段找到比根大的数，则返回false，反之则继续递归在左右子树中判断
        for (int j = start; j < i - 1; j++)
        {
            if (a[j] > a[root])
            {
                return false;
            }
        }
        return judge(a, start, i - 1) && judge(a, i, root - 1);
    }
}
