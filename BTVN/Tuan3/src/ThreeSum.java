import java.util.Arrays;
import java.util.Scanner;

public class ThreeSum {
    public static long Triplets(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int l = 0;
        int r = n - 1;
        long res = 0;
        for (int i = 0; i < n - 2; i ++) {
            l = i + 1;
            r = n - 1;
            while (l < r) {
                if (a[i] + a[l] + a[r] == 0) {
                    res++;
                    l++;
                    r--;
                } else if (a[i] + a[l] + a[r] > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int n = scanner.nextInt();
      int[] a = new int[n];
      for (int i = 0; i < n; i++)
          a[i] = scanner.nextInt();
      System.out.println(Triplets(a));
  }
}
