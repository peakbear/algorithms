package model;

import helper.IndividualBuilder;
import helper.Helper;
import helper.IndividualHelper;
import java.util.List;
import org.junit.jupiter.api.Test;

class IndividualTest {

    IndividualHelper individualHelper = new IndividualHelper();

    @Test
    void testMutate() {
        Individual individual = new Individual(Helper.generatePoints(5, 100));
        System.out.println(individual);
        individual.mutate();
        System.out.println(individual);
    }

    @Test
    void testCross() {
        List<Point> points  = Helper.generatePoints(5, 100);
        IndividualBuilder individualBuilder = new IndividualBuilder().withPoints(points);
        Individual i1 = individualBuilder.buildRandomIndividual();
        Individual i2 = individualBuilder.buildRandomIndividual();
        System.out.println(i1);
        System.out.println(i2);
        Individual child = individualHelper.crossForBestChild(i1, i2);
        System.out.println(child);
    }

    @Test
    void testIndividualBuilder() {
        List<Point> points  = Helper.generatePoints(5, 100);
        IndividualBuilder individualBuilder = new IndividualBuilder().withPoints(points);
        System.out.println(individualBuilder.buildRandomIndividual());
        System.out.println(individualBuilder.buildRandomIndividual());
    }
}