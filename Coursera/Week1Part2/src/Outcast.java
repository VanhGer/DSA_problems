import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

  private final WordNet wordnet;

  // constructor takes a WordNet object
  public Outcast(WordNet wordnet) {
    this.wordnet = wordnet;
  }
  // given an array of WordNet nouns, return an outcast
  public String outcast(String[] nouns) {
    String res = null;
    int cur = 0;
    for (int i = 0; i < nouns.length; i++) {
      int tmp = 0;
      for (int j = 0; j < nouns.length; j++) {
        tmp += wordnet.distance(nouns[i], nouns[j]);
      }
      if (tmp > cur) {
        cur = tmp;
        res = nouns[i];
      }
    }
    return res;
  }
  // see test client below
  public static void main(String[] args) {
    WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
    Outcast outcast = new Outcast(wordnet);
    In in = new In("outcast5.txt");
    String[] nouns = in.readAllStrings();
    StdOut.println("outcast5.txt" + ": " + outcast.outcast(nouns));
  }
}
