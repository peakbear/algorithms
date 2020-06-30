package solver;

import java.util.List;
import model.Point;
import helper.Helper;

public class Solver {

    public void solve() {
        final List<Point> targetPoints = Helper.generatePoints(20, 500, 10);
        final int population = 500;
        final int maxAge = 1000;
        final double r = 0.9;
        final double v = 0.1;
        final int capacity = 20;

        GeneSolver geneSolver = new GeneSolver(targetPoints, population, maxAge, r, v, capacity);
        List<Point> result = geneSolver.run();
        System.out.println(result);
    }

}
