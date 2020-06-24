package helper;

import java.util.ArrayList;
import java.util.List;
import model.Individual;
import model.Point;

public class Helper {

    public static List<Point> generatePoints(int pointsNumber, int range) {
        List<Point> points = new ArrayList<>();
        PointBuilder pointBuilder = new PointBuilder().withRange(range);

        for (int i = 0; i < pointsNumber; ++i) {
            points.add(pointBuilder.buildRandomPoint());
        }

        return points;
    }

    public static List<Individual> createFirstGen(List<Point> points, int population) {
        List <Individual> individuals = new ArrayList<>();
        IndividualBuilder individualBuilder = new IndividualBuilder().withPoints(points);
        for (int i = 0; i < population; i++) {
            individuals.add(individualBuilder.buildRandomIndividual());
        }
        return individuals;
    }
}
