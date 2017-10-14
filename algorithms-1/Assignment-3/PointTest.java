import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void slopeTest() {

        Point point = new Point(1,1);

       assert(point.slopeTo(new Point(1,1)) == Double.NEGATIVE_INFINITY);
       assert(point.slopeTo(new Point(2,2)) == 1.0);
       assert(point.slopeTo(new Point(1,5)) == Double.POSITIVE_INFINITY);
       assert(point.slopeTo(new Point(5,1)) == +0.0);
    }

    @Test
    public void comparisonTest() {
        Point point = new Point(2, 2);

        assertEquals(0, point.compareTo(new Point(2,2)));
        assertEquals(-1, point.compareTo(new Point(3,3)));
        assertEquals(1, point.compareTo(new Point(1,2)));
        assertEquals(1, point.compareTo(new Point(2,1)));
        assertEquals(-1, point.compareTo(new Point(3,2)));
    }

    @Test
    public void comparatorTest() {
        Point point = new Point(2,2);

        Comparator<Point> p = point.slopeOrder();
        assertEquals(-1, p.compare(new Point(1, 1), new Point(2,1)));
        assertEquals(1, p.compare(new Point(2, 1), new Point(1,1)));
    }
}
