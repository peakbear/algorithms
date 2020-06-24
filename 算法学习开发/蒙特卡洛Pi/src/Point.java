import static java.lang.StrictMath.sqrt;

import java.util.Random;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Boolean isInCircle() {
        return sqrt(x * x + y * y) <= 1;
    }

    public static Point generatePoint() {
        Random rand = new Random();
        return new Point(rand.nextDouble(), rand.nextDouble());
    }
}
