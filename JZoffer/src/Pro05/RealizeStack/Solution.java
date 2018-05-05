package Pro05.RealizeStack;



import java.util.Arrays;
import java.util.Stack;
//定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数
public class Solution
{
    private int size;
    private int min = Integer.MAX_VALUE;
    private Stack<Integer> stack = new Stack<Integer>();
    private Integer[] elements = new Integer[10];

    public void push(int node)
    {
        enlargeSize(size + 1);
        elements[size++] = node;
        if (node <= min)
        {
            stack.push(node);
            min = stack.peek();
        }
        else
        {
            stack.push(min);
        }
    }

    public void enlargeSize(int size)
    {
        int len = elements.length;
        if (size > len)
        {
            int newLen = 2 * len;
            elements = Arrays.copyOf(elements, newLen);
        }
    }

    public void pop()
    {
        Integer top = top();
        if (top != null)
        {
            elements[size - 1] = (Integer) null;
        }
        size--;
        stack.pop();
        min = stack.peek();
    }

    public int top()
    {
        if (!empty())
        {
            if (size - 1 > 0)
            {
                return elements[size - 1];
            }
        }
        return (Integer) null;
    }

    public boolean empty()
    {
        return size == 0;
    }

    public int min()
    {
        return min;
    }
}
