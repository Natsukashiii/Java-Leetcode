package Pro01.TwoSum;

import java.util.HashMap;
import java.util.Scanner;

public class Solution
{
    public  int[] twoSum(int[] nums,int target){
        int[] result=new int[2];
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int i = 0; i < nums.length; i++)
        {
            //由于输出的是下标，get取到的值是value，所以key的值是数组的数值，value的值是数组下标
            if (hashMap.containsKey(target-nums[i])){
                result[0]=hashMap.get(target-nums[i]);
                result[1]=i;
            }else {
                hashMap.put(nums[i],i);
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        int[] arrays=new int[]{2,7,11,15};
        int target=27;
        int[] result=new int[2];
        Solution solution=new Solution();
        result=solution.twoSum(arrays,target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
