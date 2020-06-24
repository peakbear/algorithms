import java.util.Scanner;

public class Main {

    private static int[][] map;

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        // 读取输入
        int n = cin.nextInt();
        map = new int[n+1][n+1];
        for (int i = 0; i < n - 1; i++) {
            int p = cin.nextInt();
            int q = cin.nextInt();
            int d = cin.nextInt();
            map[p][q] = d;
            map[q][p] = d;
        }

        int max = 0;
        for (int nextPos = 1; nextPos < map[0].length; nextPos++) {
            int maxDis = maxDistance(nextPos, nextPos);
            if (maxDis > max) {
                max = maxDis;
            }
        }

        int fee = calculateFee(max);

        System.out.println(fee);
    }

    private static int maxDistance(int prePos, int curPos) {
        int maxDistance = 0;
        for (int nextPos = 1; nextPos < map[0].length; nextPos++) {
            if (nextPos != prePos && map[curPos][nextPos] != 0) {
                int nextDis = maxDistance(curPos, nextPos);
                if (nextDis > maxDistance){
                    maxDistance = nextDis;
                }
            }
        }
        return map[prePos][curPos] + maxDistance;

    }

    private static int calculateFee(int d) {
        return (1 + d) * d / 2 + d * 10;
    }

}