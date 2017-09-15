import java.util.NoSuchElementException;

public class LinkListQueue {

    private Node first = null;
    private Node last = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(String s) {

        Node oldlast = last;
        last = new Node();
        last.item = s;
        last.next = null;

        if(isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
    }

    public String dequeue() {
        if(isEmpty()) { throw new NoSuchElementException("Queue is empty"); }
        String item = first.item;
        first = first.next;
        return item;
    }



}
