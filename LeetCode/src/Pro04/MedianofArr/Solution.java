package Pro04.MedianofArr;

public class Solution
{
//    直接把两个数组合并，数字大的数组开始从末尾添加，用下标index，存储位置
//    但是时间复杂度是O(m+n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] arr = new int[len1 + len2];
        int end1 = len1 - 1;
        int end2 = len2 - 1;
        int index = arr.length - 1;
        while (end1 >= 0 & end2 >= 0)
        {
            if (nums1[end1] > nums2[end2])
            {
                arr[index] = nums1[end1];
                end1--;
                index--;
            }
            else
            {
                arr[index] = nums2[end2];
                end2--;
                index--;
            }
        }

        while (end1 >= 0)
        {
            arr[index] = nums1[end1];
            end1--;
            index--;
        } while (end2 >= 0)
    {
        arr[index] = nums2[end2];
        end2--;
        index--;
    }
        if (arr.length % 2 == 0)
        {
            return (double) (arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2;
        }
        else
        {
            return arr[arr.length / 2];
        }
    }

//    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
//
//    }
}
