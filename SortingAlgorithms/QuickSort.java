import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {

    public int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;

        while(true) {
            while(a[++i].compareTo(a[lo]) < 0) {
                if(i == hi) break;
            }
            while(a[lo].compareTo(a[--j]) < 0) {
                if(j == lo) break;
            }
            if(i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        System.out.println("Quick Sort\n");

        Integer[] a = new Integer[10];
        a[0] = 1;
        a[1] = 4;
        a[2] = 6;
        a[3] = 9;
        a[4] = 5;
        a[5] = 0;
        a[6] = 7;
        a[7] = 3;
        a[8] = 8;
        a[9] = 2;

        String[] s = new String[10];
        s[0] = "p";
        s[1] = "o";
        s[2] = "o";
        s[3] = "j";
        s[4] = "a";
        s[5] = "g";
        s[6] = "u";
        s[7] = "p";
        s[8] = "t";
        s[9] = "a";

        for(String item: s) {
            System.out.print(item + " ");
        }

        QuickSort q = new QuickSort();
        q.sort(s);

        System.out.println();
        for(String item: s) {
            System.out.print(item + " ");
        }
    }
}
