import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int N, M;
        // 读取输入，直到没有整型数据可读
        while (cin.hasNextInt()) {
            // 读取N 和 M
            N = cin.nextInt();
            M = cin.nextInt();
            List<Integer> relationsList = new ArrayList(Collections.nCopies(N+1, 0));
            // 读取接下来M行
            for (int i = 0; i < M; i++) {
                // 读取每行的a b c
                int a = cin.nextInt();
                int b = cin.nextInt();
                int c = cin.nextInt();
                relationsList = calRelations(relationsList, a, b, c);
            }
            int sai = relationsList.get(1);
            if (sai == 0) {
                System.out.println(0);
                continue;
            }
            List<Integer> hometownMates = relationsList.stream().filter(r -> r == sai).collect(Collectors.toList());
            System.out.println(hometownMates.size() - 1);
        }
    }

    private static List calRelations(List<Integer> relationsList, int a, int b, int c) {
        if (c == 1) {
            if (relationsList.get(a) != 0) {
                relationsList.set(b, relationsList.get(a));
            } else if (relationsList.get(b) != 0) {
                relationsList.set(a, relationsList.get(b));
            } else {
                relationsList.set(a, a);
                relationsList.set(b, a);
            }
        }
        return relationsList;
    }
}