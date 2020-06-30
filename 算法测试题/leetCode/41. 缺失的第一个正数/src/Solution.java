public class Solution {
    public int run(int[] nums) {
        int[] counter = new int[nums.length + 1];
        for (int i : nums) {
            if (i > 0 & i < counter.length) {
                counter[i]++;
            }
        }
        for (int i = 1; i < counter.length; i++) {
            if (counter[i] == 0) {
                return i;
            }
        }
        return counter.length;

    }
}