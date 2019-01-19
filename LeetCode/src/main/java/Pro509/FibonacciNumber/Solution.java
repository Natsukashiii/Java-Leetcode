package Pro509.FibonacciNumber;

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
