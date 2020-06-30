import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int run(int[][] rectangles) {

        return points.size() % ((int)Math.pow(10, 9) + 7 );
    }

    private int getSlices(List<List<Integer>> coordinates) {
        List<List<Integer>> buffer = new ArrayList();
        for (int i = 0; i < coordinates.size(); i++) {
            if (buffer.isEmpty()) {
                buffer.add(Arrays.asList(coordinates.get(i).get(0), coordinates.get(i).get(1)));
                continue;
            }
            boolean pushed = false;
            for (int k = 0; k < buffer.size() - 1; k += 2) {
                if (coordinates.get(i).get(1) < buffer.get(k).get(0)
                    || coordinates.get(i).get(0) > buffer.get(k).get(1)) {
                    continue;
                }
                pushed = true;
                if (coordinates.get(i).get(1) >= buffer.get(k).get(0)
                    && coordinates.get(i).get(1) <= buffer.get(k).get(1)) {
                    buffer.get(k).set(0, Math.min(buffer.get(k).get(0), coordinates.get(i).get(0)));
                    break;
                }
                if (coordinates.get(i).get(0) >= buffer.get(k).get(0)
                    && coordinates.get(i).get(0) <= buffer.get(k).get(1)) {
                    buffer.get(k).set(1, Math.max(buffer.get(k).get(1), coordinates.get(i).get(1)));
                    break;
                }
                if (coordinates.get(i).get(0) <= buffer.get(k).get(0)
                    && coordinates.get(i).get(1) >= buffer.get(k).get(1)) {
                    buffer.set(k, Arrays.asList(coordinates.get(i).get(0), coordinates.get(i).get(1)));
                    break;
                }

            }
            if (!pushed) {
                buffer.add(Arrays.asList(coordinates.get(i).get(0), coordinates.get(i).get(1)));
            }
        }
        buffer.sort((o1, o2) -> {
            if (o1.get(0).equals(o2.get(0))) {
                return o1.get(1).compareTo(o2.get(1));
            }
            return o1.get(0).compareTo(o2.get(0));
        });

    }


}