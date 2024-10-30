import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int numOfPermutations = Integer.parseInt(args[0]); // our k value specifying how many
        // items from the collection we want to print out to console
        RandomizedQueue<String> collectionOfStrings = new RandomizedQueue<String>();
        // we'll use a RandomQueue to store our Strings as its dequeue method automatically
        // removes but also returns a random item.

        // while we still have input fed into the console
        while (!StdIn.isEmpty()) {
            String textInput = StdIn.readString();
            // enqueue each individual string into the RandomizedQueue
            collectionOfStrings.enqueue(textInput);
        }

        // must return numOfPermutation randomized items by printing dequeue(method) returning the value
        // of a randomized item
        for (int i = 0; i < numOfPermutations; i++) {
            StdOut.println(collectionOfStrings.dequeue());
        }

    }
}
