package Pro23.MergeTwoLists;

public class Solution
{
    public ListNode mergeKLists(ListNode[] lists)
    {
        int len = lists.length;

        if (lists == null || len == 0)
        {
            return null;
        }
        if (len == 1)
        {
            return lists[0];
        }

        //基于“二分”思想进行链表的两两组合
        while (len > 1)
        {
            int mid = (len + 1) / 2;
            for (int i = 0; i < len / 2; i++)
            {
                lists[i] = mergeTwoLists(lists[i], lists[i + mid]);
            }
            len = mid;
        }
        return lists[0];
    }

    //有序链表的组合，L1和L2为头结点，归并排序的核心思想
    public ListNode mergeTwoLists(ListNode L1, ListNode L2)
    {
        if (L1 == null)
        {
            return L2;
        }
        if (L2 == null)
        {
            return L1;
        }

        //保存结果的链表，头结点初始化
        ListNode head = new ListNode(0);
        ListNode phead = head;

        //两个链表归并排序
        while (L1 != null && L2 != null)
        {
            if (L1.val <= L2.val)
            {
                //接入结果链表
                phead.next = L1;
                //移动指针
                phead = phead.next;
                L1 = L1.next;
            }
            else
            {
                phead.next = L2;
                phead = phead.next;
                L2 = L2.next;
            }
        }
        if (L1 != null)
        {
            phead.next = L1;
        }
        else
        {
            phead.next = L2;
        }

        //初始化的第一个节点不属于结果
        return head.next;
    }
}
