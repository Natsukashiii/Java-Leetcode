package Pro30.SubstringwithConcatenationofAllWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution
{
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        if(words.length == 0){
            return result;
        }
        int wordLength = words[0].length();
        int allWordsLength = words.length;


        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        for(int j = 0 ; j<words.length ; j++){
            wordMap.put(words[j], wordMap.containsKey(words[j]) ? wordMap.get(words[j])+1 : 1);
        }

        for(int start = 0 ; start<=s.length()-wordLength*allWordsLength ; start++){
            //副本
            Map<String, Integer> copy = new HashMap<String, Integer>(wordMap);
            for(int i=start ; i<start+wordLength*allWordsLength ; i+=wordLength){
                String current = s.substring(i, i+wordLength);
                if(copy.containsKey(current)){
                    int key = copy.get(current);
                    if(key==1){
                        copy.remove(current);
                    }else{
                        copy.put(current, key-1);
                    }
                    if(copy.isEmpty()){
                        result.add(start);
                        //及时跳出循环，否则可能造成超时问题
                        break;
                    }
                }else{
                    break;
                }
            }
        }
        return result;
    }

}
