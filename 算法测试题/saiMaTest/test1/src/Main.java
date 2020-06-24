import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Integer> numbers = new ArrayList<>();

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        // 读取输入
        int n = cin.nextInt();
        int m = cin.nextInt();

        for (int i = 0; i < n; i++) {
            numbers.add(cin.nextInt());
        }

        System.out.println(solution(m));
    }

    private static int solution(int m) {
        int longestLength = 1;
        for (int head = 0; head < numbers.size(); head++) {
            for (int tail = head + 1; tail < numbers.size() + 1; tail++) {
                List<Integer> candidates = cut(head, tail);
                int diff = getMaxMinDif(findMax(candidates), findMin(candidates));
                if (diff <= m) {
                    if (candidates.size() > longestLength) {
                        longestLength = candidates.size();
                    }
                } else {
                    continue;
                }
            }
        }
        return longestLength;
    }

    private static int findMax(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    private static int findMin(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).min().getAsInt();
    }

    private static List<Integer> cut(int head, int tail) {
        return numbers.subList(head, tail);
    }

    private static int getMaxMinDif(int max, int min) {
        return max - min;
    }



}