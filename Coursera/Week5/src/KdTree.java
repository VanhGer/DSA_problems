import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

class Node {
  double x;
  double y;
  Node left;
  Node right;
  Point2D point;

  Node() {}

  Node(double x, double y, Point2D point) {
    this.x = x;
    this.y = y;
    this.point = point;
    this.left = null;
    this.right = null;
  }

  public double getY() {
    return y;
  }

  public double getX() {
    return x;
  }
}

public class KdTree {

  private Node root = null;
  private int sz;
  // construct an empty set of points
  public KdTree() {
    sz = 0;
  }
  // is the set empty?
  public boolean isEmpty() {
    return sz == 0;
  }
  // number of points in the set
  public int size() {
    return sz;
  }
  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    root = insert(p, root, true);
  }

  private Node insert(Point2D p, Node root, boolean id) {
    if (root == null) {
      root = new Node(p.x(), p.y(), p);
      sz++;
      return root;
    }
    double c1 = id ? root.getX() : root.getY();
    double c2 = id ? p.x() : p.y();
    if (c1 == c2) {
      if (root.point.equals(p)) return root;
    }
    if (c2 < c1) { // left
      root.left = insert(p, root.left, !id);
    } else {
      root.right = insert(p, root.right, !id);
    }
    return root;
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    if (this.isEmpty()) return false;
    return contains(p, root, true);
  }

  private boolean contains(Point2D p, Node root, boolean id) {
    if (root == null) {
      return false;
    }
    double c1 = id ? root.getX() : root.getY();
    double c2 = id ? p.x() : p.y();
    if (c1 == c2) {
      if (root.point.equals(p)) return true;
    }
    if (c2 < c1) { // left
      return contains(p, root.left, !id);
    } else {
      return contains(p, root.right, !id);
    }
  }
  // draw all points to standard draw
  public void draw() {
    draw(root, new RectHV(0, 0, 1, 1), true);
  }

  private void draw(Node root, RectHV rectHV, boolean id) {
    if (root == null) return;
    StdDraw.setPenColor(StdDraw.BLACK);
    root.point.draw();
    if (id) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.line(root.getX(), rectHV.ymin(), root.getX(), rectHV.ymax());
      draw(root.left, new RectHV(rectHV.xmin(), rectHV.ymin(), root.getX(), rectHV.ymax()), !id);
      draw(root.right, new RectHV(root.getX(), rectHV.ymin(), rectHV.xmax(), rectHV.ymax()), !id);
    } else {
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.line(rectHV.xmin(), root.getY(), rectHV.xmax(), root.getY());
      draw(root.left, new RectHV(rectHV.xmin(), rectHV.ymin(), rectHV.xmax(), root.getY()), !id);
      draw(root.right, new RectHV(rectHV.xmin(), root.getY(), rectHV.xmax(), rectHV.ymax()), !id);
    }
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new IllegalArgumentException();
    List<Point2D> point2DList = new ArrayList<>();
    range(root, rect, point2DList, true);
    return point2DList;
  }

  private void range(Node root, RectHV rect, List<Point2D> point2DList, boolean id) {
    if (root == null) return;
    if (rect.contains(root.point)) {
      point2DList.add(root.point);
    }
    /** vertical */
    if (id) {
      if (rect.xmin() <= root.getX() && root.getX() <= rect.xmax()) {
        range(root.left, rect, point2DList, !id);
        range(root.right, rect, point2DList, !id);
      } else if (rect.xmax() < root.getX()) {
        range(root.left, rect, point2DList, !id);
      } else {
        range(root.right, rect, point2DList, !id);
      }
    } else {
        /** horizontal. */
      if (rect.ymin() <= root.getY() && root.getY() <= rect.ymax()) {
        range(root.left, rect, point2DList, !id);
        range(root.right, rect, point2DList, !id);
      } else if (rect.ymax() < root.getY()) {
        range(root.left, rect, point2DList, !id);
      } else {
        range(root.right, rect, point2DList, !id);
      }
    }
  }
  // a nearest neighbor in the set to point p; null if the set is empty
  
  public Point2D nearest(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }
    if (isEmpty()) return null;
    Node res = new Node(root.getX(), root.getY(), root.point);
    res.left = root.left;
    res.right = root.right;
    RectHV rootRect = new RectHV(0.0, 0.0, 1.0, 1.0);
    nearest(root, rootRect, res, p, true);
    return res.point;
  }

  private void nearest(Node h, RectHV hRect, Node res, Point2D queryP, boolean id) {
    if (h == null) return;

    if (queryP.distanceTo(h.point) < queryP.distanceTo(res.point)) {
      res.point = h.point;
    }

    double hx = h.point.x();
    double hy = h.point.y();
    double x = queryP.x();
    double y = queryP.y();
    double xmin, xmax, ymin, ymax;
    if (id) {
      ymin = hRect.ymin();
      ymax = hRect.ymax();

      xmin = hx;
      xmax = hRect.xmax();
      RectHV rtRect = new RectHV(xmin, ymin, xmax, ymax);

      xmin = hRect.xmin();
      xmax = hx;
      RectHV lbRect = new RectHV(xmin, ymin, xmax, ymax);

      if (x >= hx) {
        nearest(h.right, rtRect, res, queryP, !id);
        if (lbRect.distanceTo(queryP) < queryP.distanceTo(res.point)) {
          nearest(h.left, lbRect, res, queryP, !id);
        }
      } else {
        nearest(h.left, lbRect, res, queryP, !id);
        if (rtRect.distanceTo(queryP) < queryP.distanceTo(res.point)) {
          nearest(h.right, rtRect, res, queryP, !id);
        }
      }
    } else {
      xmin = hRect.xmin();
      xmax = hRect.xmax();

      ymin = hy;
      ymax = hRect.ymax();
      RectHV rtRect = new RectHV(xmin, ymin, xmax, ymax);

      ymin = hRect.ymin();
      ymax = hy;
      RectHV lbRect = new RectHV(xmin, ymin, xmax, ymax);

      if (y >= hy) {
        nearest(h.right, rtRect, res, queryP, !id);
        if (lbRect.distanceTo(queryP) < queryP.distanceTo(res.point)) {
          nearest(h.left, lbRect, res, queryP, !id);
        }
      } else {
        nearest(h.left, lbRect, res, queryP, !id);
        if (rtRect.distanceTo(queryP) < queryP.distanceTo(res.point)) {
          nearest(h.right, rtRect, res, queryP, !id);
        }
      }
    }
  }


  private void dfs(Node root) {
    if (root == null) return;
    System.out.println(root.point);
    dfs(root.left);
    dfs(root.right);
  }

  // unit testing of the methods (optional)
  public static void main(String[] args) {
    KdTree kdTree = new KdTree();

    kdTree.insert(new Point2D(0.372, 0.497)); // A
    kdTree.insert(new Point2D(0.564, 0.413)); // B
    kdTree.insert(new Point2D(0.226, 0.577)); // C
    kdTree.insert(new Point2D(0.144, 0.179)); // D
    kdTree.insert(new Point2D(0.083, 0.51)); // E
    kdTree.insert(new Point2D(0.32, 0.708)); // F
    kdTree.insert(new Point2D(0.417, 0.362)); // G
    kdTree.insert(new Point2D(0.862, 0.825)); // H
    kdTree.insert(new Point2D(0.785, 0.725)); // I
    kdTree.insert(new Point2D(0.499, 0.208)); // J
    // kdTree.draw();
    // kdTree.dfs(kdTree.root);
    // System.out.println(kdTree.nearest(new Point2D(0.078, 0.552)));
     System.out.println(kdTree.nearest(new Point2D(0.53, 0.79)));
    //System.out.println(kdTree.range(new RectHV(0.3, 0.3, 0.6, 0.6)));
  }
}
