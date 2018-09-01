package Pro26.RemoveDuplicates;
public class Solution
{
    public int removeDuplicates(int[] nums)
    {
        if (nums == null) return 0;
        int res = 0;
        int[] p = nums;
        for (int i = 1; i < nums.length; i++)
        {
            if (p[i] == nums[res])
            {

            }
            else
            {
                nums[++res] = p[i];
            }
        }
        return res + 1;
    }
}
