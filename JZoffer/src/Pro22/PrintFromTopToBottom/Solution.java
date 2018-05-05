package Pro22.PrintFromTopToBottom;

import java.util.ArrayList;

//层次打印二叉树

public class Solution
{
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();

        if (root == null)
        {
            return list;
        }
        queue.add(root);

        while (queue.size() != 0)
        {
//            arraylist被当做队列使用
            TreeNode temp = queue.remove(0);
            if (temp.left != null)
            {
                queue.add(temp.left);
            }
            if (temp.right != null)
            {
                queue.add(temp.right);
            }
            list.add(temp.val);
        }
        return list;
    }
}
