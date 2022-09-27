import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

  private final Point[] points;
  private final int numOfSegments;
  private final LineSegment[] lineSegments;

  /** finds all line segments containing 4 or more points. */
  public FastCollinearPoints(Point[] points) {
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
    int n = this.points.length;
    if (n < 4) {
      lineSegments = new LineSegment[0];
      numOfSegments = 0;
      return;
    }
    List<LineSegment> lineSegmentList = new ArrayList<>();
    for (int i = 0; i < this.points.length; i++) {
      Point opoint = this.points[i];
      int newsz = 0;
      Point tmp[] = new Point[n - 1];
      for (int j = 0; j < this.points.length; j++) {
        if (i != j) {
          tmp[newsz] = this.points[j];
          newsz++;
        }
      }
      Arrays.sort(tmp, opoint.slopeOrder());
      /*for (Point x : tmp) {
        x.show();
        System.out.println(opoint.slopeTo(x));
      }*/
      Point l = opoint, r = opoint;
      int sz = 1;

      for (int j = 0; j < newsz - 1; j++) {
        if (opoint.slopeTo(tmp[j]) == opoint.slopeTo(tmp[j + 1])) {
          if (sz == 1) {
            if (opoint.compareTo(tmp[j]) > 0) {
              l = tmp[j];
            } else {
              r = tmp[j];
            }
            sz++;
          }

          if (opoint.compareTo(tmp[j + 1]) > 0) {
            l = tmp[j + 1];
          } else {
            r = tmp[j + 1];
          }
          sz++;

        } else {
          if (sz >= 4 && opoint.compareTo(l) == 0) {
            LineSegment d = new LineSegment(l, r);
            lineSegmentList.add(d);
          }
          sz = 1;
          l = opoint;
          r = opoint;
        }
      }
      if (sz >= 4 && opoint.compareTo(l) == 0) {
        LineSegment d = new LineSegment(l, r);
        lineSegmentList.add(d);
      }
    }
    this.numOfSegments = lineSegmentList.size();
    this.lineSegments = new LineSegment[lineSegmentList.size()];
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
    In in = new In("input.txt");
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

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
