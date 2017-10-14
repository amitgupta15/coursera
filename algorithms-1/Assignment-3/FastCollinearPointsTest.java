import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FastCollinearPointsTest {

    @Test (expected = IllegalArgumentException.class)
    public void nullContructorTest() {

        FastCollinearPoints fp = new FastCollinearPoints(null);

    }

    @Test (expected = IllegalArgumentException.class)
    public void nullArrayItemInConstructorTest() {
        Point[] points = {null, new Point(1,1)};

        FastCollinearPoints fp = new FastCollinearPoints(points);
    }

    @Test
    public void collinearPoints8InputTest() {
        Point[] points = {
                new Point(19000,10000),
                new Point(18000,10000),
                new Point(32000,10000),
                new Point(21000,10000),
                new Point(1234,5678),
                new Point(14000,10000),
        };



        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(1, collinear.numberOfSegments());

        assertEquals("(32000, 10000) -> (14000, 10000)", collinear.segments()[0].toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void duplicateEntriesTest() {
        Point[] p = {new Point(1,1), new Point(2,2), new Point(1,1)};

        FastCollinearPoints b = new FastCollinearPoints(p);
    }
}
