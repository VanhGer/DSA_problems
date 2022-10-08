import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class PointSET {
  private Set<Point2D> pointSet = null;
  // construct an empty set of points
  public PointSET() {
    pointSet = new TreeSet<>();
  }
  // is the set empty?
  public boolean isEmpty() {
    return pointSet.size() == 0;
  }
  // number of points in the set
  public int size() {
    return pointSet.size();
  }
  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    pointSet.add(p);
  }
  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    return pointSet.contains(p);
  }
  // draw all points to standard draw
  public void draw() {
    for (Point2D p : pointSet) {
      p.draw();
    }
  }
  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new IllegalArgumentException();
    List<Point2D> pointList = new ArrayList<>();
    for (Point2D p : pointSet) {
      if (rect.contains(p)) {
        pointList.add(p);
      }
    }
    return pointList;
  }
  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    double dis = Double.MAX_VALUE;
    Point2D res = null;
    for (Point2D p2 : pointSet) {
      if (p2.distanceTo(p) < dis) {
        dis = p2.distanceTo(p);
        res = p2;
      }
    }
    return res;
  }
  // unit testing of the methods (optional)
  public static void main(String[] args) {
    PointSET PointSet = new PointSET();

    PointSet.insert(new Point2D(0.7, 0.2)); // A
    PointSet.insert(new Point2D(0.5, 0.4)); // B
    PointSet.insert(new Point2D(0.2, 0.3)); // C
    PointSet.insert(new Point2D(0.4, 0.7)); // D
    PointSet.insert(new Point2D(0.9, 0.6)); // E
    // PointSet.draw();
    // PointSet.dfs(PointSet.root);
    /*System.out.println(PointSet.nearest(new Point2D(0.078, 0.552)));
    System.out.println(PointSet.nearest(new Point2D(0.684, 0.73)));*/
    System.out.println(PointSet.nearest(new Point2D(0.574, 0.69)));
  }
}
