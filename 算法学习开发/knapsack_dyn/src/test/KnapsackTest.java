package test;

import java.util.ArrayList;
import java.util.List;
import main.knapsack.Item;
import main.knapsack.Knapsack;
import main.knapsack.Pack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KnapsackTest {

    private static List<Item> createCandidateItems1() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 20, 20));
        items.add(new Item(2, 30, 30));
        items.add(new Item(3, 40, 44));
        items.add(new Item(4, 50, 55));
        items.add(new Item(5, 60, 60));

        return items;
    }

    private static Pack expectedPack1() {
        Pack pack = new Pack();
        pack.addItem(new Item(1, 20, 20));
        pack.addItem(new Item(2, 30, 30));
        pack.addItem(new Item(4, 50, 55));
        return pack;
    }

    @Test
    void test1() {
        List<Item> candidates = createCandidateItems1();
        Pack pack = new Pack();
        Pack result = Knapsack.packing(pack, candidates);

        Assertions.assertEquals(expectedPack1(), result);
    }

    private static List<Item> createCandidateItems2() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 20, 20));
        items.add(new Item(2, 30, 30));
        items.add(new Item(3, 40, 10));
        items.add(new Item(4, 50, 55));
        items.add(new Item(5, 60, 100));

        return items;
    }

    private static Pack expectedPack2() {
        Pack pack = new Pack();
        pack.addItem(new Item(2, 30, 30));
        pack.addItem(new Item(5, 60, 100));
        return pack;
    }

    @Test
    void test2() {
        List<Item> candidates = createCandidateItems2();
        Pack pack = new Pack();
        Pack result = Knapsack.packing(pack, candidates);

        Assertions.assertEquals(expectedPack2(), result);
    }


}