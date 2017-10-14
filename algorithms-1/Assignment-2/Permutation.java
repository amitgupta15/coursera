import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        RandomizedQueue<String> d = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            d.enqueue(StdIn.readString());
        }

        for (int i = 0; i <  n; i++) {
            StdOut.println(d.dequeue());
        }

    }
}
