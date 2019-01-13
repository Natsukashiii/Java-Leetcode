package Pro961.NRepeatedElementinSize2NArray;

import java.util.HashMap;

public class Solution
{
    public int repeatedNTimes(int[] A) {
        int length = A.length;
        double i = (double) length;
        double j = 2;
        int n = (int)(Math.log(i)/Math.log(j));
        int res = 0;
        HashMap<Integer,Integer> resMap = new HashMap<Integer,Integer>();
        for( int a = 0; a < A.length; a++){
            if(!resMap.containsKey(A[a])){
                resMap.put(A[a],1);
            }else{
                int value = resMap.get(A[a]);
                resMap.put(A[a],value+1);
                if (n == value+1){
                    res = A[a];
                }
            }
        }
        return res;
    }
}
