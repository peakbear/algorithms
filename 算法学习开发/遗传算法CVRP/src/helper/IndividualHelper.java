package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import model.Individual;
import model.Point;

public class IndividualHelper {

    public static Individual OXCrossForBestChild(Individual father, Individual mother) {
        List<Point> fatherCopy = new LinkedList<>(father.getPoints());
        List<Point> motherCopy = new LinkedList<>(mother.getPoints());

        final List<Integer> idxPair = generateRandomIdxPair(fatherCopy.size());

        for (int i = idxPair.get(0); i <= idxPair.get(1); i++) {
            fatherCopy.add(i, mother.getPoints().get(i));
            motherCopy.add(i, father.getPoints().get(i));
        }
        fatherCopy = solveConflict(fatherCopy, father.getPoints());
        motherCopy = solveConflict(motherCopy, mother.getPoints());

        Individual child1 = new Individual(fatherCopy, father.getCapacity());
        Individual child2 = new Individual(motherCopy, mother.getCapacity());

        return child1.fitness() >= child2.fitness() ? child1 : child2;
    }

    private static List<Point> solveConflict(List<Point> targetPoints, List<Point> refPoints) {
        Set<Point> dupPoints = findDuplicatePoints(targetPoints);
        for (Point point : dupPoints) {
            if (point.getQty() != 0) {
                targetPoints.remove(findLastPointIndex(targetPoints, point));
            }
        }

        while (individualHasConflict(targetPoints, refPoints)) {
            targetPoints.remove(findLastPointIndex(targetPoints, new Point(0, 0, 0)));
        }
        return targetPoints;
    }

    private static boolean individualHasConflict(List<Point> targetPoints, List<Point> refPoints) {
        return targetPoints.size() != refPoints.size()
            || new HashSet<>(targetPoints).size() != new HashSet<>(refPoints).size();
    }

    private static Set<Point> findDuplicatePoints(List<Point> targetPoints) {
        Set<Point> allPoints = new HashSet<>();
        Set<Point> dulPoints = new HashSet<>();

        for (Point point : targetPoints) {
            if (!allPoints.contains(point)) {
                allPoints.add(point);
            } else {
                dulPoints.add(point);
            }
        }
        return dulPoints;
    }

    private static int findLastPointIndex(List<Point> targetPoints, Point target) {
        for (int i = targetPoints.size() - 1; i >= 0 ; i--) {
            if (targetPoints.get(i).equals(target)) {
                return i;
            }
        }
        return targetPoints.size();
    }

    public static List<Integer> generateRandomIdxPair(int size) {
        Random random = new Random();
        int firstIdx = random.nextInt(size);
        int secondIdx = random.nextInt(size);
        if (firstIdx > secondIdx) {
            int temp = firstIdx;
            firstIdx = secondIdx;
            secondIdx = temp;
        }
        return Arrays.asList(firstIdx, secondIdx);
    }
}
