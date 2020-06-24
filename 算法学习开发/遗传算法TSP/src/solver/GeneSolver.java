package solver;

import static helper.IndividualHelper.crossForBestChild;

import helper.Helper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import model.Individual;
import model.Point;
import painter.DrawLines;

public class GeneSolver {

    private static Random random = new Random();

    List<Individual> individuals;
    private int population;
    private int maxAge;
    private double r; // rate for cross
    private double v; // rate for mutate

    public GeneSolver(List<Point> points, int population, int maxAge, double r, double v) {
        this.individuals = Helper.createFirstGen(points, population);
        this.population = population;
        this.maxAge = maxAge;
        this.r = r;
        this.v = v;
        sortIndividuals(individuals);
        updatePCum(individuals);
    }

    public List<Point> run() {
        int age = 0;
        while (age < maxAge) {
            List<Individual> nextGen = new ArrayList<>(getTopPlayers(individuals));
            List<Individual> children = new ArrayList<>();
            for (int i = 0; i < population - nextGen.size(); i++) {
                Individual father = chooseRandomIndividual(individuals);
                Individual mother = chooseRandomIndividual(individuals);
                Individual child = crossForBestChild(father, mother);
                children.add(child);
            }
            Collections.shuffle(children);
            for (int i = 0; i < (int)(population * v); i++) {
                children.get(i).mutate();
            }
            nextGen.addAll(children);
            individuals = nextGen;
            sortIndividuals(individuals);
            updatePCum(individuals);

            System.out.println(individuals.get(0).fitness() + ", "
                + new HashSet<>(individuals).size());
            if (age % 10 == 0) DrawLines.drawLines(individuals.get(0).getPoints());

            age++;

        }

//        individuals.forEach(i -> System.out.println(i.fitness()));
        return individuals.get(0).getPoints();

    }

    public void sortIndividuals(List<Individual> individuals) {
        individuals.sort(Comparator.comparing(Individual::fitness).reversed());
    }

    private List<Individual> getTopPlayers(List<Individual> individuals) {
        return individuals.subList(0, (int)((1-r)*population));
    }

    private void updatePCum(List<Individual> individuals) {
        double fitnessSum = individuals.stream().mapToDouble(Individual::fitness).sum();
        double lastPCum = 0;
        for(Individual i : individuals) {
            i.updateP(fitnessSum);
            i.updatePCum(lastPCum);
            lastPCum = i.getpCum();
        }
    }

    private Individual chooseRandomIndividual(List<Individual> individuals) {
        double p = random.nextDouble();
        Optional<Individual> target = individuals.stream()
            .filter(i -> i.getpCum() >= p).findFirst();
        return target.orElse(null);
    }




}
