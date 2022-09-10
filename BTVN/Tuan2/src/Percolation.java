import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private int sz;
  private int openSites;
  private boolean[][] a;
  private WeightedQuickUnionUF uftb;
  private WeightedQuickUnionUF uft;
  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    sz = n;
    openSites = 0;
    a = new boolean[n][n];
    uftb = new WeightedQuickUnionUF(n * n + 2);
    uft = new WeightedQuickUnionUF(n * n + 1);
  }
  // check if the cell is on the grids
  private boolean onGrid(int row, int col) {
    return (row <= sz && row >= 1 && col <= sz && col >= 1);
  }

  // change the coordinates
  private int changec(int row, int col) {
    return (row - 1) * sz + col;
  }
  // opens the site (row,col) if it is not open already
  public void open(int row, int col) {
    if (!onGrid(row, col)) {
      throw new IllegalArgumentException();
    }
    if (isOpen(row, col)) {
      return;
    }
    a[row - 1][col - 1] = true;
    openSites += 1;
    if (row == 1) {
      uftb.union(changec(row, col), 0);
      uft.union(changec(row, col), 0);
    }
    if (row == sz) {
      uftb.union(changec(row, col), sz * sz + 1);
    }
    if (onGrid(row - 1, col) && isOpen(row - 1, col)) {
      uftb.union(changec(row, col), changec(row - 1, col));
      uft.union(changec(row, col), changec(row - 1, col));
    }
    if (onGrid(row + 1, col) && isOpen(row + 1, col)) {
      uftb.union(changec(row, col), changec(row + 1, col));
      uft.union(changec(row, col), changec(row + 1, col));
    }
    if (onGrid(row, col - 1) && isOpen(row, col - 1)) {
      uftb.union(changec(row, col), changec(row, col - 1));
      uft.union(changec(row, col), changec(row, col - 1));
    }
    if (onGrid(row, col + 1) && isOpen(row, col + 1)) {
      uftb.union(changec(row, col), changec(row, col + 1));
      uft.union(changec(row, col), changec(row, col + 1));
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    if (!onGrid(row, col)) {
      throw new IllegalArgumentException();
    }
    return a[row - 1][col - 1];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    if (!onGrid(row, col)) {
      throw new IllegalArgumentException();
    }
    return (uft.find(0) == uft.find(changec(row, col)));
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return this.openSites;
  }

  // does the system percolate?
  public boolean percolates() {
    return uftb.find(0) == uftb.find(sz * sz + 1);
  }
}
