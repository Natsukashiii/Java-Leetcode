package Pro657.RobotReturntoOrigin;

public class Solution {
    public boolean judgeCircle(String moves) {
        boolean res = false;
        int high = 0;
        int weight = 0;
        for (int i = 0; i < moves.length(); i++) {
            switch (moves.charAt(i)) {
                case 'U':
                    high++;
                    break;
                case 'D':
                    high--;
                    break;
                case 'L':
                    weight--;
                    break;
                case 'R':
                    weight++;
                    break;
            }
        }
        if (high == 0 && weight == 0) {
            res = true;
        }
        return res;
    }
}
