package Pro509.FibonacciNumber;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), for N > 1.
 * Given N, calculate F(N).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 */
public class Solution {
    public int fib(int N) {
        if (N < 2) {
            return N;
        }
        int first = 0;
        int second = 1;
        int sum = 0;
        while (--N > 0) {
            sum = second + first;
            first = second;
            second = sum;
        }

        return sum;
    }
}
