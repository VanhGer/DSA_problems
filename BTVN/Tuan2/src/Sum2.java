import java.util.Scanner;

public class Sum2 {
  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int n = scanner.nextInt();
      int []a = new int[n];
      for (int i = 0; i < n; i++)
          a[i] = scanner.nextInt();
    for (int i = 0; i < n; i++)
      for (int j = i + 1; j < n; j++)
          if (a[i] + a[j] == 0) System.out.println(i + " " + j);
  }
}
