package main.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Pack {

    private static final int MAX_WEIGHT = 100;
    private List<Item> items = new ArrayList<>();

    public Pack() {
    }

    public Pack(List<Item> items) {
        this.items = new ArrayList<>(items);
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int getWeight() {
        return items.stream().mapToInt(Item::getWeight).sum();
    }

    public int getValue() {
        return items.stream().mapToInt(Item::getValue).sum();
    }

    public boolean canFit(Item item) {
        return this.getWeight() + item.getWeight() <= MAX_WEIGHT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pack pack = (Pack) o;

        return items != null ? items.equals(pack.items) : pack.items == null;
    }

    @Override
    public int hashCode() {
        return items != null ? items.hashCode() : 0;
    }
}
