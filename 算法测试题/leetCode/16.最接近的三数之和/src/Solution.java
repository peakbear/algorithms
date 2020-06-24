import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int closetSum = 0;

        for (int i = 0; i < nums.length; i++) {
            int subTarget = target - nums[i];
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                if (start == i) {
                    start++;
                    continue;
                }
                if (end == i) {
                    end--;
                    continue;
                }
                int sum = nums[start] + nums[end];
                int currentDiff = sum - subTarget;
                if (Math.abs(currentDiff) < minDiff) {
                    minDiff = Math.abs(currentDiff);
                    closetSum = sum + nums[i];
                }
                if (currentDiff < 0) {
                    start++;
                    continue;
                }
                if (currentDiff > 0) {
                    end--;
                    continue;
                }
                if (currentDiff == 0) {
                    return sum + nums[i];
                }
            }
        }
        return closetSum;
    }
}