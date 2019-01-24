package Pro283.MoveZeroes;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 * <p>
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }

    }


    public static void moveZeroes(int[] nums) {
        int index = 0;
        int tmp = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[index++] = nums[j];
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
