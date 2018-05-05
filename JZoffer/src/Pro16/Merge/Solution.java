package Pro16.Merge;

//输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。

public class Solution
{
    //    非递归，直接list2插入list1生成新的链表
    public ListNode Merge(ListNode list1, ListNode list2)
    {
        if (list1 == null && list2 != null)
        {
            return list2;
        }
        if (list2 == null && list1 != null)
        {
            return list1;
        }
        if (list1 == null && list2 == null)
        {
            return null;
        }

        ListNode newList = null;
        ListNode temp = null;
        while (list1 != null || list2 != null)
        {
            if (list2.next.val >= list1.next.val)
            {
                if (newList == null)
                {
                    newList = list1;
                }
                else
                {
                    temp.next = list1;
                    temp = temp.next;
                }
                list1 = list1.next;
            }
            else
            {
                if (newList == null)
                {
                    newList = list2;
                }
                else
                {
                    temp.next = list2;
                    temp = temp.next;
                }
            }
            if (list1 == null)
            {
                temp.next = list2;
            }
            else
            {
                temp.next = list1;
            }
        }
        return newList;
    }

    //    递归
    public ListNode Merge1(ListNode list1, ListNode list2)
    {
        if (list1 == null)
        {
            return list2;
        }
        if (list2 == null)
        {
            return list1;
        }
        if (list1.val <= list2.val)
        {
            list1.next = Merge1(list1.next, list2);
            return list1;
        }
        else
        {
            list2.next = Merge1(list1, list2.next);
            return list2;
        }
    }
}
