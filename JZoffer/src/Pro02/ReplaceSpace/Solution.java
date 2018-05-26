package Pro02.ReplaceSpace;

//用原数组扩大长度后存储新的数组

public class Solution
{
    public String replaceSpace(StringBuffer str)
    {
        int spacenum = 0;
        for (int i = 0; i < str.length(); i++) 
        
            if (str.charAt(i) == ' ')
            {
                spacenum++;
            }
        }
        int indexold = str.length() - 1;
        int newlength = str.length() + spacenum * 2; //原来的空格变成'%''2''0' 多了两倍的空格数
        int indexnew = newlength - 1;
        str.setLength(newlength);

        for (; indexold >= 0 && indexold < newlength; --indexold)
        {
            if (str.charAt(indexold) == ' ')
            {
                str.setCharAt(indexnew--, '0');
                str.setCharAt(indexnew--, '2');
                str.setCharAt(indexnew--, '%');
            }
            else
            {
                str.setCharAt(indexnew--, str.charAt(indexold));
            }
        }
        return str.toString();
//        return str.toString().replaceAll("\\s","%20");
//        正则
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        StringBuffer stringBuffer = new StringBuffer(" hello");
        System.out.println(solution.replaceSpace(stringBuffer));
    }
}
