import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int N;
        // 读取输入，直到没有整型数据可读
        while (cin.hasNextInt()) {
            // 读取N
            N = cin.nextInt();
            System.out.println(solution(N));
        }
    }

    private static int solution(int N) {
        int group = findGroup(N);
        int negtive = group-1;
        int positive = N - negtive;
        return positive - negtive;
    }

    private static int findGroup(int N) {
        int group = 1;
        while (((1 + group) * group / 2) <= N) {
            group++;
        }
        group--;
        return group;
    }


}