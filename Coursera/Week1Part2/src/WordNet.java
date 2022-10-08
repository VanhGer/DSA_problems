import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

  private final HashMap<Integer, String> idSyn;
  private final HashMap<String, Set<Integer>> nounId;
  private final SAP sap;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    idSyn = new HashMap<Integer, String>();
    nounId = new HashMap<String, Set<Integer>>();
    In in = new In(synsets);
    int id;
    String s;
    while (in.hasNextLine()) {
      s = in.readLine();
      String[] tmp = s.split(",");
      id = Integer.parseInt(tmp[0]);
      idSyn.put(id, tmp[1]);
      String[] nouns = tmp[1].split(" ");
      for (int i = 0; i < nouns.length; i++) {
        if (!nounId.containsKey(nouns[i])) {
          Set<Integer> set = new HashSet<>();
          set.add(id);
          nounId.put(nouns[i], set);
        } else {
          nounId.get(nouns[i]).add(id);
        }
      }
    }
    Digraph digraph = new Digraph(idSyn.size());
    in = new In(hypernyms);
    while (in.hasNextLine()) {
      s = in.readLine();
      String[] tmp = s.split(",");
      int tmpid = Integer.parseInt(tmp[0]);
      for (int i = 1; i < tmp.length; i++) {
        int hypernymsid = Integer.parseInt(tmp[i]);
        digraph.addEdge(tmpid, hypernymsid);
      }
    }

    DirectedCycle dc = new DirectedCycle(digraph);
    if (dc.hasCycle()) throw new IllegalArgumentException();
    int root = 0;
    for (int i = 0; i < digraph.V(); i++) {
      if (!digraph.adj(i).iterator().hasNext()) root++;
    }
    if (root != 1) throw new IllegalArgumentException();
    sap = new SAP(digraph);
  }

  // returns all WordNet nouns
  public Iterable<String> nouns() {
    return nounId.keySet();
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    if (word == null) throw new IllegalArgumentException();
    return nounId.containsKey(word);
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    if (nounA == null || nounB == null) throw new IllegalArgumentException();
    if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
    return sap.length(nounId.get(nounA), nounId.get(nounB));
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    if (nounA == null || nounB == null) throw new IllegalArgumentException();
    if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
    int ancestorId = sap.ancestor(nounId.get(nounA), nounId.get(nounB));
    return idSyn.get(ancestorId);
  }

  // do unit testing of this class
  public static void main(String[] args) {
    WordNet wordnet = new WordNet("synsetst1.txt", "hypernymst1.txt");
    System.out.println(wordnet.distance("wer", "hi"));
  }
}
