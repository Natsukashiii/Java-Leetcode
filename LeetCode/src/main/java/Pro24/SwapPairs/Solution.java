package Pro24.SwapPairs;

public class Solution
{
    public ListNode swapPairs(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode A = head;
        ListNode B = head.next;

        int t;
        while (A != null && B != null)
        {
            t = B.val;
            B.val = A.val;
            A.val = t;

            if (B.next == null)
            {
                break;
            }
            A = B.next;
            B = B.next.next;
        }

        return head;
    }
}
