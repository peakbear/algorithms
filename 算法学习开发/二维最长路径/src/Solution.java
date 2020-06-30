import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Integer, Integer> distanceMap = new HashMap<>();

    public int run(int[][] map) {
        int distance = 0;
        for (int loc = 0; loc < map.length * map[0].length; loc++) {
            int dis = getLongestDistnace(map, loc);
            if (dis > distance) {
                distance = dis;
            }
        }
        return distance;
    }

    public int getLongestDistnace(int[][] map, int loc) {
        if (distanceMap.containsKey(loc)) {
            return distanceMap.get(loc);
        }

        int x = loc / map.length;
        int y = loc % map.length;
        int[] delta = {-1, 0, 1};

        int distance = 0;
        for (int dx : delta) {
            for (int dy : delta) {
                if ((dx==0 || dy==0) && (dx!=dy) && x + dx >= 0 && x + dx < map.length && y + dy >= 0 && y + dy < map[0].length) {
                    if (map[x+dx][y+dy] < map[x][y]) {
                        int newloc = (x + dx)*map.length + y + dy;
                        int dis = getLongestDistnace(map, newloc) + 1;
                        if (distance < dis) {
                            distance = dis;
                        }
                    }
                }
            }
        }

        distanceMap.put(loc, distance);
        return distance;

    }
}
