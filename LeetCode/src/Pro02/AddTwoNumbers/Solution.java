package Pro02.AddTwoNumbers;


import java.util.Scanner;

public class Solution
{

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2)
    {
        int sum = 0;
        ListNode listNode = new ListNode(0);
        ListNode p1 = l1, p2 = l2, p3 = listNode;
        while (p1 != null || p2 != null)
        {
            if (p1 != null)
            {
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null)
            {
                sum += p2.val;
                p2 = p2.next;
            }
            p3.next = new ListNode(sum % 10);
            p3 = p3.next;
//            sum /= 10;
        }
        if (sum == 1)
        {
            p3.next = new ListNode(1);
        }
        return listNode.next;
    }

    //直接返回最终的ListNode
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode newList = new ListNode(0);
        ListNode nextNode = new ListNode(0);

        if (l1 == null)
        {
            return l2;
        }
        if (l2 == null)
        {
            return l1;
        }

        int sum = 0;
        int a = 0;

        while (l1 != null && l2 != null)
        {
            if (l1 != null)
            {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null)
            {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += a;
            if (sum > 9)
            {
                sum -= 10;
                a = 1;
            }
            if (newList == null)
            {
                newList = new ListNode(sum);
                nextNode = newList;
            }
            else
            {
                nextNode.next = new ListNode(sum);
                nextNode = nextNode.next;
            }
            sum = 0;
        }
        if (a == 1)
        {
            nextNode.next = new ListNode(a);
        }
        return newList;
    }

    public static ListNode addNode(int a, ListNode head, ListNode last)
    {
        ListNode node = new ListNode(0);
        if (head == null)
        {
            head = node;
        }
        else
        {
            last.next = node;
        }
        last = node;
        return head;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] split = s.split("\\s+");
        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++)
        {
            arr[i] = Integer.parseInt(split[i]);
        }

        ListNode head1 = null;
        ListNode last1 = null;
        int modCount1 = 0;
        for (int i = 0; i < arr.length / 2; i++)
        {
            addNode(arr[i], head1, last1);
        }

        ListNode head2 = null;
        ListNode last2 = null;
        int modCount2 = 0;
        for (int i = arr.length / 2; i < arr.length; i++)
        {
            addNode(arr[i], head2, last2);
        }
//        int[] arr1 = new int[3];
//        int[] arr2 = new int[3];
//        for (int i = 0; i <3 ; i++)
//        {
//            arr1[i] = scanner.nextInt();
//        }
//        for (int i = 0; i < 3; i++)
//        {
//            arr2[i] = scanner.nextInt();
//        }
//        scanner.close();
//
//        ListNode l1 = new ListNode(0);
//        for (int i = 0; i < 3; i++)
//        {
//            l1.next = new ListNode(arr1[i]);
//            l1 = l1.next;
//        }
//
//        ListNode l2 = new ListNode(0);
//        for (int i = 3; i < arr1.length; i++)
//        {
//            l2.next = new ListNode(arr1[i]);
//            l2 = l2.next;
//        }

//        System.out.println(arr1[0]);
//        System.out.println(arr1.length);
//        while (l1!=null){
//            System.out.println(l1.val);
//            l1=l1.next;
//        }
//        System.out.println(l2.val);
//        System.out.println(l1.val);

//        Solution solution = new Solution();
//        ListNode newList  = solution.addTwoNumbers2(l1, l2);
//        while (newList!= null)
//        {
//            System.out.println(newList.val);
//            newList=newList.next;
//        }
    }
}
