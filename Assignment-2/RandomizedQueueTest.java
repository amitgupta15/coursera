import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by amit on 9/14/17.
 */
public class RandomizedQueueTest {

    @Test
    public void isEmptyTest() {

        RandomizedQueue q = new RandomizedQueue();
        assertTrue(q.isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void enqueueNullItemTest() {

        RandomizedQueue<Integer> q = new RandomizedQueue();
        q.enqueue(null);
    }

    @Test
    public void enqueueTest() {
        RandomizedQueue q = new RandomizedQueue();
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(2, q.size());
    }

    @Test (expected = NoSuchElementException.class)
    public void dequeueEmptyTest() {
        RandomizedQueue q = new RandomizedQueue();
        q.dequeue();
    }

    @Test
    public void dequeueRandomItemTest() {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
    }

    @Test (expected = NoSuchElementException.class)
    public void sampleEmptyQueueTest() {
        RandomizedQueue<Integer> q = new RandomizedQueue();
        int i = q.sample();
    }

    @Test
    public void sampleTest() {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        assertEquals(new Long(1), new Long(q.sample()));
        q.enqueue(2);
        q.enqueue(3);
        //System.out.println(q.sample());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void iteratorRemoveTest() {
        RandomizedQueue q = new RandomizedQueue();

        Iterator i = q.iterator();
        i.remove();
    }

    @Test
    public void iteratorHasNextTest() {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        Iterator i = q.iterator();
        assertTrue(i.hasNext());
        i.next();
        assertFalse(i.hasNext());
    }

    @Test
    public void iteratorTest() {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        Iterator x = q.iterator();
        Iterator y = q.iterator();

        while(x.hasNext()) {
            System.out.println(x.next());
        }
        System.out.println("======================================");
        while(y.hasNext()) {
            System.out.println(y.next());
        }

    }
}
