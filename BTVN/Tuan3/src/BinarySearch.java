import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class BinarySearch {
  public static int binarySearch(int[] a, int number) {
    int l = 0;
    int r = a.length;
    while (l <= r) {
      int mid = (l + r) / 2;
      if (a[mid] < number) {
        l = mid + 1;
      } else if (a[mid] > number) {
        r = mid - 1;
      } else return mid;
    }
    return -1;
  }

  public static void main(String[] args) {
    int n = StdRandom.uniformInt(5, 10);
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = StdRandom.uniformInt(1, 10);
    }
    Arrays.sort(a);
    for (int i = 0; i < n; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.print('\n');
    System.out.println(binarySearch(a, 3));
  }
}
