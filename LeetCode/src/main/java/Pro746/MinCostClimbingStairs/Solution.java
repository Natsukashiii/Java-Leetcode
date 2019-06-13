package Pro746.MinCostClimbingStairs;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * <p>
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
 * <p>
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 */
public class Solution
{
    public int minCostClimbingStairs(int[] cost)
    {
        int length = cost.length + 1;
        int[] min = new int[length];
        min[0] = 0;
        min[1] = 0;
        for (int i = 2; i < length; i++) {
            min[i] = Math.min(min[i - 2] + cost[i - 2], min[i - 1] + cost[i - 1]);
        }
        return min[length - 1];
    }
}
