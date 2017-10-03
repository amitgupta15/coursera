import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class BoardTest {

    @Test (expected = IllegalArgumentException.class)
    public void nullTest() {
        Board board = new Board(null);
    }

    @Test
    public void dimensionTest() {
        int n = 3;
        int[][] blocks = new int[n][n];
        Board board = new Board(blocks);

        assertEquals(3, board.dimension());
    }

    @Test
    public void hammingTest() {
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

        Board board = new Board(blocks);

        assertEquals(5, board.hamming());
    }

    @Test
    public void manhattanTest() {
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

        Board board = new Board(blocks);

        assertEquals(10, board.manhattan());
    }

    @Test
    public void isGoalTest() {
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

        Board board = new Board(blocks);

        assertFalse(board.isGoal());

        int[][] tiles = new int[3][3];

        tiles[0][0] = 1;
        tiles[0][1] = 2;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 5;
        tiles[1][2] = 6;
        tiles[2][0] = 7;
        tiles[2][1] = 8;
        tiles[2][2] = 0;

        Board goalBoard = new Board(tiles);

        assertTrue(goalBoard.isGoal());
    }

    @Test
    public void twinBoardTest() {
        int[][] blocks = new int[3][3];

        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 2;
        blocks[2][0] = 7;
        blocks[2][1] = 6;
        blocks[2][2] = 8;

        Board board = new Board(blocks);
        Board twin = board.twin();

        //making sure the twin does not modify the original array
        assertEquals(6, blocks[2][1]);
        assertEquals(8, blocks[2][2]);
    }

    @Test
    public void equalsTest() {

        int[][] blocks = new int[3][3];

        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 2;
        blocks[2][0] = 7;
        blocks[2][1] = 6;
        blocks[2][2] = 8;

        Board board1 = new Board(blocks);

        int[][] tiles = new int[2][2];
        tiles[0][0] = 0;
        tiles[0][1] = 1;

        Board board2 = new Board(tiles);

        assertFalse(board1.equals(board2));

        Board board3 = new Board(blocks);

        assertTrue(board1.equals(board3));

    }

    @Test
    public void neighborsTest() {
        int[][] blocks = new int[3][3];

        blocks[0][0] = 4;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 5;
        blocks[1][1] = 0;
        blocks[1][2] = 2;
        blocks[2][0] = 7;
        blocks[2][1] = 6;
        blocks[2][2] = 8;

        Board board1 = new Board(blocks);
        Iterator<Board> it = board1.neighbors().iterator();

        int count = 0;
        while(it.hasNext()) {
            it.next();
            count++;
        }

        assertEquals(4, count);
    }
}
