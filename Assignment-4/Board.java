import edu.princeton.cs.algs4.Stack;

public class Board {

    private int[][] tiles;
    private int hamming;
    private int manhattan;
    private int n;

    public Board(int[][] blocks) {

        if (blocks == null) throw new IllegalArgumentException("Cannot pass null in Board constructor");
        hamming = -1;
        manhattan = -1;

        this.tiles = blocks.clone();

        this.n = tiles.length;

        computeHeuristics();
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    public boolean isGoal() {

        return (manhattan == 0) ? true : false;
    }

    public Board twin() {
        int[][] twinBoard = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                twinBoard[i][j] = tiles[i][j];


        if (twinBoard[n-1][n-1] == 0 || twinBoard[n-1][n-2] == 0) {
            int temp = twinBoard[0][0];
            twinBoard[0][0] = twinBoard[0][1];
            twinBoard[0][1] = temp;
        } else {
            int temp = twinBoard[n-1][n-1];
            twinBoard[n-1][n-1] = twinBoard[n-1][n-2];
            twinBoard[n-1][n-2] = temp;
        }
        return new Board(twinBoard);
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;
        if (that.n != this.n) return false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (that.tiles[i][j] != this.tiles[i][j]) return false;
        return true;
    }

    public Iterable<Board> neighbors() {

        Stack<Board> s = new Stack<Board>();
        int zRow = -1;
        int zCol = -1;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (tiles[i][j] == 0) {
                    zRow = i;
                    zCol = j;
                    break;
                }
        if (zRow - 1 >= 0) s.push(neighborBoard(this, zRow, zRow - 1, zCol, zCol));
        if (zRow + 1 <  n) s.push(neighborBoard(this, zRow, zRow + 1, zCol, zCol));
        if (zCol - 1 >= 0) s.push(neighborBoard(this, zRow, zRow, zCol, zCol - 1));
        if (zCol + 1 < n) s.push(neighborBoard(this, zRow, zRow, zCol, zCol + 1));

        return s;
    }

    private Board neighborBoard(Board board, int currRow, int newRow, int currCol, int newCol) {
        int[][] adash = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                adash[i][j] = board.tiles[i][j];

        int temp = adash[currRow][currCol];
        adash[currRow][currCol] = adash[newRow][newCol];
        adash[newRow][newCol] = temp;

        return new Board(adash);
    }
    private void computeHamming(int[][] a, int i, int j, int goal) {
        if ((a[i][j] != 0) && (a[i][j] != goal)) {
            hamming++;
        }
    }

    private void computeManhattan(int[][] a, int i, int j, int goal) {
        if ((a[i][j] != goal) && (a[i][j] != 0)) {
            int row = (a[i][j] - 1) / n;
            int col = (a[i][j] - 1) % n;

            int dx = (row < i) ? i - row : row - i;
            int dy = (col < j) ? j - col : col - j;
            int distance = dx + dy;
            manhattan += distance;
        }
    }

    private void computeHeuristics() {
        hamming = 0;
        manhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int goal = i*n + j + 1;
                if (goal == n*n) goal = 0;
                computeHamming(tiles, i, j, goal);
                computeManhattan(tiles, i, j, goal);
           }
        }
    }
}
