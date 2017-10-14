import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Iterator;
import java.util.TreeSet;

public class PointSET {

    private final TreeSet<Point2D> pointSet;

    public PointSET() {
        pointSet = new TreeSet<>();
    }

    public boolean isEmpty() {
        return pointSet.isEmpty();
    }

    public int size() {
        return  pointSet.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Cannot insert null in PointSET");
        pointSet.add(p);
    }

    public boolean contains(Point2D p) {
        return pointSet.contains(p);
    }

    public void draw() {
        for (Point2D p : pointSet) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        TreeSet<Point2D> pointsInRange = new TreeSet<>();

        Iterator<Point2D> it = pointSet.iterator();
        while (it.hasNext()) {
            Point2D p = it.next();
            if (rect.contains(p)) pointsInRange.add(p);
        }
        return pointsInRange;
    }

    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;

        Iterator<Point2D> it = pointSet.iterator();
        Point2D thisPoint = it.next();
        double min = p.distanceSquaredTo(thisPoint);
        Point2D minPoint = thisPoint;

        while (it.hasNext()) {
            Point2D thatPoint = it.next();
            double distance = p.distanceSquaredTo(thatPoint);
            if (distance < min) {
                min = distance;
                minPoint = thatPoint;
            }
        }
        return minPoint;
    }

    public static void main(String[] args) {

        Point2D p = new Point2D(0.1, 0.1);
        Point2D q = new Point2D(0.1, 0.2);


        PointSET points = new PointSET();


        System.out.println("Initialized the PointSET object");
        System.out.println("PointSET empty? : " + points.isEmpty());
        System.out.println("PointSET size : " + points.size());

        System.out.println("---------------------------------------------------------\n");

        points.insert(p);
        points.insert(q);
        points.insert(new Point2D(0.4, 0.4));
        points.insert(new Point2D(0.1, 0.4));

        System.out.println("PointSET empty? : " + points.isEmpty());
        System.out.println("PointSET size : " + points.size());
        System.out.println("Does PointSET contain (0.1, 0.1)? : " + points.contains(p));

        // points.draw();

        Iterator<Point2D> it = points.range(new RectHV(0.0, 0.0, 0.4, 0.4)).iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("Nearest Point to (0.1,0.3) is : " + points.nearest(new Point2D(0.1, 0.3)));


    }
}
