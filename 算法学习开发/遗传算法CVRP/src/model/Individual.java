package model;

import static helper.IndividualHelper.generateRandomIdxPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Individual {

    private List<Point> points;
    private int capacity;
    private double p;
    private double pCum;

    public Individual(List<Point> points, int capacity) {
        this.points = points;
        this.capacity = capacity;
    }

    public List<Point> getPoints() {
        return points;
    }

    public double getpCum() {
        return pCum;
    }

    public int getCapacity() {
        return capacity;
    }

    public double calculateDistance() {
        List<Point> pointsCopy = new ArrayList<>();
        pointsCopy.add(new Point(0, 0, 0));
        pointsCopy.addAll(points);

        double distance = 0;
        for (int i = 0; i < pointsCopy.size(); i++) {
            Point p1 = pointsCopy.get(i);
            Point p2 = pointsCopy.get((i+1)%pointsCopy.size());
            distance += p1.distanceTo(p2);
        }
        return distance;
    }

    public double fitness() {
        double distance = this.calculateDistance();
        double overFilledScore = getTotalOverfilledScore(points) * 100;

        return 1 / (distance * (1 + overFilledScore));
    }

    private double getTotalOverfilledScore(List<Point> points) {
        double overFilledScore = 0;
        int startidx = 0;
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getQty() == 0) {
                overFilledScore += overFilledScore(points.subList(startidx, i));
                startidx = i;
            }
        }
        overFilledScore += overFilledScore(points.subList(startidx, points.size()));
        return overFilledScore;
    }

    private double overFilledScore(List<Point> points) {
        double qty = points.stream().mapToDouble(Point::getQty).sum();
        return qty > capacity ? (qty - capacity) / capacity : 0;
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
