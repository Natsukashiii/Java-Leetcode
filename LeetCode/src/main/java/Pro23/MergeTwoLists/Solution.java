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

        //���ڡ����֡�˼�����������������
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

    //�����������ϣ�L1��L2Ϊͷ��㣬�鲢����ĺ���˼��
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

        //������������ͷ����ʼ��
        ListNode head = new ListNode(0);
        ListNode phead = head;

        //��������鲢����
        while (L1 != null && L2 != null)
        {
            if (L1.val <= L2.val)
            {
                //����������
                phead.next = L1;
                //�ƶ�ָ��
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

        //��ʼ���ĵ�һ���ڵ㲻���ڽ��
        return head.next;
    }
}
