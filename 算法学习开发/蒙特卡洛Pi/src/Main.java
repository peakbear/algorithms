import java.math.BigDecimal;

public class Main {

    private static BigDecimal TRAILS = BigDecimal.valueOf(100000);

    public static void main(String[] args) {
        int count = 0;

        for (int i = 0; i < TRAILS.intValue(); ++i) {
            if (Point.generatePoint().isInCircle()) {
                count++;
            }
        }

        System.out.println("pi = " + count / TRAILS.doubleValue() * 4);
    }


}


