package Pro27.RemoveElement;

public class Solution
{
    public int removeElement(int[] nums, int val)
    {
        int left = 0;
        int right = nums.length - 1;
        int length = nums.length;
        while (true)
        {
            while (left <= nums.length - 1 && nums[left] != val)
            {
                left++;
            }
            while (right >= 0 && nums[right] == val)
            {
                right--;
                length--;
            }
            if (left < right)
            {
                nums[left++] = nums[right--];
                length--;
            }
            else
            {
                break;
            }
        }
        return length;
    }
}
