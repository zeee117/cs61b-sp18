package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
    /* return size of the buffer */
    int capacity();
    /* return number of items currently in the buffer */
    int fillCount();
    /* add item x to the end */
    void enqueue(T x);
    /* delete and return item in the front of the buffer */
    T dequeue();
    /* return item from the front but not delete it */
    T peek();

    @Override
    public Iterator<T> iterator();

    default boolean isEmpty(){
        return fillCount() == 0;
    }

    default boolean isFull(){
        return fillCount() == capacity();
    }
}
