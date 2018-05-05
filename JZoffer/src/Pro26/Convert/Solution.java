package Pro26.Convert;

import java.util.Stack;

/*输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
        要求不能创建任何新的结点，只能调整树中结点指针的指向。*/

public class Solution
{
    //    中序遍历的非递归算法
    public TreeNode Convert(TreeNode pRootOfTree)
    {
        if (pRootOfTree == null)
        {
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = pRootOfTree;
//        pre保存中序遍历的上一个节点
        TreeNode pre = null;
        boolean isFirst = true;
        while (p != null || !stack.isEmpty())
        {
//            左子树入栈
            while (p != null)
            {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if (isFirst)
            {
                pRootOfTree = p;
                pre = pRootOfTree;
                isFirst = false;
            }
            else
            {
                pre.right = p;
                p.left = pre;
                pre = p;
            }
            p = p.right;
        }
        return pRootOfTree;
    }

    //    递归
    public TreeNode Convert1(TreeNode pRootOfTree)
    {
        if (pRootOfTree == null)
        {
            return null;
        }
        if (pRootOfTree.left == null && pRootOfTree.right == null)
        {
            return pRootOfTree;
        }

//        将左子树构成双链表，并返回链表头结点
        TreeNode left = Convert1(pRootOfTree.left);
        TreeNode p = left;

//        定位到左子树双链表最后一个结点
        while (p != null && p.right != null)
        {
            p = p.right;
        }

//        左子树链表不为空，将当前root追加到左子树
        if (left != null)
        {
            p.right = pRootOfTree;
            pRootOfTree.left = p;
        }

//        将右子树构造成双链表，并返回链表头结点
        TreeNode right = Convert1(pRootOfTree.right);

//        如果右子树链表不为空，追加到root结点之后
        if (right != null)
        {
            right.left = pRootOfTree;
            pRootOfTree.right = right;
        }
        return left != null ? left : pRootOfTree;
    }
}

