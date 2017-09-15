public class ArrayBasedStack<Item> {

    private Item[] s;
    private int N = 0;

    public ArrayBasedStack() {
        s = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if(N == s.length) { resize(s.length * 2); }
        s[N++] = item;
    }

    public Item pop() {
        if(N == 0) { throw new IndexOutOfBoundsException("Stack is empty"); }
        Item item = s[--N];
        s[N] = null;
        if(N == s.length/4) { resize(s.length / 2); }
        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for(int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
