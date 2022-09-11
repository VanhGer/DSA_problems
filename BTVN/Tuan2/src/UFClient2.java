import edu.princeton.cs.algs4.UF;

import java.util.Scanner;

public class UFClient2 {
  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int N = Integer.parseInt(scanner.nextLine());
      UF uf = new UF(N);
      int cnt = 0;
      while (scanner.hasNextLine()) {
          int u = scanner.nextInt();
          int v = scanner.nextInt();
          cnt++;
          if (! uf.connected(u, v)) {
              uf.union(u, v);
          }
          if (uf.count() == 1) {
              System.out.println(cnt);
              break;
          }
      }

      if (uf.count() != 1) {
        System.out.println("FAILED");
      }
  }
}
