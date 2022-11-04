class QuickUnionUF {
  private int[] parent;  // parent[i] = parent of i
  private int count;     // number of components

  public QuickUnionUF(int n) {
    parent = new int[n];
    count = n;
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
  }

  public int count() {
    return count;
  }

  public int find(int p) {
    validate(p);
    while (p != parent[p])
      p = parent[p];
    return p;
  }

  private void validate(int p) {
    int n = parent.length;
    if (p < 0 || p >= n) {
      throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
    }
  }


  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) return;
    parent[rootP] = rootQ;
    count--;
  }

}