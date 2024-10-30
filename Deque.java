import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first; // first Node (least recently added)
    private Node last; // last Node (most recently added)
    private int numOfItems; // number of existing items in my linked list

    // private constructor for Node object
    private class Node {
        Item item; // value of item
        Node next; // next Node connected to current Node
        Node previous; // previous Node connected to current Node
    }


    // construct an empty deque with deque constructor (no parameters)
    public Deque() {
        first = null; // first and last node don't exist
        // when first creating empty Deque object
        last = null;
        numOfItems = 0;
    }

    // is the deque empty?
    public boolean isEmpty() { // determines if count of items is empty in deque
        return numOfItems == 0;
    }


    // return the number of items on the deque
    public int size() {
        return numOfItems;
    }


    // add the item to the front (least recently added)
    // Takes Item item, which will be added, as parameter.
    public void addFirst(Item item) {
        // check if item is a valid object (can't be null)
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // new first will be added to the front and now
        // connects to the old first node.
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        // haven't updated numOfItems just yet but assuming that
        // we begin with an empty deque, we want to assign the last and first node as
        // possessing the same value
        if (isEmpty()) {
            last = first;
        }
        else {
            // Making sure that first Node doesn't refer to a null object
            // that simply doesn't exist, else NullPointerException arises in an empty list.
            oldFirst.previous = first;
        }

        numOfItems++;

    }

    // add the item to the back //most recently added
    public void addLast(Item item) {
        // check if item is valid (can't be null)
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // new last will be added to the back and now
        // connects to the old last node.
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        // haven't updated numOfItems just yet but assuming that
        // we begin with an empty deque, we want to assign the last and first node as
        // possessing the same value
        if (isEmpty()) {
            first = last;

        }
        // Making sure that last Node doesn't refer to a null object
        // that simply doesn't exist, else NullPointerException arises in an empty list.
        else {
            oldLast.next = last;
        }
        numOfItems++;


    }

    // remove and return the item from the front
    public Item removeFirst() {
        // can't remove item from an empty list so throw exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next; // replace first with node next to it
        numOfItems--;
        if (isEmpty()) { // nothing remains in list, so last can't take on a value
            last = null;

        }
        else {
            first.previous = null; // avoid loitering by assigning old First to a nonexistent value
        }

        return item; // value of item removed

    }


    // remove and return the item from the back
    public Item removeLast() {
        // can't remove item from an empty list so throw exception
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item; // replace ;ast with node previous to it
        last = last.previous;

        numOfItems--;
        if (isEmpty()) {
            first = null; // nothing remains in list, so first can't take on a value
        }
        else {
            last.next = null; // avoid loitering by assigning old Last to a nonexistent value
        }
        return item; // value of item removed
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();

    }

    // private Iterator class implementing Iterator interface with methods hasNext() and next()
    private class DequeIterator implements Iterator<Item> {
        private Node current = first; // assign current node to the value of the first to begin
        // iterating at front/start of LinkedList


        public boolean hasNext() { // should this be privated
            return current != null;
            // check if existing items are still in Linkedlist and if we can continue to iterate through list
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item; // saving item that was just iterated over
            current = current.next; // current is now assigned to the next Node in list
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        // tests if addFirst works correctly. If iterating through list, should print
        // elements 10 to 1 in that order
        Deque<Integer> test = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            test.addFirst(i + 1);
        }
        // tests if removeLast works correctly
        for (int i = 0; i < 10; i++) { // print 1 - 10
            System.out.println(test.removeLast());
        }

        // tests if addFirst works correctly. If iterating through list, should print
        // elements 1 to 10 in that order
        Deque<Integer> test1 = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            test1.addLast(i + 1);
        }
        // tests if removeFirst works correctly
        for (int i = 0; i < 10; i++) { // print 1 - 10
            System.out.println(test1.removeFirst());
        }

        Deque<Integer> test2 = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            test2.addFirst(i + 1);
        }
        // prints 10 to 1 in that order
        for (int i : test2) {
            System.out.println(i);
        }

        // testing Deque() Constructor
        System.out.println(test2.numOfItems == 10);
        System.out.println(test2.last.item == 1);

        for (int i = 0; i < 10; i++) { // remove 10 -> 1
            test1.removeFirst();
        }

        // testing size()
        System.out.println(test2.size() == 0);

        // testing isEmpty()
        System.out.println(test2.isEmpty());


    }

}
