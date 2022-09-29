import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


public class Solver {

  private boolean solvable;
  private int moves;
  private Node goal;

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) throw new IllegalArgumentException();
    MinPQ<Node> q = new MinPQ<>();
    q.insert(new Node(null, 0, initial, true));
    q.insert(new Node(null, 0, initial.twin(), false));
    Node u = null;
    while (!q.isEmpty()) {
      u = q.delMin();
      if (u.board.isGoal()) break;
      for (Board x : u.board.neighbors()) {
        if (u.preBoard == null || !x.equals(u.preBoard.board)) {
          q.insert(new Node(u, x));
        }
      }
    }
    if (!u.state || !u.board.isGoal()) {
      solvable = false;
      moves = -1;
      goal = null;
    } else {
      goal = u;
      solvable = true;
      moves = goal.moves;
    }
  }

  private class Node implements Comparable<Node> {
    private Node preBoard;
    private int moves;
    private int priority;
    private Board board;
    private boolean state;
    private int manhattan;

    public Node(Node preBoard, int moves, Board board, boolean state) {
      this.preBoard = preBoard;
      this.moves = moves;
      this.board = board;
      this.state = state;
      this.manhattan = this.board.manhattan();
      this.priority = this.manhattan + this.moves;
    }

    public Node(Node preBoard, Board board) {
      this.preBoard = preBoard;
      this.board = board;
      this.moves = preBoard.moves + 1;
      this.state = preBoard.state;
      this.manhattan = this.board.manhattan();
      this.priority = this.manhattan + this.moves;
    }

    @Override
    public int compareTo(Node o) {
      if (this.priority != o.priority) {
        return this.priority - o.priority;
      }
      return this.manhattan - o.manhattan;
    }
  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return this.solvable;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    return this.moves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (goal == null) {
      return null;
    }
    Node cur = goal;
    Stack<Board> res = new Stack<>();
    while (cur != null) {
      res.push(cur.board);
      cur = cur.preBoard;
    }
    return res;
  }

  // test client (see below)
  public static void main(String[] args) {

    // create initial board from file
    In in = new In("puzzle3x3-unsolvable.txt");
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable()) StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution()) StdOut.println(board);
    }
  }
}
