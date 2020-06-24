package main.knapsack;

import java.util.List;
import java.util.stream.Collectors;

public class Knapsack {

    public static Pack packing(Pack pack, List<Item> items) {

        if (items.isEmpty()) {
            return pack;
        }

        List<Item> candidates = items.stream().filter(pack::canFit).collect(Collectors.toList());
        if (candidates.isEmpty()) {
            return pack;
        }

        Item targetItem = candidates.get(0);
        candidates.remove(targetItem);

        Pack pack1 = new Pack(pack.getItems());
        pack1.addItem(targetItem);
        pack1 = packing(pack1, candidates);

        Pack pack2 = packing(pack, candidates);

        if (pack1.getValue() >= pack2.getValue()){
            return pack1;
        }

        return pack2;
    }

}
