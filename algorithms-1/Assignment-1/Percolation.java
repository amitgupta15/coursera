import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import static junit.framework.TestCase.*;

public class Percolation {

    private int n;
    private int openSiteCount;
    private int[] grid;
    private WeightedQuickUnionUF uf;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Cannot pass a value < 1 to constructor");
        this.n = n;
        this.grid = new int[n*n + 2]; // 2 extra nodes for virtual top and bottom
        this.uf = new WeightedQuickUnionUF(n*n + 2);
    }

    public void open(int row, int col) {
        validate(row, col);
        int x = xyTo1D(row, col);

        if (!isOpen(row, col)) {
            grid[x] = 1;
            openSiteCount++;
        }
        if (row == 1 && isOpen(row, col)) uf.union(x, 0);
        if (row == n) uf.union(x, n*n+1);
        if (col > 1 && isOpen(row, col-1)) connect(x, row, col-1);
        if (col < n && isOpen(row, col+1)) connect(x, row, col+1);
        if (row > 1 && isOpen(row-1, col)) connect(x, row-1, col);
        if (row < n && isOpen(row+1, col)) connect(x, row+1, col);
    }

    private void connect(int x, int row, int col) {
        int y = xyTo1D(row, col);
        uf.union(x, y);
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        int x = xyTo1D(row, col);
        return grid[x] > 0;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int x = xyTo1D(row, col);
        return uf.connected(x, 0);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return uf.connected(0, n*n+1);
    }
    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new IllegalArgumentException("row and col parameters have to be between 1 and n");
    }

    private int xyTo1D(int i, int j) {
        return (i - 1) * n + j;
    }


    /*
    public static void main(String[] args) {

        Percolation p = new Percolation(4);
        assertEquals(18, p.grid.length);

        p.open(1, 1); // 1
        p.open(1, 2); // 2
        p.open(4, 1); // 13

        assertTrue(p.uf.connected(1, 0));
        assertTrue(p.uf.connected(2, 0));
        assertTrue(p.uf.connected(13, 17));
        assertFalse(p.uf.connected(0, 17));

        assertTrue(p.isOpen(4, 1));
        assertTrue(p.isOpen(1, 1));
        assertTrue(p.isOpen(1, 2));

        assertTrue(p.isFull(1, 1));
        assertTrue(p.isFull(1, 2));

        p.open(2, 2);
        assertTrue(p.isOpen(2, 2));
        assertTrue(p.isFull(2, 2));

        assertEquals(4, p.numberOfOpenSites());
        assertFalse(p.percolates());

        p.open(3, 2);
        p.open(3, 1);
        assertTrue(p.isFull(3, 2));
        assertTrue(p.isFull(3, 1));
        assertTrue(p.percolates());
        assertEquals(6, p.numberOfOpenSites());
    }
    */
}
