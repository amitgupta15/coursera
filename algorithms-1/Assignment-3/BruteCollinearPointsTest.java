import org.junit.Test;

import static org.junit.Assert.*;

public class BruteCollinearPointsTest {

    @Test (expected = IllegalArgumentException.class)
    public void badConstructionTest() {
        BruteCollinearPoints b = new BruteCollinearPoints(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void badPointTest() {
        Point[] p = {new Point(1,1), null};

        BruteCollinearPoints b = new BruteCollinearPoints(p);
    }

    @Test (expected = IllegalArgumentException.class)
    public void duplicateEntriesTest() {
        Point[] p = {new Point(1,1), new Point(2,2), new Point(1,1)};

        BruteCollinearPoints b = new BruteCollinearPoints(p);
    }

    @Test
    public void collinearPoints8InputTest() {
        Point[] points = {
                new Point(10000, 0),
                new Point(0, 10000),
                new Point(3000, 7000),
                new Point(7000, 3000),
                new Point(20000, 21000),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000),
        };


        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        assertEquals(2, collinear.numberOfSegments());

        assertEquals("(10000, 0) -> (0, 10000)", collinear.segments()[0].toString());
        assertEquals("(3000, 4000) -> (20000, 21000)", collinear.segments()[1].toString());

    }
}
