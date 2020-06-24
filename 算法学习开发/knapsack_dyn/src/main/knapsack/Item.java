package main.knapsack;

public class Item {

    private int id;
    private int weight;
    private int value;

    public Item(int id, int weight, int value) {
        this.id = id;
        this.weight = weight;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (id != item.id) {
            return false;
        }
        if (weight != item.weight) {
            return false;
        }
        return value == item.value;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + weight;
        result = 31 * result + value;
        return result;
    }
}
