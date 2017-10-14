import java.util.NoSuchElementException;

public class LinkedListStack {

    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void push(String s) {
        Node newFirst = new Node();
        newFirst.item = s;
        newFirst.next = first;
        first = newFirst;
    }

    public String pop() {

        if(first == null) throw new NoSuchElementException("Stack is empty");

        String item = first.item;
        first = first.next;
        return item;
    }
}
