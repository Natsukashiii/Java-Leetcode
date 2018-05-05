package Pro42.LeftRotateString;


/*输入一个递增排序的数组和一个数字S，
        在数组中查找两个数，是的他们的和正好是S，
        如果有多对数字的和等于S，输出两个数的乘积最小的。*/


public class Solution
{
    public String LeftRotateString(String str, int n)
    {
        char[] chars = str.toCharArray();
        if (chars.length < n)
        {
            return "";
        }
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        StringBuilder stringBuilder = new StringBuilder(chars.length);
        for (char c : chars)
        {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();


    }

    public void reverse(char[] chars, int low, int high)
    {
        char temp;
        while (low < high)
        {
            temp = chars[low];
            chars[low] = chars[high];
            chars[high] = temp;
            low++;
            high++;
        }
    }
}
