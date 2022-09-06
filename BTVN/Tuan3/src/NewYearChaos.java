import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * problems from Hackerank.
 */

public class NewYearChaos {
    public static void minimumBribes(List<Integer> q) {
        boolean ok = true;
        int res = 0;
        for (int i = q.size() - 1; i >= 0; i--) {
            if (q.get(i) == i + 1) continue;
            else {
                if (i - 1 >= 0 && q.get(i - 1) == i + 1) {
                    res++;
                    Collections.swap(q, i, i - 1);
                } else if (i - 2 >= 0 && q.get(i - 2) == i + 1) {
                    res += 2;
                    Collections.swap(q, i -  2, i - 1);
                    Collections.swap(q, i - 1, i);
                } else {
                    ok = false;
                    break;
                }
            }
        }
        if (ok) {
            System.out.println(res);
        } else {
            System.out.println("Too chaotic");
        }
    }

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int n = scanner.nextInt();
      List a = new ArrayList<>();
      for (int i = 0; i < n; i++) {
          a.add(scanner.nextInt());
      }
      minimumBribes(a);
  }
}
