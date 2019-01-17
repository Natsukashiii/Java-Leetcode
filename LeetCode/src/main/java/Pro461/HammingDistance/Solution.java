package Pro461.HammingDistance;

public class Solution {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }

    /**
     * 计算x和y的二进制不同内容的位数, 实际将x和y取异或, 求出异或后的数有多少个1
     *
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistance(int x, int y) {
        int i = x ^ y;
        int count = 0;
        while (i != 0) {
            ++count;
            i = (i - 1) & i;
        }
        return count;
    }
}
