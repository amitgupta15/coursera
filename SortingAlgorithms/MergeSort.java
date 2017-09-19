import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MergeSort {

    public static void merge(int[] a, int[] aux, int lo, int mid, int hi) {

        for(int k = lo; k <= hi; k++) {
            aux[k] = a[k];                      //Copy the array to auxiliary array
        }

        int i = lo, j = mid + 1;

        for (int k=lo; k<=hi; k++) {
            if(i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void sort(int[] a, int[] aux, int lo, int hi) {
        if(hi <= lo) return;

        int mid = lo + (hi - lo) / 2;

        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    public static void main(String[] args) {

        int size = 100;
        int[] a = new int[size];

        for (int i = 0; i< size; i++) {
            a[i] = i;
        }

        StdRandom.shuffle(a);

        for(int i = 0; i< 10; i++) {
            System.out.print(a[i] + " ");
        }

        MergeSort.sort(a);

        StdOut.println();
        for(int i=0; i<10; i++) {
            System.out.print(a[i] + " ");
        }

    }
}
