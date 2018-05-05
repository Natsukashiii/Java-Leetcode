package Pro18.Mirror;

public class Solution
{
//    递归
    public void Mirror(TreeNode root)
    {
        TreeNode temp = null;
        if (root != null)
        {
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            if (root.left != null)
            {
                Mirror(root.left);
            }
            if (root.right != null)
            {
                Mirror(root.right);
            }
        }
    }
}
