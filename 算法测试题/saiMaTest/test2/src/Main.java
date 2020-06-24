import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        // 读取输入
        int n = cin.nextInt() + 1;
        int p = cin.nextInt();
        int k = cin.nextInt();

        System.out.println(solution(n, p, k));
    }

    private static int solution(int n, int p, int k) {
        // prefixed calculation for powered numbers mods
        HashMap<Integer, Integer> modsMap = new HashMap<>();
        for (int i = 1; i < n; i++) {
            int mod = (i * i) % p;
            if (modsMap.containsKey(mod)) {
                modsMap.put(mod, modsMap.get(mod) + 1);
            } else {
                modsMap.put(mod, 1);
            }
        }

        // double for loop while inner incremental from external loop
        int count = 0;
        for (Map.Entry entry : modsMap.entrySet()) {
            int mod = (int) entry.getKey();
            int target = k >= mod ? k - mod : k + p - mod;
            count += modsMap.get(mod) * modsMap.getOrDefault(target, 0);
        }

        return count;
    }


}