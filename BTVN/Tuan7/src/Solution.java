import java.io.*;
import java.util.*;

public class Solution {

    private static int qsort(List<Integer> a, int lo, int hi, int k) {
        if (hi <= lo) return a.get(hi);
        int lt = lo, gt = hi;
        int pivot = a.get(lo);
        int i = lo;
        while (i <= gt) {
            int cmp = a.get(i).compareTo(pivot);
            if (cmp > 0) {
                Collections.swap(a, i, gt--);
            } else if (cmp < 0) {
                Collections.swap(a, lt++, i++);
            } else i++;
        }
        if (lt - 1 >= k) return qsort(a, lo, lt - 1, k);
        if (gt + 1 <= k) return qsort(a, gt + 1, hi, k);
        return pivot;

    }

    public static int findMedian(List<Integer> arr) {
        // Write your code here
        return qsort(arr, 0, arr.size() - 1, arr.size() / 2);
    }

  public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     int n = scanner.nextInt();
     List<Integer> a = new ArrayList<>();
     for (int i = 0; i < n; i++)
         a.add(scanner.nextInt());
    System.out.println(findMedian(a));
  }
}