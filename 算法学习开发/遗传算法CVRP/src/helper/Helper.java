package helper;

import java.util.ArrayList;
import java.util.List;
import model.Individual;
import model.Point;

public class Helper {

    public static List<Point> generatePoints(int pointsNumber, int mapRange, int qtyRange) {
        List<Point> points = new ArrayList<>();
        PointBuilder pointBuilder = new PointBuilder().withMapRange(mapRange).withQtyRange(qtyRange);

        for (int i = 0; i < pointsNumber; ++i) {
            points.add(pointBuilder.buildRandomPoint());
        }

        return points;
    }

    public static List<Individual> createFirstGen(List<Point> points, int population, int capacity) {
        List <Individual> individuals = new ArrayList<>();
        IndividualBuilder individualBuilder = new IndividualBuilder().withPoints(points).withCapacity(capacity);
        individualBuilder.initialise();
        for (int i = 0; i < population; i++) {
            individuals.add(individualBuilder.buildRandomIndividual());
        }
        return individuals;
    }
}
