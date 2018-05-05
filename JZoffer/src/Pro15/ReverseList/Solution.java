package Pro15.ReverseList;

//输入一个链表，反转链表后，输出链表的所有元素。

public class Solution
{
    public ListNode ReverseList(ListNode head)
    {
        if (head == null)
        {
            return null;
        }

        ListNode pre = null;
        ListNode next = null;

//        next保存下一个结点的信息，以免失去head节点
//        让head指向pre（pre初始为null）
//        head后移变成next，pre指向head
        while (head != null)
        {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        ListNode node = null;
        for (int i = 0; i < 10; i++)
        {
            node.val = i;
            node = node.next;
        }
        solution.ReverseList(node);
    }
}
