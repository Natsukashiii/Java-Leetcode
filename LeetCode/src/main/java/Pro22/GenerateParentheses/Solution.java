package Pro22.GenerateParentheses;

import java.util.ArrayList;
import java.util.List;

public class Solution
{
    public List<String> generateParenthesis(int n)
    {

        List<String> resList = new ArrayList<String>();
        char[] possible = new char[2 * n];
        traceBack(resList, possible, 0, 2 * n);
        return resList;
    }

    public void traceBack(List<String> resList, char[] possible, int t, int m)
    {  //�����m=2*n
        char[] x = {'(', ')'};
        if (t >= m)
        {        //�н⣬������
            String string = String.valueOf(possible);
            resList.add(string);
        }
        else
        {
            for (int i = 0; i <= 1; i++)
            {
                int left = 0, right = 0;
                possible[t] = x[i];
                for (int j = 0; j <= t; j++)
                {
                    if (possible[j] == ')')
                    {
                        right++;
                    }
                    else
                    {
                        left++;
                    }
                }
                //�߽�����Ϊ������Ŀ���ڵ��ڣ���,��left<=n
                if (left >= right && left <= m / 2)
                {   //������һά�ȵļ���
                    traceBack(resList, possible, t + 1, m);
                }
            }
        }
        return;
    }
}
