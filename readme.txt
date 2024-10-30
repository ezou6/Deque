Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */

For the deque, I used a doubly linked list to assign a first and last node that
could easily have its references to the next and previous nodes changed to facilitate
the addition or removal of an item from either the back or front, fulfilling the constant run-time
required of a deque. If I used an array, I would've had to shift elements an index up if I wanted
to add a new first node or shift elements down an index if I wanted to remove the first element, which
would've taken a big theta runtime of n as I make a new array of copied values. To avoid the
problem of loitering, I made sure to assign next and previous references pointing to a non-existent
item as null, depending on whether we removed or added to the front or back.

For the randomizedQueue, I used a resizing array since the order of the elements was
immaterial for this type of collection. Thus, I would not have to worry about shifting
elements an index up or down and would avoid creating a runtime of big theta(n) for dequeue
and queue operations. Instead, I would have a constant first index set at 0 and the last index
would keep track of the first nonexistent item in my array with a prestablished capacity of 10.
Whenever I wanted to remove a random item from the randomizedQueue using dequeue, I would just swap it out
with the item designated as an index before last (aka the last item that existed in the array with
a non-null value) and set the new last-1 as null to accomodate for loitering. I copied the resize(int capacity)
helper method from the textbook's API, which would appropriately resize my array by a factor of
two when last reaches the length of my array. The sample method() only returned a random item
from my collection with replacement. Finally, my RandomizedIterator similarly generated a random item and swapped it out
with the pre-existing last-1 to generate a randomized order.




/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */

It was fascinating to manually implement my own data structures by basing it off of pre-existing APIs such as the
Linkedlist and ResizingArray example from the textbook. I haven't worked extensively with data structures before
(other than binaryTree) so I enjoyed the challenge of determining which data structures would adhere to the
runtime requirements specified by RandomizedQueue and Deque. I like that I had to think about how to optimize
my implementation before coding. Since I placed out of COS 126, it was also nice to have a refresher on how
to use command-line arguments in the Permutation method.
