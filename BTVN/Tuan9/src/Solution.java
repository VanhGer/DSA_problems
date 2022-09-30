import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MergeSort {
  private static void merge(Integer[] a, Integer[] aux, int lo, int mid, int hi) {
    for (int i = lo; i <= hi; i++)
      aux[i] = a[i];
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) a[k] = aux[j++];
      else if (j > hi) a[k] = aux[i++];
      else if (aux[j] < aux[i]) a[k] = aux[j++];
      else a[k] = aux[i++];

    }
  }

  private static void sort(Integer[] a, Integer[] aux, int lo, int hi) {
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
  }

  public static void sort(Integer[] a) {
    Integer[] aux = new Integer[a.length];
    sort(a, aux, 0, a.length - 1);
  }
}

class Result {

  /*
   * Complete the 'closestNumbers' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts INTEGER_ARRAY arr as parameter.
   */

  public static List<Integer> closestNumbers(List<Integer> arr) {
    int minimum = 10000001;
    Integer[] a = new Integer[arr.size()];
    arr.toArray(a);
    MergeSort.sort(a);
    for (int i = 0; i < a.length - 1; i++) {
      int gap = a[i + 1] - a[i];
      if (gap < minimum) {
        minimum = gap;
      }
    }
    List res = new ArrayList<>();
    for (int i = 0; i < a.length - 1; i++) {
      if (a[i + 1] - a[i] == minimum) {
        res.add(a[i]);
        res.add(a[i + 1]);
      }
    }
    return res;
  }

}
class Solution {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    List <Integer> a = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      a.add(scan.nextInt());
    }
    List<Integer> tmp = Result.closestNumbers(a);
    for (int x : tmp) System.out.print(x +  " ");
  }
}