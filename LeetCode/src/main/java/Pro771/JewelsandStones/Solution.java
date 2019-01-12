package Pro771.JewelsandStones;

import java.util.HashMap;

public class Solution
{
    public int numJewelsInStones(String J, String S) {
        int res = 0;
        HashMap<Character,Character> JMap = new HashMap<Character,Character>();
        for (int i = 0; i < J.length(); i++){
            char ch = J.charAt(i);
            JMap.put(ch,ch);
        }
        for (int j = 0; j < S.length(); j++){
            char ch = S.charAt(j);
            if (JMap.containsKey(ch)){
                res++;
            }
        }

        return res;
    }
}
