import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/**
 * problems from Hackerank.
 */

public class Pairs {

    public static int pairs(int k, List<Integer> arr) {
        int l = 0;
        int r = 0;
        int res = 0;
        int precnt = 0;
        Collections.sort(arr);
        while (r < arr.size() && l < arr.size()) {
            int gap = arr.get(r) - arr.get(l);
            if (gap < k) {r++;}
            else if (gap > k) {
                if (l != arr.size()) {
                    while (arr.get(l) == arr.get(l + 1)) {
                        res += precnt;
                        l++;
                    }
                }
                l++;
                precnt = 0;
            }
            else {
                if (l != 0 && arr.get(l) == arr.get(l - 1)) {
                    res += precnt;
                    l++;
                }else {
                    precnt++;
                    res++;
                    r++;
                }
            }
        }
        l++;
        System.out.println(res + " " + precnt + " " + l);
        while (l < arr.size() && precnt > 0) {
            if (arr.get(l) == arr.get(l - 1)) {
                res += precnt;
                l++;
            } else break;
        }
        return res;
    }

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int n = scanner.nextInt();
      int k = scanner.nextInt();
      List a = new ArrayList<>();
      for (int i = 0; i < n; i++) {
          a.add(scanner.nextInt());
      }
      System.out.println(pairs(k, a));
  }
}
