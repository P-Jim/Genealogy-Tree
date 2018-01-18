/////////////////////////////////////////////////////////////////////////////
//
// Author1: (Sovankosal Ly ,sly5@wisc.edu, sly5,Lec 002)
// Author2: (Jun Yu Ma,jma222@wisc.edu,jma222,Lec 002)
//
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * An Iterable list that is implemented using a singly-linked chain of nodes
 * with a header node and without a tail reference.
 * 
 * The "header node" is a node without a data reference that will 
 * reference the first node with data once data has been added to list.
 * 
 * The iterator returned is a LinkedListIteratorx constructed by passing 
 * the first node with data.
 * 
 * CAUTION: the chain of nodes in this class can be changed without
 * calling the add and remove methods in this class.  So, the size()
 * method must be implemented by counting nodes.  This counting must
 * occur each time the size method is called.
 * 
 */
public class LinkedList<E> implements ListADT<E> {
	
	/** 
	 * Returns a reference to the header node for this linked list.
	 * The header node is the first node in the chain and it does not 
	 * contain a data reference.  It does contain a reference to the 
	 * first node with data (next node in the chain). That node will exist 
	 * and contain a data reference if any data has been added to the list.
	 * 
	 * NOTE: Typically, a LinkedList would not provide direct access
	 * to the headerNode in this way to classes that use the linked list.  
	 * We are providing direct access to support testing and to 
	 * allow multiple nodes to be moved as a chain.
	 * 
	 * @return a reference to the header node of this list. 0
	 */
	private Listnode<E> header;
	public int size;

	/**
	 * the constructor for a linked list
	 */
	public LinkedList() {
		header = null;
		size = 0;
	}

	/**
	 * Returns the header node for the linked list
	 */
	public Listnode<E> getHeaderNode() {
		return header;
	}

	/**
	 * adds item to the end of the linked list
	 */
	public void add(E item) { 
		//if the head is null make the header into
		//the item
		if (header == null) {
			header = new Listnode(item);
		}
		//listnode temp will be added to the linked list
		Listnode temp = new Listnode(item);
		//current marks the current location of the pointer
		//through the linked list
		Listnode current = header;
		//if current is not null
		if (current != null) {
			//while the next node after current is not null
			while (current.getNext() != null) {
				//iterate through the linked list
				current = current.getNext();
			}
			//once it reaches the end of the list set the next value 
			//to temp
			current.setNext(temp);
		}
		//keeping track of size this way for is empties 
		//use, getting size will not return int size
		//so I am still following program specifications
		size++;
	}

	/**
	 * This method adds object item to pos in the Linked List
	 * @param pos the position where item will be added to the Linked List
	 * @param item the object that is added to the Liked List
	 */
	public void add(int pos, E item) {
		//temp will be inserted into the linked list
		Listnode<E> temp = new Listnode(item);
		//current location
		Listnode<E> current = header;
		//while current is not null
		if(current != null) {
			//iterate through the linked list until it reaches the end or 
			//the node before node pos
			for (int i=0; i < pos && current.getNext() != null; i++) {
				current = current.getNext();
			}
		}
		//insert temp into pos of the linked list and scoot the 
		//object originally at pos and behind it backwards
		temp.setNext(current.getNext());
		current.setNext(temp);
		//keeping track of size this way for another methods
		//use, getting size will not return int size
		//so I am still following program specifications
		size++;
	}

	/**
	 * This method find if the linked list contains
	 * object item
	 *
	 * @param item The object that you are trying 
	 * @return true if the Linked List contains item, otherwise false
	 */
	public boolean contains(E item) {
		Listnode<E> current = header.getNext();
		for (int i=0; i < size; i++) {
			if (!current.getNext().getData().equals(item)) {
				return true;
			}
			current= current.getNext();
		}
		return false;
	}

	/**
	 * This method iterates through all the trains in the list
	 * and gets the object at pos
	 *
	 * @param pos The position of the object you are trying to get
	 * @return the object that was located at pos
	 */
	public E get(int pos) {
		//if pos is less than zero return null
		if (pos<0) {
			return null;
		}
		Listnode<E> current = null;
		if (header != null) {
			current = header.getNext();
			//iterate through the linked list until right before pos
			for (int i=0; i<pos; i++) {
				//if next is null while iterating there was an error
				//so return null
				if (current.getNext()==null) {
					return null;
				}
				//gets to pos
				current = current.getNext();
			}
			//return the data at pos
			return current.getData();
		}
		return current.getData();
	}

	/**
	 * This method checks if the linked list is empty
	 * 
	 * @return true if it is empty othrwise returns false
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * This method iterates through all the trains in the list
	 * and removed the object at pos
	 *
	 * @param pos The position of the object you are trying to remove
	 * @return The removed object
	 */
	public E remove(int pos) {
		//the node that will be removed
		E removed = null;
		//if the pos is less than  or greater than size than it 
		//is invalid
		if (pos<0 || pos > size) {
			return null;
		}
		//current location in the linked list
		Listnode<E> current = header;
		if (header != null) {
			//iterate until it reaches the position before pos
			for (int i = 0; i<pos; i++) {
				//set the next value is null then 
				//it there is nothing at pos so don't do anything
				if (current.getNext() == null) {
					break;
				}
				current = current.getNext();
			}
			//set the next node (the node that we want to remove) into
			//the node after that (skipping the previous last node)
			removed = current.getNext().getData();
			current.setNext(current.getNext().getNext());
			//decrement the size integer
			//which will not be used for the size() method
			size--;
		}
		//return the node that was removed
		return removed;
	}

	/**
	 * This method retrieves the size of the linked list
	 * 
	 * @return the size of the linked list
	 */
	public int size() {
		//NumCount records the size
		int NumCount = 0;
		//current is the current location in the linked list
		Listnode current = header;
		try {
			//while the next node is not null increment 
			//NumCount and move the point/current to the next node
			while (current.getNext() != null) {
				NumCount++;
				current = current.getNext();
			}
		}
		catch(NullPointerException g) {
			return NumCount;
		}		
		return NumCount;
	}
}
