package model;

import static helper.IndividualHelper.generateRandomIdxPair;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Individual {

    private List<Point> points;
    private double p;
    private double pCum;

    public Individual(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public double getpCum() {
        return pCum;
    }

    private double calculateDistance() {
        double distance = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i+1)%points.size());
            distance += p1.distanceTo(p2);
        }
        return distance;
    }

    public double fitness() {
        double distance = this.calculateDistance();
        return 1 / distance;
    }

    public void updateP(double fitnessSum) {
        this.p = fitness() / fitnessSum;
    }

    public void updatePCum(double lastPCum) {
        this.pCum = lastPCum + p;
    }

    public void mutate() {
        final List<Integer> idxPair = generateRandomIdxPair(this.getPoints().size());
        Collections.reverse(this.getPoints().subList(idxPair.get(0), idxPair.get(1)));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Individual that = (Individual) o;

        return points != null ? points.equals(that.points) : that.points == null;
    }

    @Override
    public int hashCode() {
        return points != null ? points.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{ " +
            "\"points\":" + (points == null ? "null" : Arrays.toString(points.toArray())) +
            "}";
    }
}
