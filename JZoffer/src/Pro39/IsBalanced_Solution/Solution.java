package Pro39.IsBalanced_Solution;


/*输入一棵二叉树，判断该二叉树是否是平衡二叉树。*/

public class Solution
{
    public boolean isBalanced = true;

    public boolean IsBalanced_Solution(TreeNode root)
    {
        getDepth(root);
        return isBalanced;
    }

    public int getDepth(TreeNode treeNode)
    {
        if (treeNode == null)
        {
            return 0;
        }
        int left = getDepth(treeNode.left);
        int right = getDepth(treeNode.right);

        if (Math.abs(left - right) > 1)
        {
            isBalanced = false;
        }
        return right > left ? right + 1 : left + 1;
    }

}
