import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UnionFind {
  private int[] id;
  private int[] sz;

  public UnionFind(int N) {
    id = new int[N];
    sz = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
      sz[i] = 0;
    }
  }

  public int find(int p) {
    while (id[p] != p) {
      id[p] = id[id[p]];
      p = id[p];
    }
    return p;
  }

  public void union(int p, int q) {
    int rootp = find(p);
    int rootq = find(q);
    if (rootp == rootq) {
      return;
    }
    if (sz[p] < sz[q]) {
      sz[q] += sz[p];
      id[p] = q;
    } else {
      sz[p] += sz[q];
      id[q] = p;
    }
  }

  boolean connected(int p, int q) {
    return (find(p) == find(q));
  }

  public static void main(String[] args) {
    int N = StdIn.readInt();
    UnionFind uf = new UnionFind(N);
    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (!uf.connected(p, q)) {
        uf.union(p, q);
        StdOut.println(p + " vs " + q);
      }
    }
  }
}
