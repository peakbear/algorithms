import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        // 读取输入
        // 第一行是约德尔历史字符串
        String yodelString = cin.nextLine();
        // 第二行是星空观测0101001
        String starsString = cin.nextLine();

        // 获取样本size
        int size = starsString.length();

        int sameDigit = 0;
        // 转换yodelString为数字，1为字母数字，0为其它
        for (int i = 0; i < size; ++i) {
            char c = yodelString.charAt(i);
            if (Character.isDigit(c) || Character.isAlphabetic(c)) {
                if (starsString.charAt(i) == '1') {
                    sameDigit++;
                }
            } else if (starsString.charAt(i) == '0') {
                sameDigit++;
            }
        }

        System.out.printf("%.2f%%", ((float) sameDigit)/size * 100);
    }


}