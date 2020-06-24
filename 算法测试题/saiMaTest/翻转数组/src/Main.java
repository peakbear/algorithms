import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        // 读取输入
        int N = cin.nextInt();
        List<Integer> nList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nList.add(cin.nextInt());
        }

        boolean result = reverse(nList);
        System.out.println(result ? "yes" : "no");
    }

    private static boolean reverse(List<Integer> nList) {
        Status previousStatus = Status.INCREASE;
        Status currentStatus;
        int decreaseCount = 0;
        int previousNum = nList.get(0);
        int head = Integer.MAX_VALUE, tail = Integer.MIN_VALUE;
        Integer preHead = null, postTail = null;
        for (int i = 1; i < nList.size(); i++) {
            int currentNum = nList.get(i);
            if (currentNum > previousNum) {
                currentStatus = Status.INCREASE;
            } else {
                currentStatus = Status.DECREASE;
                if (previousStatus == Status.INCREASE) {
                    decreaseCount++;
                    if (decreaseCount > 1) {
                        return false;
                    }
                    head = previousNum;
                    preHead = i == 1 ? null : nList.get(i-2);
                }
                tail = currentNum;
                postTail = i == nList.size() - 1 ? null : nList.get(i+1);
            }
            previousNum = currentNum;
            previousStatus = currentStatus;
        }

        if (decreaseCount == 1) {
            return (preHead == null || tail > preHead) && (postTail == null || head < postTail);
        }

        return false;

    }

    private enum Status {
        INCREASE,
        DECREASE;
    }


}