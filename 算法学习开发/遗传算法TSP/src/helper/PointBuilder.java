package helper;

import java.util.Random;
import model.Point;

public class PointBuilder {

    private static Random random = new Random();

    private int x;
    private int y;
    private int range;

    public PointBuilder withRange(int range) {
        this.range = range;
        return this;
    }
    public Point buildRandomPoint() {
        x = random.nextInt(range);
        y = random.nextInt(range);
        return new Point(x, y);
    }
}
