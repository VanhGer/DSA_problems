import java.util.*;

/**
 * problems from Hackerank.
 */
public class ClosestNumbers {
    public static List<Integer> closestNumbers(List<Integer> arr) {
        int minimum = 10000001;
        Collections.sort(arr);
        for (int i = 0; i < arr.size() - 1; i++) {
            int gap = arr.get(i + 1) - arr.get(i);
            if (gap < minimum) {
                minimum = gap;
            }
        }
        List res = new ArrayList<>();
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i + 1) - arr.get(i) == minimum) {
                res.add(arr.get(i));
                res.add(arr.get(i + 1));
            }
        }
        return res;
    }

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int n = scanner.nextInt();
      List a = new ArrayList<>();
      for (int i = 0; i < n; i++) {
          a.add(scanner.nextInt());
      }
      List res = closestNumbers(a);
      for (int i = 0; i < res.size(); i++) {
          System.out.print(res.get(i) + " ");
      }
  }
}
