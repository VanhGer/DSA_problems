import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

  private final Point[] points;
  private final int numOfSegments;
  private final LineSegment[] lineSegments;

  /** finds all line segments containing 4 points, */
  public BruteCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException();
    }

    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) {
        throw new IllegalArgumentException();
      }
    }

    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        if (points[i].compareTo(points[j]) == 0) {
          throw new IllegalArgumentException();
        }
      }
    }

    this.points = new Point[points.length];
    for (int i = 0; i < points.length; i++) {
      this.points[i] = points[i];
    }

    Arrays.sort(this.points);
    List<LineSegment> lineSegmentList = new ArrayList<>();
    for (int i = 0; i < this.points.length - 3; i++) {
      for (int j = i + 1; j < this.points.length - 2; j++) {
        for (int k = j + 1; k < this.points.length - 1; k++) {
          for (int l = k + 1; l < this.points.length; l++) {
            if (this.points[i].slopeTo(this.points[j]) == this.points[i].slopeTo(this.points[k])
                && this.points[i].slopeTo(this.points[j])
                    == this.points[i].slopeTo(this.points[l])) {
              LineSegment d = new LineSegment(this.points[i], this.points[l]);
              lineSegmentList.add(d);
            }
          }
        }
      }
    }

    this.lineSegments = new LineSegment[lineSegmentList.size()];
    this.numOfSegments = lineSegmentList.size();
    for (int i = 0; i < lineSegmentList.size(); i++) {
      lineSegments[i] = lineSegmentList.get(i);
    }
  }

  /** the number of line segments. */
  public int numberOfSegments() {
    return numOfSegments;
  }

  /** the line segments. */
  public LineSegment[] segments() {
    return Arrays.copyOf(this.lineSegments, numberOfSegments());
  }

  public static void main(String[] args) {

    // read the n points from a file
    In in = new In("input8.txt");
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    System.out.println(collinear.numberOfSegments());
    System.out.println(collinear.numberOfSegments());
    System.out.println(collinear.numberOfSegments());
    System.out.println(collinear.numberOfSegments());
    System.out.println(collinear.numberOfSegments());
  }
}
