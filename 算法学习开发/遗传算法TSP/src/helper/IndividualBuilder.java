package helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Individual;
import model.Point;

public class IndividualBuilder {

    private List<Point> points;

    public IndividualBuilder withPoints(List<Point> points) {
        this.points = points;
        return this;
    }

    public Individual buildRandomIndividual() {
        Collections.shuffle(points);
        return new Individual(new ArrayList<>(points));
    }
}
