import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * problems from Hackerank.
 */
public class SherlockAndArray {
    public static String balancedSums(List<Integer> arr) {
        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        boolean res = false;
        int left = 0;
        for (int i = 0; i < arr.size(); i++) {
            int right = sum - left - arr.get(i);
            if (left == right) {
                res = true;
                break;
            }
            left += arr.get(i);
        }
        if (res) return "YES";
        else return "NO";
    }

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int t = scanner.nextInt();
      for (int test = 0; test < t; test++) {
          int n = scanner.nextInt();
          List a = new ArrayList<>();
          for (int i = 0; i < n; i++) {
              a.add(scanner.nextInt());
          }
          System.out.println(balancedSums(a));
      }
  }
}
