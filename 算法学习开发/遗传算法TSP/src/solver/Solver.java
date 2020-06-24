package solver;

import java.util.List;
import model.Point;
import helper.Helper;

public class Solver {

    public void solve() {
        final List<Point> targetPoints = Helper.generatePoints(100, 500);
        final int population = 100;
        final int maxAge = 2000;
        final double r = 0.9;
        final double v = 0.2;

        GeneSolver geneSolver = new GeneSolver(targetPoints, population, maxAge, r, v);
        List<Point> result = geneSolver.run();
        System.out.println(result);
    }

}
