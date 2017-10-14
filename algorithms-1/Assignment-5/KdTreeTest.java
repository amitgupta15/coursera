import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.TestCase.*;

public class KdTreeTest {

    @Test
    public void emptyTest() {
        KdTree kdt = new KdTree();
        assertTrue(kdt.isEmpty());
        assertEquals(0, kdt.size());
    }

    @Test
    public void uniqueValueTest() {
        KdTree kdt = new KdTree();
        Point2D p = new Point2D(0.1,0.1);
        kdt.insert(p);
        kdt.insert(p);

        assertFalse(kdt.isEmpty());
        assertEquals(1, kdt.size());
    }

    @Test
    public void insertTest() {
        KdTree k = new KdTree();
        k.insert(new Point2D(0.1,0.1));
        k.insert(new Point2D(0.1,0.2));
        k.insert(new Point2D(0.1,0.3));
        k.insert(new Point2D(0.1,0.4));

        assertEquals(4, k.size());

    }

    @Test
    public void containsTest() {
        KdTree k = new KdTree();
        Point2D[] p = {new Point2D(0.8,0.5),
                new Point2D(0.4,0.3),
                new Point2D(0.2,0.7),
                new Point2D(0.5,0.8)};

        for(int i = 0; i<p.length; i++) k.insert(p[i]);

        assertEquals(4, k.size());
        assertTrue(k.contains(p[0]));
        assertTrue(k.contains(p[1]));
        assertTrue(k.contains(p[2]));
        assertTrue(k.contains(p[3]));
    }

    @Test
    public void rangeTest() {
        KdTree k = new KdTree();
        Point2D[] p = {new Point2D(0.8,0.5),
                new Point2D(0.4,0.3),
                new Point2D(0.2,0.7),
                new Point2D(0.5,0.8)};

        for(int i = 0; i<p.length; i++) k.insert(p[i]);

        RectHV rect = new RectHV(0.1,0.5,0.7,0.9);
        Iterator<Point2D> it = k.range(rect).iterator();
        int count = 0;
        while(it.hasNext()) {
            count++;
            it.next();
        }
        assertEquals(2, count);

    }

    @Test
    public void nearestTest() {
        KdTree k = new KdTree();
        Point2D[] p = {new Point2D(0.8,0.5),
                new Point2D(0.4,0.3),
                new Point2D(0.2,0.7),
                new Point2D(0.5,0.8)};

        for(int i = 0; i<p.length; i++) k.insert(p[i]);

        Point2D nearest = k.nearest(new Point2D(0.1,0.6));
        assertEquals(new Point2D(0.2,0.7), nearest);

    }

}
