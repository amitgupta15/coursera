public class QuickSort {

    public int partition(int[] a, int low, int high) {
        
        int i = low;
        int j = high + 1;
        
        while(true) {
            
            while(a[++i] <= a[low]) { 
                if(i == high) break;
            }
            
            while(a[low] <= a[--j]) {
                if(j == low) break;
            }
            
            if(i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, low, j);
        return j;
    }
    
    private void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static void main(String[] args) {
        
        System.out.println("QuickSort");
    }
}