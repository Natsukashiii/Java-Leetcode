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
    {  //这里的m=2*n
        char[] x = {'(', ')'};
        if (t >= m)
        {        //有解，输出结果
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
                //边界条件为（的数目大于等于）数,且left<=n
                if (left >= right && left <= m / 2)
                {   //继续下一维度的计算
                    traceBack(resList, possible, t + 1, m);
                }
            }
        }
        return;
    }
}
