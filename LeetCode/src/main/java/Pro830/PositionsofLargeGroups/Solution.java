package Pro830.PositionsofLargeGroups;

import java.util.ArrayList;
import java.util.List;

/**
 * In a string S of lowercase letters, these letters form consecutive groups of the same character.
 * <p>
 * For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
 * <p>
 * Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.
 * <p>
 * The final answer should be in lexicographic order.
 * <p>
 * Example 1:
 * <p>
 * Input: "abbxxxxzzy"
 * Output: [[3,6]]
 * Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
 * Example 2:
 * <p>
 * Input: "abc"
 * Output: []
 * Explanation: We have "a","b" and "c" but no large group.
 * Example 3:
 * <p>
 * Input: "abcdddeeeeaabbbcd"
 * Output: [[3,5],[6,9],[12,14]]
 * <p>
 * <p>
 * Note:  1 <= S.length <= 1000
 */
public class Solution
{
    public List<List<Integer>> largeGroupPositions(String S)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        char[] s = S.toCharArray();
        int len = s.length;
        for (int i = 0; i < len; i++)
        {
            int count = 1;
            int index = i;
            while (i + 1 < len && s[i] == s[i + 1])
            {
                count++;
                i++;
            }
            if (count >= 3)
            {
                List<Integer> list = new ArrayList<>();
                list.add(index);
                list.add(i);
                result.add(list);
            }
        }
        return result;
    }
}
