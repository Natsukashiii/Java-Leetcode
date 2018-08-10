package Pro20.ValidParentheses;

import java.util.Stack;

public class Solution
{
    public boolean isValid(String s)
    {
        char[] list = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for (char temp : list)
        {
            if (temp == '(' || temp == '[' || temp == '{')
            {
                stack.push(temp);
            }
            if (temp == '}' && (stack.isEmpty() || stack.pop() != '{'))
            {
                return false;
            }
            if (temp == ')' && (stack.isEmpty() || stack.pop() != '('))
            {
                return false;
            }
            if (temp == ']' && (stack.isEmpty() || stack.pop() != '['))
            {
                return false;
            }
        }
        if (!stack.isEmpty())
        {
            return false;
        }
        return true;
    }
}