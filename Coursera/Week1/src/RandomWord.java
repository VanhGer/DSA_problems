import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
  public static void main(String[] args) {
    String surviving_champion = "";
    String new_champion = "";
    int cnt = 0;
    while (!StdIn.isEmpty()) {
      new_champion = StdIn.readString();
      cnt++;
      if (StdRandom.bernoulli(1.0/cnt)) {
        surviving_champion = new_champion;
      }
    }
    System.out.println(surviving_champion);
  }
}
