import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private Board board;
    private Board twin;
    private boolean isSolvable;
    private Stack<Board> boards;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private SearchNode prev;
        private int priority;

        public SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.prev = prev;
            this.moves = moves;
            this.priority = -1;
        }

        private int getPriority() {
            if (priority == -1) {
                priority = moves + board.manhattan();
            }
            return priority;
        }

        @Override
        public int compareTo(SearchNode that) {
            if (this.getPriority() < that.getPriority()) return -1;
            if (this.getPriority() > that.getPriority()) return 1;
            return 0;
        }
    }

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Cannot create Solver with null Board");

        this.board = initial;
        this.twin = initial.twin();
        this.isSolvable = false;
        this.boards = new Stack<>();

        if (board.isGoal()) isSolvable = true;
        if (twin.isGoal()) isSolvable = false;

        solve();
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (!isSolvable) return -1;
        return boards.size() - 1;

    }

    public Iterable<Board> solution() {
        if (!isSolvable) return  null;
        return boards;
    }

    private void solve() {
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> tpq = new MinPQ<SearchNode>();

        insertInitialBoardToPriorityQueue(pq, board);
        insertInitialBoardToPriorityQueue(tpq, twin);

        int count = 0;
        while (count < 100) {
            SearchNode node = pq.delMin();
            SearchNode tnode = tpq.delMin();

            if (tnode.board.isGoal()) {
                isSolvable = false;
                return;
            }

            if (node.board.isGoal()) {
                isSolvable = true;
                pushSolutionBoardsToStack(node);
                return;
            }
            insertNeighborsToPriorityQueue(pq, node);
            insertNeighborsToPriorityQueue(tpq, tnode);
            count++;
        }
    }

    private void pushSolutionBoardsToStack(SearchNode node) {
        boards.push(node.board);
        while (node.prev != null) {
            node = node.prev;
            this.boards.push(node.board);
        }
    }

    private void insertNeighborsToPriorityQueue(MinPQ<SearchNode> pq, SearchNode node) {
        Iterable<Board> neighbors = node.board.neighbors();
        for (Board neighbor : neighbors) {
            if ((node.prev != null) && neighbor.equals(node.prev.board)) continue;
            pq.insert(new SearchNode(neighbor, node.moves++, node));
        }
    }
    private void insertInitialBoardToPriorityQueue(MinPQ<SearchNode> pq, Board initial) {
        pq.insert(new SearchNode(initial, 0, null));
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] blocks = new int[n][n];
        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 2;
        blocks[1][2] = 5;
        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 6;

        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}
