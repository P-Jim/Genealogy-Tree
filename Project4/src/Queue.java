/////////////////////////////////////////////////////////////////////////////
//
// Author1: (Jun Yu Ma,jma222@wisc.edu,jma222,Lec 002)
// Author2: (Sovankosal Ly ,sly5@wisc.edu, sly5,Lec 002)
//
//////////////////////////// 80 columns wide //////////////////////////////////
public class Queue<T> implements QueueADT<T> {
	//Queue is used as the internal data structure for the class
	LinkedList<T> Queue;

	/**
	 * the constructor for a Queue
	 */
	public Queue() {
		Queue = new LinkedList<T>();
	}

	/**
	 * Return true if the Queue is empty
	 * @return Whether the Queue is empty
	 */
	public boolean isEmpty() {
		return Queue.size()==0;
	}

	/**
	 * Add data to the rear of the Queue
	 * @param data that is to be added
	 * @throws IllegalArgumentException when data is null
	 */
	public void enqueue(T data) throws IllegalArgumentException {
		//if the data is null throw llegalArgumentException
		if (data == null) {
			throw new IllegalArgumentException();
		}
		Queue.add(data);		
	}

	/**
	 * Return and remove the next item (from the front) of the queue,
	 *
	 * If queue is empty, throw EmptyQueueException
	 * @return the item that is removed
	 */
	public T dequeue() throws EmptyQueueException {
		//If queue is empty, throw EmptyQueueException
		if (Queue.size() == 0) {
			throw new EmptyQueueException();
		}
		//"removed" store the item that was remove
		T removed = Queue.remove(0);
		return removed;
	}

	/**
	 * Get and do not remove the next item in the queue,
	 *
	 * @return The highest priority element in the queue
	 * @throws EmptyQueueException
	 */
	public T element() throws EmptyQueueException{
		//If queue is empty, throw EmptyQueueException
		if (Queue.size()==0) {
			throw new EmptyQueueException();
		}
		// Queue.get(0) is the highest priority element in the queue
			return Queue.get(0);
		}

	}

