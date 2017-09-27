import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private int size = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Cannot pass null to the constructor of BruteCollinearPoints");

        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Cannot pass a null point");
        }

        segments = new LineSegment[1];

        Arrays.sort(points);

        computeLineSegments(points);
    }

    public int numberOfSegments() {
        return size;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }


    private void computeLineSegments(Point[] points) {

        for (int i = 0; i < points.length; i++) {
            Point start = points[i];
            Point end = points[i];
            Arrays.sort(points, points[i].slopeOrder());
            int count = 1;
            for (int j = i+1; j < points.length - 1; j++) {
                if ((start.slopeTo(points[j]) - start.slopeTo(points[j+1])) == 0) {
                    end = points[j+1];
                    count++;
                }
            }
            if (count >= 3) {
                if (segments.length == size) resize(size * 2);
                segments[size++] = new LineSegment(start, end);
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

        // read the n points from a file
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
