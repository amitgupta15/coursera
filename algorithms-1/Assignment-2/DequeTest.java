import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by amit on 9/12/17.
 */
public class DequeTest {

    @Test
    public void isEmptyTest() {
        Deque<String> d = new Deque<>();
        assertTrue("Deque is not empty", d.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFirstNullItemTest() {
        Deque<String> d = new Deque<>();
        d.addFirst(null);
    }

    @Test
    public void addFirstTest() {
        Deque<String> d = new Deque<>();

        d.addFirst("one");
        assertEquals(1, d.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addLastNullItemTest() {
        Deque<String> d = new Deque<>();
        d.addLast(null);
    }

    @Test
    public void addLastTest() {
        Deque<Integer> d = new Deque<>();

        d.addLast(1);
        d.addLast(2);
        assertEquals(2, d.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstEmptyDequeTest() {
        Deque<String> d = new Deque<>();
        d.removeFirst();
    }

    @Test
    public void removeFirstTest() {
        Deque<String> d = new Deque<>();

        d.addFirst("one");
        d.removeFirst();
        assertTrue("Deque is not empty", d.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastEmptyDequeTest() {
        Deque<String> d = new Deque<>();
        d.removeLast();
    }

    @Test
    public void removeLastTest() {
        Deque<Integer> d = new Deque<>();

        d.addFirst(1);
        d.removeLast();
        assertTrue("Deque is not empty", d.isEmpty());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void removeFromIteratorTest() {
        Deque<Integer> d = new Deque<>();

        Iterator<Integer> i = d.iterator();

        i.remove();
    }

    @Test
    public void iteratorTest() {
        Deque<Integer> d = new Deque<>();

        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(6);
        Iterator<Integer> i = d.iterator();

        assertTrue("Iterator failed", i.hasNext());
        assertEquals(new Long(i.next()), new Long(6));
        assertEquals(new Long(i.next()), new Long(3));
        assertEquals(new Long(i.next()), new Long(2));
        assertEquals(new Long(i.next()), new Long(1));
        assertEquals(new Long(i.next()), new Long(4));


    }

    @Test
    public void addFirstRemoveLastTest() {
        Deque<Integer> d = new Deque<>();

        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        d.addFirst(4);
        d.addFirst(5);

        assertEquals(new Long(1), new Long(d.removeLast()));
        assertEquals(new Long(2), new Long(d.removeLast()));
        assertEquals(new Long(3), new Long(d.removeLast()));
        assertEquals(new Long(4), new Long(d.removeLast()));
        assertEquals(new Long(5), new Long(d.removeLast()));
    }

}

