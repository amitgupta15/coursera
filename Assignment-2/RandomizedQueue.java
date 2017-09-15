import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by amit on 9/14/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] rq;
    private int size;

    public RandomizedQueue() {
        rq = (Item[]) new Object[2];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot add null item to the RandomizedQueue");
        if (size == rq.length) {
            resize(rq.length * 2);
        }
        rq[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue is empty");
        int random = StdRandom.uniform(size);
        Item item = rq[random];
        rq[random] = rq[size - 1];
        rq[--size] = null;

        if (size > 0 && size == rq.length/4) {
            resize(rq.length/2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue is empty");
        int i = StdRandom.uniform(size);
        return rq[i];
    }

    private void resize(int capacity) {
        assert capacity > size;
        Item[] temp = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            temp[i] = rq[i];
        }
        rq = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private int i = size;
        private int[] randomNumbers;

        public RandomizedQueueIterator() {
            randomNumbers = new int[i];
            for (int j = 0; j < size; j++) {
                randomNumbers[j] = j;
            }
            StdRandom.shuffle(randomNumbers);
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("RandomizedQueue is empty");
            return (Item) rq[randomNumbers[--i]];
        }
    }
}
