import java.util.Scanner;

public class SelectionSort {

  private static void swap(Comparable[] a, int i, int j) {
    Comparable tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }
  public static void sort(Comparable[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      int min = i;
      for (int j = i + 1; j < n; j++)
        if (less(a[j], a[min])) {
          min = j;
        }
      swap(a, i, min);
    }
  }
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++)
      a[i] = scanner.nextDouble();
    long start = System.currentTimeMillis();
    sort(a);
    long end = System.currentTimeMillis();
    for (int i = 0; i < n; i++)
      System.out.println(a[i]);
    System.out.println(end + " " + start);
  }
}
