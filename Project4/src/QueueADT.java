/**
 * A First In First Out data structure.
 *
 */
public interface QueueADT<T> {

	/**
	 * Return true if the Queue is empty
	 */
	boolean isEmpty();

	/**
	 * Add data to the rear of the Queue,
     *  throws IllegalArgumentException when data is null
     */
	void enqueue(T data) throws IllegalArgumentException;

	/**
	 * Return and remove the next item (from the front) of the queue,
	 *
     * If queue is empty, throw EmptyQueueException
     */
	T dequeue() throws EmptyQueueException;

	/**
	 * Get and do not remove the next item in the queue,
     *
     * If queue is empty, throw EmptyQueueException
     */
	T element() throws EmptyQueueException;

}
