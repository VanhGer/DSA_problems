import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  // init
  private static final double CONFIDENCE_95 = 1.96;
  private double numOfTrials;
  private double[] thresholds;

  // perform independent numOfTrials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }
    numOfTrials = trials;
    thresholds = new double[trials];
    for (int i = 0; i < trials; i++) {
      Percolation p = new Percolation(n);
      while (!p.percolates()) {
        int x = StdRandom.uniformInt(1, n + 1);
        int y = StdRandom.uniformInt(1, n + 1);
        p.open(x, y);
      }
      int sites = p.numberOfOpenSites();
      thresholds[i] = (double) sites / ((double) n * (double) n);
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(thresholds);
  }

  // sample standard devitation of percolation threshold
  public double stddev() {
    return StdStats.stddev(thresholds);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return this.mean() - CONFIDENCE_95 * this.stddev() / Math.sqrt(numOfTrials);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return this.mean() + CONFIDENCE_95 * this.stddev() / Math.sqrt(numOfTrials);
  }

  public static void main(String[] args) {
    int n = 64;
    int t = 150;
    n = Integer.parseInt(args[0]);
    t = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(n, t);
    System.out.println("mean                    = " + ps.mean());
    System.out.println("stddev                  = " + ps.mean());
    System.out.println(
        "95% confidence interval = " + "[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
  }
}
