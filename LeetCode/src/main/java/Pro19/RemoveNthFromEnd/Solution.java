package Pro19.RemoveNthFromEnd;

public class Solution
{
    public ListNode removeNthFromEnd(ListNode head, int n)
    {
        ListNode first = new ListNode(0);
        first.next = head;
        ListNode p1 = first;
        ListNode p2 = first;
        int k = 0;
        while (k < n)
        {
            if (p2.next == null)
                return head;
            p2 = p2.next;
            k++;
        }
        while (true)
        {
            if (p2.next == null)
            {
                p1.next = p1.next.next;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return first.next;
    }

}
