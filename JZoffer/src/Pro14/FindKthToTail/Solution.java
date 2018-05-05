package Pro14.FindKthToTail;
/*
输入一个链表，输出该链表中倒数第k个结点。
*/

//两个指针指向head头结点，p1指针先跑k-1个结点，
//此时p2结点开始跑，p1到结尾时，p2正好到倒数第k个结点


public class Solution
{
    //    直接遍历链表，再找到第n-k+1个结点
    public ListNodes FindKthToTail1(ListNodes head, int k)
    {
        if (head == null || k < 0)
        {
            return null;
        }
        int n = 0;
        ListNodes newHead = new ListNodes(0);
        newHead = head;
        while (newHead.next != null)
        {
            n++;
            newHead = newHead.next;
        }
        for (int i = 0; i < n - k + 1; i++)
        {
            head = head.next;
        }
        return head;
    }

    public ListNodes FindKthToTail(ListNodes head, int k)
    {
        if (head == null || k < 0)
        {
            return null;
        }
        ListNodes p1 = head, p2 = head;
        int a = k;
        int count = 0;
        while (p1 != null)
        {
            p1 = p1.next;
            count++;
            if (k < 1)
            {
                p2 = p2.next;
            }
            k--;
        }
        if (count < a)
        {
            return null;
        }
        return p2;
    }
}