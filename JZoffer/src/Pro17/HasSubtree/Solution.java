package Pro17.HasSubtree;

public class Solution
{
    public boolean HasSubtree(TreeNode root1, TreeNode root2)
    {
        boolean result = false;
        if (root2 != null && root1 != null)
        {
            if (root1.val == root2.val)
            {
                result = doesTree1HaveTree2(root1, root2);
            }
            if (!result)
            {
                result = HasSubtree(root1.left, root2);
            }
            if (!result)
            {
                result = HasSubtree(root1.right, root2);
            }
        }
        return result;
    }

//    递归遍历两棵树，一个点对应不上，则返回false
    public static boolean doesTree1HaveTree2(TreeNode node1, TreeNode node2)
    {
        if (node2 == null)
        {
            return true;
        }
        if (node1 == null)
        {
            return false;
        }
        if (node1.val != node2.val)
        {
            return false;
        }
        return doesTree1HaveTree2(node1.left, node2.left) && doesTree1HaveTree2(node1.right, node2.right);
    }
    private TreeNode root;

    //    递归创建二叉树
    public void bulidTree(TreeNode node, int data)
    {
        if (root == null)
        {
            root = new TreeNode(data);
        }
        else
        {
            if (data < node.val)
            {
                if (node.left == null)
                {
                    node.left = new TreeNode(data);
                }
                else
                {
                    bulidTree(node.left, data);
                }
            }
            else
            {
                if (node.right == null)
                {
                    node.right = new TreeNode(data);
                }
                else
                {
                    bulidTree(node.right, data);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(1);

        solution.bulidTree(treeNode, 6);
        solution.bulidTree(treeNode, 5);
        solution.bulidTree(treeNode, 7);

        for (int i = 5; i < 12; i++)
        {
            solution.bulidTree(treeNode1, i);
        }

//        if (solution.isTraversal(treeNode, 6) != null)
        if (solution.HasSubtree(treeNode1, treeNode))
        {
            System.out.println("有");
        }
        else
        {
            System.out.println("无");
        }
    }

}
