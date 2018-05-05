package Pro04.ReConstructBinaryTree;

import java.util.Arrays;

public class Solution
{
    //    递归,先序的第一个数字是根节点，由此在中序中区分出左右子树，再在左右子树中重复该步骤
    public TreeNodes reConstructBinaryTree(int[] pre, int[] in)
    {
        if (pre.length == 0 || in.length == 0)
        {
            return null;
        }
        TreeNodes root = new TreeNodes(pre[0]);
        for (int i = 0; i < in.length; i++)
        {
            if (pre[0] == in[i])
            {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return root;
    }

    public TreeNodes reConstructBinaryTree1(int[] pre, int[] in)
    {
        TreeNodes root = new TreeNodes(pre[0]);

        int inRootIndex = 0;
        for (int i = 0; i < in.length; i++)
        {
            if (in[i] == root.val)
            {
                inRootIndex = i;
                break;
            }
        }

        int[] leftin=Arrays.copyOfRange(in,0,inRootIndex); //取的是index为0到inRootIndex-1的数组
        int[] rightin=Arrays.copyOfRange(in,inRootIndex+1,in.length);

        int[] leftpre=Arrays.copyOfRange(pre,1,1+inRootIndex);
        int[] rightpre=Arrays.copyOfRange(pre,inRootIndex+1,pre.length);

        root.left=reConstructBinaryTree(leftpre,leftin);
        root.right=reConstructBinaryTree(rightpre,rightin);

        return root;
    }

    //    中序打印
    public void print(TreeNodes treeNodes)
    {
        if (treeNodes != null)
        {
            print(treeNodes.left);
            System.out.println(treeNodes.val);
            print(treeNodes.right);
        }
    }

    public static void main(String[] args)
    {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        Solution solution = new Solution();
        TreeNodes root = new TreeNodes(0);

        root = solution.reConstructBinaryTree1(pre, in); //root保存重建的二叉树

        solution.print(root);
    }
}
