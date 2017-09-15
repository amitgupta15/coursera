import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by amit on 9/12/17.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkNullItem(item);

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        checkNullItem(item);

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        checkRemoveFromEmptyDeque();

        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = first;
        } else {
            first.prev = null;
        }
        return item;
    }

    public Item removeLast() {
        checkRemoveFromEmptyDeque();

        Item item = last.item;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = last;
        } else {
            last.next = null;
        }
        return item;
    }

    private void checkNullItem(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot add a null item to the Deque");
    }

    private void checkRemoveFromEmptyDeque() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Deque is empty");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
