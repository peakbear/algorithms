// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int N) {
        // write your code in Java SE 8
        boolean startCount = false;
        int zeroCount = 0;
        int maxCount = 0;

        while (N > 0) {
            if ((N & 1) == 1) {
                startCount = true;
            }
            if (startCount) {
                if ((N & 1) == 0) {
                    zeroCount++;
                } else {
                    maxCount = Math.max(zeroCount, maxCount);
                    zeroCount = 0;
                }
            }
            N >>= 1;
        }
        return maxCount;
    }
    
}
