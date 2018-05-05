package Pro38.TreeDepth;

import Pro17.HasSubtree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*输入一棵二叉树，求该树的深度。
        从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
        最长路径的长度为树的深度。*/

public class Solution
{
    //    递归
    public int TreeDepth(Pro38.TreeDepth.TreeNode root)
    {
        if (root == null)
        {
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return Math.min(left, right) + 1;
    }

    //    非递归
    public int TreeDepth1(Pro38.TreeDepth.TreeNode root)
    {
        if (root == null)
        {
            return 0;
        }
        Queue<Pro38.TreeDepth.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        int count = 0;
        int nextCount = 1;
        while (queue.size() != 0)
        {
            Pro38.TreeDepth.TreeNode top = queue.poll();
            count++;
            if (top.left != null)
            {
                queue.add(top.left);
            }
            if (top.right != null)
            {
                queue.add(top.right);
            }
            if (count == nextCount)
            {
                nextCount = queue.size();
                count = 0;
                depth++;
            }
        }
        return depth;
    }
}

