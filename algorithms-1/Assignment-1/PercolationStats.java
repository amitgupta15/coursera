import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by amit on 10/13/17.
 */
public class PercolationStats {

    private int n;
    private int trials;
    private double[] thresholds;
    private double mean;
    private double stddev;
    private double confidencelo;
    private double confidencehi;
    private final double confidence95 = 1.96;

    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) throw new IllegalArgumentException("n and trials cannot be less than 1");
        this.n = n;
        this.trials = trials;
        this.thresholds = new double[trials];
        performTrials();
        this.mean = StdStats.mean(thresholds);
        this.stddev = StdStats.stddev(thresholds);
        this.confidencelo = mean - confidence95 * stddev / Math.sqrt(trials);
        this.confidencehi = mean + confidence95 * stddev / Math.sqrt(trials);

    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidencelo;
    }

    public double confidenceHi() {
        return confidencehi;
    }

    private void performTrials() {
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                p.open(row, col);
            }
            thresholds[i] = (double) p.numberOfOpenSites()/(n*n);
        }
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(20, 10);
        System.out.println("mean =  " + ps.mean());
        System.out.println("Std Dev = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + " , " + ps.confidenceHi() + "]");
    }
}
