package Pro03.PrintArrayList;

//构造一个ListNode类型的链表
public class AddList
{
    int length;
    ListNode head;
    ListNode tail;

    public void add(ListNode listNode)
    {
        length++;
        if (head == null)
        {
            this.head = listNode;
            this.tail = listNode;
        }
        else
        {
            tail.next = listNode;
            tail = listNode;
        }
    }
}
