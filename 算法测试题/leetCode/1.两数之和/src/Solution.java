import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] raw = nums.clone();
        Arrays.sort(nums);

        Set<Integer> results = new HashSet<>();
        int i = 0;
        int k = nums.length - 1;
        while (i < k) {
            if (nums[i] + nums[k] < target) {
                i++;
                continue;
            }
            if (nums[i] + nums[k] > target) {
                k--;
                continue;
            }
            // nums[i] + nums[k] == target
            break;
        }
        for (int a = 0; a < raw.length; a++) {
            if (raw[a] == nums[i] || raw[a] == nums[k]) {
                results.add(a);
            }
        }
        int[] r = new int[2];
        r[0] = (int)results.toArray()[0];
        r[1] = (int)results.toArray()[1];
        return r;
    }
}