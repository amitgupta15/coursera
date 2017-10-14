import org.junit.Test;

public class SolverTest {

    @Test (expected = IllegalArgumentException.class)
    public void nullTest() {
        Solver s = new Solver(null);
    }

    @Test
    public void solverTest() {
        int[][] blocks = new int[3][3];

        blocks[0][0] = 8;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 0;
        blocks[1][2] = 2;
        blocks[2][0] = 7;
        blocks[2][1] = 6;
        blocks[2][2] = 5;

        Solver s = new Solver(new Board(blocks));
    }


}
