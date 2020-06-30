public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[] arr = {1, 3, 2, 4, 5};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);
        System.out.println(arr);
    }
}
