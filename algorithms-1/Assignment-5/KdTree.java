
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.NoSuchElementException;

public class KdTree {

    private int n;
    private Node root;

    public KdTree() {
    }

    private static class Node {
        Point2D p;
        RectHV rect;
        Node lb, rt;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return n;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("called insert with a null Point2D");
        RectHV unitSquare = new RectHV(0, 0, 1, 1);
        root = insert(root, p, unitSquare, true);
    }

    private Node insert(Node node, Point2D p, RectHV rect, boolean vertical) {
        if (node == null) {
            ++n;
            return new Node(p, rect);
        }
        RectHV lRect = leftRectangle(node, vertical);
        RectHV rRect = rightRectangle(node, vertical);

        if ((node.p.x() == p.x()) && (node.p.y() == p.y())) return node;

        if ((vertical && (p.x() < node.p.x())) || (!vertical && (p.y() < node.p.y()))) {
            node.lb = insert(node.lb, p, lRect, !vertical);
        } else {
            node.rt = insert(node.rt, p, rRect, !vertical);
        }
        return node;
    }

    private RectHV leftRectangle(Node node, boolean vertical) {
        if (vertical) {
            return new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        } else {
            return new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
        }
    }

    private RectHV rightRectangle(Node node, boolean vertical) {
        if (vertical) {
            return new RectHV(node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
        } else {
            return new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument to contains() is null");
        return contains(root, p, true);
    }

    private boolean contains(Node node, Point2D p, boolean vertical) {
        if (node == null) return false;

        if ((node.p.x() == p.x()) && (node.p.y() == p.y())) return true;

        if ((vertical && (p.x() < node.p.x())) || (!vertical && (p.y() < node.p.y()))) {
            return contains(node.lb, p, !vertical);
        } else {
            return contains(node.rt, p, !vertical);
        }
    }

    public void draw() {
        if (isEmpty()) return;
        draw(root, true);
    }

    private void draw(Node node, boolean vertical) {
        if (node == null) return;
        drawPoint(node.p);
        drawLineSegment(node, vertical);

        draw(node.lb, !vertical);
        draw(node.rt, !vertical);
    }

    private void drawPoint(Point2D p) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        p.draw();
    }

    private void drawLineSegment(Node node, boolean vertical) {
        Point2D p1, p2;
        StdDraw.setPenRadius(0.001);
        if (vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            p1 = new Point2D(node.p.x(), node.rect.ymin());
            p2 = new Point2D(node.p.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            p1 = new Point2D(node.rect.xmin(), node.p.y());
            p2 = new Point2D(node.rect.xmax(), node.p.y());
        }
        p1.drawTo(p2);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("range() argument cannot be null");

        Queue<Point2D> q = new Queue<Point2D>();
        range(root, q, rect);
        return q;
    }

    private void range(Node node, Queue<Point2D> q, RectHV rect) {
        if (node == null) return;
        if (rect.intersects(node.rect)) {
            if (rect.contains(node.p)) {
                q.enqueue(node.p);
            }
            range(node.lb, q, rect);
            range(node.rt, q, rect);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("nearest() cannot have null argument");
        if (isEmpty()) throw new NoSuchElementException("Tree is empty");
        return nearest(root, p, root.p, true);
    }

    private Point2D nearest(Node node, Point2D p, Point2D c, boolean vertical) {
        Point2D closest = c;
        if (node == null) return closest;

        if (node.p.distanceSquaredTo(p) < closest.distanceSquaredTo(p)) closest = node.p;

        if (node.rect.distanceSquaredTo(p) < closest.distanceSquaredTo(p)) {
            Node near, far;
            if ((vertical && (p.x() < node.p.x())) || (!vertical && (p.y() < node.p.y()))) {
                near = node.lb;
                far = node.rt;
            } else {
                near = node.rt;
                far = node.lb;
            }

            closest = nearest(near, p, closest, !vertical);
            closest = nearest(far, p, closest, !vertical);
        }
        return  closest;
    }

    public static void main(String[] args) {
        /*
        String filename = args[0];
        In in = new In(filename);
        KdTree kdt = new KdTree();

        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            kdt.insert(new Point2D(x,y));
        }
        kdt.draw();
        */

        KdTree k = new KdTree();
        Point2D[] p = {new Point2D(0.8, 0.5),
                new Point2D(0.4, 0.3),
                new Point2D(0.2, 0.7),
                new Point2D(0.5, 0.8)};

        for (int i = 0; i < p.length; i++) k.insert(p[i]);
        k.draw();


    }
}
