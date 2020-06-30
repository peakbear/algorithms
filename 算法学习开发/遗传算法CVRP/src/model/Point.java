package model;

public class Point {

    private int x;
    private int y;
    private int qty;

    public Point(int x, int y, int qty) {
        this.x = x;
        this.y = y;
        this.qty = qty;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getQty() {
        return qty;
    }

    public double distanceTo(Point point) {
        return Math.sqrt(Math.pow((this.x - point.x), 2) + Math.pow((this.y - point.y), 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        if (x != point.x) {
            return false;
        }
        if (y != point.y) {
            return false;
        }
        return qty == point.qty;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + qty;
        return result;
    }

    @Override
    public String toString() {
        return "[(" + x + ", " + y + "), " + qty + "]";
    }
}
