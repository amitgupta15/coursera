import org.junit.Test;

import static org.junit.Assert.*;

public class LinkListQueueTest {

    @Test
    public void isEmptyTest() {

        LinkListQueue l = new LinkListQueue();

        assertTrue(l.isEmpty());
    }

    @Test
    public void enqueDequeTest() {
        LinkListQueue l = new LinkListQueue();

        String one = "one";
        String two = "two";
        String three = "three";

        l.enqueue(one);
        l.enqueue(two);
        l.enqueue(three);

        assertEquals("Did not dequeue the correct item", l.dequeue(), one);
        assertEquals("Did not dequeue the correct item", l.dequeue(), two);
        assertEquals("Did not dequeue the correct item", l.dequeue(), three);
    }
}
