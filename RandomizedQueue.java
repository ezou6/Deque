import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item>
        implements Iterable<Item> { // implements Iterable interface requiring
    // public Iterator(item) iterator() method
    private static final int INIT_CAPACITY = 10; // stores fixed initial capacity of array
    private Item[] elements; // stores array of items added to collection
    private int first = 0;
    // stores fixed index of first item (will always be 0 since we only swap with last)
    private int last = 0;// stores fixed index of first non-existent value
    // in array and is equal to number of items in queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        elements = (Item[]) new Object[INIT_CAPACITY];
        // must cast array of objects to an array of generic items
        // by default, initializes first and last to 0
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        // number of elements is 0
        return last == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return last;
    }

    // add the item by adding to back of array
    public void enqueue(Item item) { // takes Item parameter to be added

        // check if item possesses a value and exists
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // resize by factor of two if items fill length of entire array
        if (last == elements.length) {
            resize(2 * elements.length);
        }
        // assign new added item to previous null space and then increment last
        elements[last++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        // checks if queue is empty. If so, we can't remove items.
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is already empty. Can't remove any items.");
        }

        int randomIndex = StdRandom.uniformInt(0, last);

        // we want to swap the index where the randomly selected item exists with the last valid item in the queue
        // so that null values all populate at the end of the queue rather than in the middle
        Item item = elements[randomIndex];
        Item lastValidItem = elements[last - 1];
        elements[randomIndex] = lastValidItem;
        elements[last - 1]
                = null; // avoids loitering by assigning the index of the last valid item to null,
        // effectively getting rid of elements[randomIndex] item

        last--;

        // If number of items in the array reaches a fourth of its current capacity, resize down by a factor of 2
        if (last > 0 && last == elements.length / 4) {
            resize(elements.length / 2);
        }
        return item;

    }

    private void resize(int capacity) { // private helper method that appropriately resizes
        // the array if it reaches full capacity or drops to a fourth of full capacity
        // takes parameter capacity
        if (capacity
                >= last) { // check that capacity argument is greater or equal to current length of array
            Item[] copy = (Item[]) new Object[capacity]; // create new array and copy items into it
            for (int i = 0; i < last; i++) {
                copy[i] = elements[(first + i) % elements.length];
            }
            elements = copy;
        }
    }


    // return a random item (but do not remove it)
    // samples randomly without replacement
    public Item sample() {
        // can't sample an empty queue
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // generate random index and return item corresponding to index in queue
        int index = StdRandom.uniform(last);
        return elements[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }


    private class RandomizedIterator implements Iterator<Item> {
        // borrowed from reverse array iterator
        // implements Iterator interface with methods next() and hasNext()
        private int currentIndex = 0; // keeps track of current index being iterated over
        private Item[] temp = (Item[]) new Object[last]; // declares temproary copy of elements
        private int size = last; // number of elements in temproary copy temp

        public RandomizedIterator() {
            for (int x = 0; x < last; x++) { // copy over all items from elements into temp
                temp[x] = elements[x];
            }
        }

        public boolean hasNext() { // check if we iterated through all items in elements
            return currentIndex < last;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // like dequeue, we want to get rid of randomItems that have already been returned, so we swap
            // item at randomIndex with the last valid item at some index. Then we assign last index containing
            // a value to null.
            int randomIndex = StdRandom.uniformInt(0, size);
            Item item = temp[randomIndex];
            temp[randomIndex] = temp[size - 1];
            temp[size - 1] = null;
            currentIndex++;
            size--;
            return item;
        }

    }

    public static void main(String[] args) {

    }
}
