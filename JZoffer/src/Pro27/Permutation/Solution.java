package Pro27.Permutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/*输入一个字符串,按字典序打印出该字符串中字符的所有排列。
        例如输入字符串abc,则打印出由
        字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。*/


public class Solution
{
    public ArrayList<String> Permutation(String str)
    {
        ArrayList res = new ArrayList();
        if (str == null && str.length() == 0)
        {
            return res;
        }
        HashSet<String> set = new HashSet<String>();
        fun(set, str.toCharArray(), 0);
        res.addAll(set);
        Collections.sort(res);
        return res;

    }

    void fun(HashSet<String> re, char[] str, int k)
    {
        if (k == str.length)
        {
            re.add(new String(str));
            return;
        }
        for (int i = k; i < str.length; i++)
        {
            swap(str, i, k);
            fun(re, str, k + 1);
            swap(str, i, k);
        }
    }

    void swap(char[] str, int i, int j)
    {
        if (i != j)
        {
            char t = str[i];
            str[i] = str[j];
            str[j] = t;
        }
    }


    //DFS
    private char[] seqs;
    private Integer[] book;
    private HashSet<String> result = new HashSet<String>();

    private ArrayList<String> Permutation2(String str)
    {
        ArrayList<String> arrange = new ArrayList<String>();
        if (str == null || str.isEmpty())
        {
            return arrange;
        }
        char[] strs = str.toCharArray();
        seqs = new char[str.length()];
        book = new Integer[str.length()];
        for (int i = 0; i < book.length; i++)
        {
            book[i] = 0;
        }
        dfs(strs, 0);
        arrange.addAll(result);
        Collections.sort(arrange);
        return arrange;
    }

//    记录所有可能排列
    private void dfs(char[] arrs, int step)
    {
        if (arrs.length == step)
        {
            String str = "";
            for (int i = 0; i < seqs.length; i++)
            {
                str += seqs[i];
            }
            result.add(str);
            return;
        }

//        遍历整个序列，尝试每一种可能
        for (int i = 0; i < arrs.length; i++)
        {
            if (book[i] == 0)
            {
                seqs[step] = arrs[i];
                book[i] = 1;
                dfs(arrs, step + 1);
                book[i] = 0;
            }
        }
    }

}
