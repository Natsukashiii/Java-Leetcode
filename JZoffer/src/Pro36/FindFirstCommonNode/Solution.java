package Pro36.FindFirstCommonNode;

import java.util.HashMap;

/*输入两个链表，找出它们的第一个公共结点。*/

public class Solution
{
    //    hashMap
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2)
    {
        ListNode current1 = pHead1;
        ListNode current2 = pHead2;

        HashMap<ListNode, Integer> hashMap = new HashMap<>();
        while (current1 != null)
        {
            hashMap.put(current1, null);
            current1 = current1.next;
        }
        while (current2 != null)
        {
            if (hashMap.containsKey(current2))
            {
                return current2;
            }
            current2 = current2.next;
        }
        return null;
    }

    //    算两个链表长度，长的链表先把长度差走了，再开始比较
    public ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2)
    {
        ListNode current1 = pHead1;
        ListNode current2 = pHead2;
        if (pHead1 == null || pHead2 == null)
        {
            return null;
        }
        int length1 = getLength(current1);
        int length2 = getLength(current2);

//        比较两个链表的长度，长的链表先把长度差走了
        if (length1 >= length2)
        {
            int shortLength = length1 - length2;
            while (shortLength > 0)
            {
                current1 = current1.next;
                shortLength--;
            }
        }
        else if (length2 > length1)
        {
            int shortLength = length2 - length1;
            while (shortLength > 0)
            {
                current2 = current2.next;
                shortLength--;
            }
        }
        while (current1 != current2)
        {
            current1 = current1.next;
            current2 = current2.next;
        }
        return current1;
    }

    public static int getLength(ListNode pHead)
    {
        int length = 0;
        ListNode current = pHead;
        while (current != null)
        {
            length++;
            current = current.next;
        }
        return length;
    }

}
