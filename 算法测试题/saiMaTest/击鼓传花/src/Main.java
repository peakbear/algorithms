import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        // 读取输入
        int n = cin.nextInt();
        int m = cin.nextInt();

        int result = calculate(n , m);
        System.out.println(result);
    }

    private static int calculate(int n, int m) {
        List<Integer> circle = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            circle.add(i);
        }
        return transport(0, n, m, 0);

    }

    private static int toLeft(int i, int n) {
        return (n + i - 1) % n;
    }

    private static int toRight(int i, int n) {
        return (i + 1) % n;
    }

    private static int transport(int i, int n, int m, int times) {
        if (times == m) {
            if (i == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int distanceToOrigin = i < n - i ? i : n - i;
        if (m - times < distanceToOrigin) {
            return 0;
        }
        return transport(toLeft(i, n), n, m, times+1) + transport(toRight(i, n), n, m, times+1);

    }

}