import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;
    private int size = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Cannot pass null to the constructor of BruteCollinearPoints");

        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Cannot pass a null point");
        }

        segments = new LineSegment[1];

        computeLineSegments(points);
    }

    public int numberOfSegments() {
        return size;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    private void computeLineSegments(Point[] points) {
        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            for (int j = i+1; j < points.length; j++) {
                Point q = points[j];
                for (int k = j+1; k < points.length; k++) {
                    Point r = points[k];
                    if ((p.slopeTo(q) - p.slopeTo(r)) != 0) continue;
                    for (int m = k+1; m < points.length; m++) {
                        Point s = points[m];
                        if ((p.slopeTo(q) - p.slopeTo(r)) == 0 && (p.slopeTo(q) - p.slopeTo(s)) == 0) {

                            if (segments.length == size) resize(size * 2);
                            segments[size++] = new LineSegment(p, s);
                        }

                    }
                }
            }
        }
    }

    private void resize(int capacity) {
        LineSegment[] temp = new LineSegment[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = segments[i];
        }
        segments = temp;
    }

    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
