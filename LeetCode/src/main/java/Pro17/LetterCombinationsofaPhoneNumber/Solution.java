package Pro17.LetterCombinationsofaPhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class Solution
{
    public static void main(String[] args)
    {
        String one = "abc";

    }

    public List<String> letterCombinations(String digits)
    {
        //��table�ϵ����ֶ�Ӧ����ĸ�г�����������Ϊ2�ǣ�digits[2]����2����Ӧ��"abc"
        String[] table = new String[]
                {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> list = new ArrayList<String>();
        //index��0��ʼ����digits�ĵ�һ������
        letterCombinations(list, digits, "", 0, table);
        return list;
    }

    private void letterCombinations(List<String> list, String digits,
                                    String curr, int index, String[] table)
    {
        //���һ���˳�����
        if (index == digits.length())
        {
            if (curr.length() != 0)
            {
                list.add(curr);
            }

            return;

        }

        //�ҵ����ֶ�Ӧ���ַ���
        String temp = table[digits.charAt(index) - '0'];
        for (int i = 0; i < temp.length(); i++)
        {
            //ÿ��ѭ���Ѳ�ͬ�ַ����ӵ���ǰcurr֮��
            String next = curr + temp.charAt(i);
            //������һ��
            letterCombinations(list, digits, next, index + 1, table);
        }
    }
}
