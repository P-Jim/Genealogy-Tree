/////////////////////////////////////////////////////////////////////////////
//
// Author1: (Jun Yu Ma,jma222@wisc.edu,jma222,Lec 002)
// Author2: (Sovankosal Ly ,sly5@wisc.edu, sly5,Lec 002)
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.EmptyStackException;

public class Stack<T> implements StackADT<T> {
	//Stack is used as the internal data structure for the class
	LinkedList<T> Stack;

	/**
	 * the constructor for a Stack
	 */
	public Stack() {
		Stack = new LinkedList<T>();
	}

	/** return true if this Stack is empty
	 *  @return whether the queue is empty
	 */
	public boolean isEmpty() {
		return Stack.size()==0;
	}

	/**
	 * Add the data item to top of the Stack.
	 * If data is null, throw IllegalArgumentException
	 * @param the data that is to be added
	 */
	public void push(T data) throws IllegalArgumentException {
		//if data is null, throws IllegalArgumentException
		if (data == null) {
			throw new IllegalArgumentException();
		}
		Stack.add(data);		
	}

	/**
	 * Returns the element from the top of Stack,
	 * without removing it from the stack.
	 *
	 * If the stack is empty, throws java.util.EmptyStackException
	 * @return the element at the top of the stack
	 */
	public T peek() throws EmptyStackException {
		//if Stack is empty, throw EmptyStackException
		if (Stack.size() == 0) {
			throw new EmptyStackException();
		}
		//Stack.size()-1 is the element at the top of the stack
		return Stack.get(Stack.size()-1);
	}

	/**
	 * Returns and removes the element from the top of Stack,
	 *
	 * If the stack is empty, throws java.util.EmptyStackException
	 * @return the element that was removed
	 */
	public T pop() throws EmptyStackException {
//		//if Stack is empty, throw EmptyStackException  //IF YOU UNCOMMENT THE IF STATEMENT, AND TRY 'f' and 'b' or 'c' 
//		if (Stack.size() == 0) {						//IT WILL CAUSE ERROR
//			throw new EmptyStackException();
//		}
		//"removed" store the removed element at the top of the stack 
		T removed = Stack.remove(Stack.size()-1);
		return removed;
	}

	/**
	 * Creates a new Stack with the items of this stack
	 * in the reverse order.
	 * The items in this stack remain the same order.
	 *
	 * If this stack is empty, the reverse order stack is also empty.
	 */
	public StackADT<T> reverse() {
		//create a new stack that will be the reverse of Stack
		Stack reverse = new Stack();
		//Add the elements at the top of Stack to reverse
		//first which puts them at the bottom of reverse
		for (int i = Stack.size()-1; i >=0; i--) {
			reverse.push(Stack.get(i));
		}
		//return the reverse stack
		return reverse;
	}

}
