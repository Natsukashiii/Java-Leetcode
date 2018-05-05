package Pro03.PrintArrayList;

import java.util.ArrayList;
import java.util.Stack;

//从尾到头打印一个链表每个节点的值
//先反转再打印

public class Solution
{
    ArrayList<Integer> list = new ArrayList<Integer>();

//    递归
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode)
    {
        if (listNode != null)
        {
            this.printListFromTailToHead(listNode.next);
            list.add(listNode.val); //开始执行的时候，已经递归到最后一个数，从最后一个数开始加入list
        }
        return list;

    }

//    栈
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode)
    {
        Stack<Integer> stack = new Stack<Integer>();
        while (listNode != null)
        {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        while (!stack.isEmpty())
        {
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        AddList list = new AddList();
        for (int i = 0; i < 10; i++)
        {
            ListNode listNode = new ListNode(i);
            list.add(listNode);
        }
        ListNode head = list.head;
        ArrayList<Integer> integers = solution.printListFromTailToHead(head);
        for (Integer i : integers)
        {
            System.out.println(i);
        }
    }


}
