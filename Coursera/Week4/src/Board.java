import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

  private int[][] a;
  private int sz;
  private int hamming = -1;
  private int manhattan = -1;
  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    a = new int[tiles.length][];
    sz = tiles.length;
    for (int i = 0; i < tiles.length; i++) {
      a[i] = tiles[i].clone();
    }
  }

  // string representation of this board
  public String toString() {
    String res = sz + "\n";
    for (int i = 0; i < sz; i++) {
      res += " ";
      for (int j = 0; j < sz; j++) res += a[i][j] + " ";
      res += "\n";
    }
    return res;
  }

  // board dimension n
  public int dimension() {
    return sz;
  }

  // number of tiles out of place
  public int hamming() {
    if (hamming != -1) {
      return hamming;
    }
    this.hamming = 0;
    for (int i = 0; i < sz; i++) {
      for (int j = 0; j < sz; j++) {
        if (a[i][j] == 0) continue;
        if (a[i][j] != (i * sz + j + 1)) {
          hamming++;
        }
      }
    }
    return hamming;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    if (manhattan != -1)
      return manhattan;
    manhattan = 0;
    for (int i = 0; i < sz; i++)
      for (int j = 0; j < sz; j++) {
        if (a[i][j] == 0) continue;
        int x = (a[i][j] - 1) / sz;
        int y = (a[i][j] - 1) % sz;
        manhattan += Math.abs(x - i) + Math.abs(y - j);
      }
    return manhattan;
  }

  // is this board the goal board?
  public boolean isGoal() {
    if (this.manhattan == -1) {
      return (this.manhattan() == 0);
    }
    return (this.manhattan == 0);
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (y instanceof Board) {
      if (((Board) y).dimension() != this.dimension()) return false;
      return Arrays.deepEquals(this.a, ((Board) y).a);
    }
    else return false;
  }

  private void exch(int x1, int y1, int x2, int y2) {
    int tmp = a[x1][y1];
    a[x1][y1] = a[x2][y2];
    a[x2][y2] = tmp;
  }
  // all neighboring boards
  public Iterable<Board> neighbors() {
    List<Board> boardList = new ArrayList<>();
    int x = -1, y = -1;
    boolean ok = false;
    for (int i = 0; i < sz; i++) {
      for (int j = 0; j < sz; j++) {
        if (a[i][j] == 0) {
          x = i;
          y = j;
          ok = true;
          break;
        }
      }
      if (ok) break;
    }
    if (0 <= x + 1 && x + 1 < sz) {
      Board neighbor = new Board(this.a);
      neighbor.exch(x, y, x + 1, y);
      boardList.add(neighbor);
    }
    if (0 <= x - 1 && x - 1 < sz) {
      Board neighbor = new Board(this.a);
      neighbor.exch(x, y, x - 1, y);
      boardList.add(neighbor);
    }
    if (0 <= y + 1 && y + 1 < sz) {
      Board neighbor = new Board(this.a);
      neighbor.exch(x, y, x, y + 1);
      boardList.add(neighbor);
    }
    if (0 <= y - 1 && y - 1 < sz) {
      Board neighbor = new Board(this.a);
      neighbor.exch(x, y, x, y - 1);
      boardList.add(neighbor);
    }
    return boardList;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    Board twin = new Board(this.a);
    if (sz == 1) return twin;
    if (twin.a[0][0] != 0 && twin.a[0][1] != 0)
      twin.exch(0, 0, 0, 1);
    else twin.exch(sz - 1, sz - 2, sz - 1, sz - 1);
    return twin;
  }

  // unit testing (not graded)
}
