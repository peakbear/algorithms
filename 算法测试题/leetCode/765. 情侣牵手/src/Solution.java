import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int run(int[] row) {
        Map<Integer, Integer> numberPos = new HashMap();

        for (int i = 0; i < row.length; i++) {
            numberPos.put(row[i], i);
        }

        int move = 0;

        for (int i = 0; i < row.length; i = i + 2) {
            if (!isPairedAt(i, row)) {
                int pairNum = getPairNumber(row[i]);
                swap(i+1, numberPos.get(pairNum), row, numberPos);
                move++;
            }
        }

        return move;
    }

    private int getPairNumber(int number) {
        return number % 2 == 0 ? number + 1 : number - 1;
    }

    private boolean isPairedAt(int i, int[] nums) {
        return getPairNumber(nums[i]) == nums[i+1];
    }

    private void swap(int i, int k, int[] nums, Map numberPos) {
        int temp = nums[i];
        nums[i] = nums[k];
        nums[k] = temp;
        numberPos.put(nums[i], i);
        numberPos.put(nums[k], k);
    }


}
