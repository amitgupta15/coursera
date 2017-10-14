public class SelectionSort {

    public static void sort(Comparable[] a) {

        int size = a.length;
        for(int i=0; i<size; i++) {
            int min = i;

            for(int j=i+1; j<size; j++) {
                if(a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("Selection Sort\n");

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

        SelectionSort.sort(s);

        System.out.println();
        for(String item: s) {
            System.out.print(item + " ");
        }
    }

}
