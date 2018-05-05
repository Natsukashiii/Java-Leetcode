package Pro24.FindPath;

import java.util.ArrayList;
import java.util.Stack;

/*输入一颗二叉树和一个整数，
        打印出二叉树中结点值的和为输入整数的所有路径。
        路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。*/

public class Solution
{
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target)
    {
        ArrayList<ArrayList<Integer>> pathlist = new ArrayList<ArrayList<Integer>>();
        if (root == null)
        {
            return pathlist;
        }
        Stack<Integer> stack = new Stack<Integer>(); //基本数据类型不能用作泛型
        FindPath(root,target,stack,pathlist);
        return pathlist;
    }

    //类似先序遍历，path作为辅助栈，pathlist存储路径
    public void FindPath(TreeNode root, int target, Stack<Integer> path, ArrayList<ArrayList<Integer>> pathlist)
    {
        if (root == null)
        {
            return;
        }
//        只有根节点的情况下
        if (root.left == null && root.right == null)
        {
//            根节点的值=target
            if (root.val == target)
            {
//                新建list存储每一条路径
                ArrayList<Integer> list = new ArrayList<Integer>();
//                在栈中存储路径
                for (int i : path)
                {
                    list.add(new Integer(i));
                }
                list.add(new Integer(root.val));
                pathlist.add(list);
            }
        }
        else
        {
            path.push(new Integer(root.val));
            FindPath(root.left, target - root.val, path, pathlist);
            FindPath(root.right, target - root.val, path, pathlist);
//            不满足条件回溯
            path.pop();
        }
    }

    private ArrayList<ArrayList<Integer>> listArrayList = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> FindPath1(TreeNode root, int target)
    {
        if (root == null)
        {
            return listArrayList;
        }
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null)
        {
            listArrayList.add(new ArrayList<Integer>(list));
        }
        FindPath1(root.left, target);
        FindPath1(root.right, target);
        list.remove(list.size() - 1);
        return listArrayList;
    }
}
