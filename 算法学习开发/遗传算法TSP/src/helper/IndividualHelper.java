package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import model.Individual;
import model.Point;

public class IndividualHelper {

    public static Individual crossForBestChild(Individual father, Individual mother) {
        List<Point> fatherCopy = new ArrayList<>(father.getPoints());
        List<Point> motherCopy = new ArrayList<>(mother.getPoints());

        final List<Integer> idxPair = generateRandomIdxPair(fatherCopy.size());

        for (int i = idxPair.get(0); i <= idxPair.get(1); i++) {
            fatherCopy.set(i, mother.getPoints().get(i));
            motherCopy.set(i, father.getPoints().get(i));
        }
        fatherCopy = solveConflict(fatherCopy, motherCopy, idxPair.get(0), idxPair.get(1));
        motherCopy = solveConflict(motherCopy, fatherCopy, idxPair.get(0), idxPair.get(1));

        Individual child1 = new Individual(fatherCopy);
        Individual child2 = new Individual(motherCopy);

        return child1.fitness() >= child2.fitness() ? child1 : child2;
    }

    private static List<Point> solveConflict(List<Point> targetPoints, List<Point> refPoints, int startIdx, int endIdx) {
        while (individualHasConflict(targetPoints)) {
            List<List<Integer>> conflictIdx = findConflictIndex(targetPoints);
            for (List<Integer> idxPair : conflictIdx) {
                int firstIdx = idxPair.get(0);
                int secondIdx = idxPair.get(1);
                int crossedIdx = firstIdx >= startIdx && firstIdx <= endIdx ? firstIdx : secondIdx;
                int changingIdx = crossedIdx == firstIdx ? secondIdx : firstIdx;
                targetPoints.set(changingIdx, refPoints.get(crossedIdx));
            }
        }
        return targetPoints;
    }

    private static boolean individualHasConflict(List<Point> targetPoints) {
        Set<Point> pointsSet= new HashSet<>(targetPoints);
        return pointsSet.size() != targetPoints.size();
    }

    private static List<List<Integer>> findConflictIndex(List<Point> targetPoints) {
        Map<Point, List<Integer>> pointIndexMap = new HashMap<>();
        for (int i = 0; i < targetPoints.size(); i++) {
            if (pointIndexMap.containsKey(targetPoints.get(i))) {
                List<Integer> idxList = pointIndexMap.get(targetPoints.get(i));
                idxList.add(i);
            } else {
                List<Integer> idxList = new ArrayList<>();
                idxList.add(i);
                pointIndexMap.put(targetPoints.get(i), idxList);
            }
        }
        return pointIndexMap.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
            .map(Entry::getValue).collect(Collectors.toList());
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
