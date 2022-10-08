import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class SAP {
  private Digraph digraph;

  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    this.digraph = new Digraph(G);
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    BreadthFirstDirectedPaths d1 = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths d2 = new BreadthFirstDirectedPaths(digraph, w);
    int res = Integer.MAX_VALUE;
    int vertexes = digraph.V();
    for (int i = 0; i < vertexes; i++) {
      if (d1.hasPathTo(i) && d2.hasPathTo(i)) res = Math.min(res, d1.distTo(i) + d2.distTo(i));
    }
    if (res == Integer.MAX_VALUE) return -1;
    return res;
  }

  // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    BreadthFirstDirectedPaths d1 = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths d2 = new BreadthFirstDirectedPaths(digraph, w);
    int dis = Integer.MAX_VALUE;
    int res = -1;
    int vertexes = digraph.V();
    for (int i = 0; i < vertexes; i++) {
      if (!d1.hasPathTo(i) || !d2.hasPathTo(i)) continue;
      if (dis > d1.distTo(i) + d2.distTo(i)) {
        res = i;
        dis = d1.distTo(i) + d2.distTo(i);
      }
    }
    if (dis == Integer.MAX_VALUE) return -1;
    return res;
  }

  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such
  // path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null) throw new IllegalArgumentException();
    if (w == null) throw new IllegalArgumentException();
    if (! v.iterator().hasNext()) return -1;
    if (! w.iterator().hasNext()) return -1;
    BreadthFirstDirectedPaths d1 = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths d2 = new BreadthFirstDirectedPaths(digraph, w);
    int res = Integer.MAX_VALUE;
    int vertexes = digraph.V();
    for (int i = 0; i < vertexes; i++) {
      if (d1.hasPathTo(i) && d2.hasPathTo(i)) res = Math.min(res, d1.distTo(i) + d2.distTo(i));
    }
    if (res == Integer.MAX_VALUE) return -1;
    return res;
  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null) throw new IllegalArgumentException();
    if (w == null) throw new IllegalArgumentException();
    if (! v.iterator().hasNext()) return -1;
    if (! w.iterator().hasNext()) return -1;
    BreadthFirstDirectedPaths d1 = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths d2 = new BreadthFirstDirectedPaths(digraph, w);
    int dis = Integer.MAX_VALUE;
    int res = -1;
    int vertexes = digraph.V();
    for (int i = 0; i < vertexes; i++) {
      if (!d1.hasPathTo(i) || !d2.hasPathTo(i)) continue;
      if (dis > d1.distTo(i) + d2.distTo(i)) {
        res = i;
        dis = d1.distTo(i) + d2.distTo(i);
      }
    }
    if (dis == Integer.MAX_VALUE) return -1;
    return res;
  }

  // do unit testing of this class
  public static void main(String[] args) {
    In in = new In("digraph1.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    while (!StdIn.isEmpty()) {
      int v = StdIn.readInt();
      int w = StdIn.readInt();
      int length = sap.length(v, w);
      int ancestor = sap.ancestor(v, w);
      StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
  }
}
