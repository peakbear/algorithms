package helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Individual;
import model.Point;

public class IndividualBuilder {

    private List<Point> points;
    private int capacity;

    public IndividualBuilder withPoints(List<Point> points) {
        this.points = points;
        return this;
    }

    public IndividualBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public void initialise() {
        int maxK = 0;
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(points);
            int k = segmentRoutes(points);
            if (maxK < k) {
                maxK = k;
            }
        }
        for (int i = 0; i < maxK-1; i++) {
            points.add(new Point(0,0,0));
        }
    }

    private int segmentRoutes(List<Point> points) {
        int k = 1;
        int currentVolume = points.get(0).getQty();
        for (int i = 1; i < points.size(); i++) {
            if (currentVolume + points.get(i).getQty() <= capacity) {
                currentVolume += points.get(i).getQty();
            } else {
                k++;
                currentVolume = points.get(i).getQty();
            }
        }

        return k;
    }

    public Individual buildRandomIndividual() {
        Collections.shuffle(points);
        return new Individual(new ArrayList<>(points), capacity);
    }
}
