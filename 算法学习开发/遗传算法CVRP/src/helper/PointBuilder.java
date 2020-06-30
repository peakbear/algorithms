package helper;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import model.Point;

public class PointBuilder {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();
    private int mapRange;
    private int qtyRange;

    public PointBuilder withMapRange(int mapRange) {
        this.mapRange = mapRange;
        return this;
    }

    public PointBuilder withQtyRange(int qtyRange) {
        this.qtyRange = qtyRange;
        return this;
    }

    public Point buildRandomPoint() {
        int x = random.nextInt(mapRange);
        int y = random.nextInt(mapRange);
        int qty = random.nextInt(1, qtyRange);
        return new Point(x, y, qty);
    }
}
