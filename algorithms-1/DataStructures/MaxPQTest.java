import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxPQTest {

    @Test
    public void MaxPQTest() {
        MaxPQ<Integer> pq = new MaxPQ<>(8);

        pq.insert(1);
        pq.insert(2);
        pq.insert(3);
        pq.insert(4);
        pq.insert(5);
        pq.insert(6);
        pq.insert(7);
        pq.insert(8);

        assertEquals(new Integer(8), pq.delMax());
        assertEquals(new Integer(7), pq.delMax());

        pq.insert(20);
        assertEquals(new Integer(20), pq.delMax());


    }
}
