import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);
    RandomizedQueue<String> rq = new RandomizedQueue<>();
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      rq.enqueue(s);
    }
    Iterator<String> it = rq.iterator();
    while (k != 0) {
      System.out.println(it.next());
      k--;
    }
  }
}
