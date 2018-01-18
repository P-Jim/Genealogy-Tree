/////////////////////////////////////////////////////////////////////////////
//
// Author1: (Jun Yu Ma,jma222@wisc.edu,jma222,Lec 002)
// Author2: (Sovankosal Ly ,sly5@wisc.edu, sly5,Lec 002)
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;
import java.io.*;

/**
 * A general tree that is can be used to keep 
 * track of parent child relationships between data.
 * 
 * Nodes in a general tree can have multiple children.
 * 
 * This tree is built by reading lines from a file.
 * 
 */
public class GenealogyTree{

	public static final String LOAD_GENEALOGY_ERROR_MESSAGE = "Error loading genealogy from file";

	// The root node of the GenealogyTree
	private TreeNode<String> root;

	public GenealogyTree(){
		root = null;
	}

	// Get the root node of the GenealogyTree
	public TreeNode<String> getRoot(){
		return root;
	}

	/**
	 * return a Stack that contains the ancestors of the node with matching data
	 * The root is at the bottom of the stack and the matching node is at the top
	 * and the stack contains all ancestors of the matching data node.
	 *
	 * THIS METHOD CALLS A COMPANION (HELPER) METHOD that is recursive
	 * 
	 * If the top of the stack is not target,
	 * return an empty stack indicating that the target
	 * was not found.
	 *
	 * @param target the data you are trying to find
	 * @return a stack with the target data node at top and the root at the bottom,
	 * or return an empty stack if the target was not found.
	 */
	public StackADT<String> getAncestorStack(String target) {
		StackADT<String> stack = new Stack<String>();
		stack = getAncestorStack(stack,root,target);
		if (stack.peek().equals(target)) {
			return stack;
		}
		return new Stack<String>(); // empty stack
	}

	/**
	 * Perform a pre-order traversal of the current node and 
	 * return a Stack that contains the ancestors of the target node.
	 * 
	 * The root is at the bottom of the stack and the matching node is at the top
	 * and the stack contains all and only ancestors of the matching data node.
	 * 
	 *
	 * @param target the data you are trying to find
	 * @return a stack with the target data node at top and the root at the bottom 
	 * or an empty stack if target is not found
	 */
	private StackADT<String> getAncestorStack(StackADT<String> st, TreeNode<String> curr, String target) {
		// if current node is not null
		if (curr !=null) {

			// push to stack
			st.push(curr.getData());
			// check for match
			// if curr matches target return stack	
			if (curr.getData()==target) {
			
				return st;
			}
			// otherwise, iterate through children
			else {
				// for each child
				for (int i=0; i<curr.getChildren().size(); i++){
					// get the ancestor stack for that child
					st = getAncestorStack(st, curr.getChildren().get(i), target);
					// if top of ancestry stack equals target
					if (st.peek().equals(target)) {
						// return stack
						return st;
					}
					// otherwise, pop the top from the stack
					else {
						st.pop();              
					}
				}
			}
		}
		// return stack if done processing child
		return st;
	}

	/**
	 * Load a tree from file.
	 *
	 * If there are IOException when loading the tree from file, 
	 * print LOAD_GENEALOGY_ERROR_MESSAGE 
	 * and throw the IOException.
	 *
	 *
	 * The relationships are listed in a pre-order traversal order starting from root.
	 *
	 * For example, for the following tree:
	 *     a
	 *  /  |  \
	 *  b  c  d
	 *  |     | \
	 *  e     f g
	 *
	 * The input file must follow this form:
	 * a -> b
	 * a -> c
	 * a -> d
	 * b -> e
	 * d -> f
	 * d -> g
	 *
	 * Note: all lines of a file must contain a relationship to be a valid format.
	 * Blank lines will cause exceptions.
	 * 
	 * 
	 */
	public void buildFromFile(String filename) throws IOException{		
		Queue<TreeNode> nodeQ = new Queue<TreeNode>();
		// Create a queue, add each new node to the queue
		// create a Scanner connect to the file
		try {
			File file = new File(filename);
			Scanner scnr = new Scanner(file).useDelimiter("-\\>");
			// for each line of the file
			while (scnr.hasNextLine()) {
				// read the line
				// parse the line into parent and child	
				String string = scnr.nextLine();
				//split the line by "->"
				String[] parts = string.split("-\\>");
				//pi = parts[0] is the primary investigator and remove extra 
				//space
				String pi = parts[0].trim();;
				//researcher = parts[1] is the researcher and remove extra 
				//space
				String researcher = parts[1].trim();

				// if root is null
				if (root == null) {
					// create the root										
					TreeNode<String> newRoot = new TreeNode<String>(pi);
					//set the root to newly created root
					root = newRoot;
					// add its first child
					TreeNode<String> child = new TreeNode<String>(researcher);
					root.addChild(child);
					// add the root and child to the queue
					nodeQ.enqueue(root);
					nodeQ.enqueue(child);
				}
				else {
					// else Construct other TreeNode
					// while queue is not empty
					while (!nodeQ.isEmpty()) {
						// get next node from queue without removing it from queue
						TreeNode getnode = nodeQ.element(); //getnode is the parent
						// if "front" node matches the parent
						if (getnode.getData().equals(pi)) {
							// create a TreeNode for the child
							TreeNode<String> newTreenode = new TreeNode<String>(researcher);

							// add the child node to the current "front" node (its parent)
							getnode.addChild(newTreenode);

							// add the child to the queue
							nodeQ.enqueue(newTreenode);

							// break out of the loop
							break;
						}
						// else dequeue the front node 
						else {
							nodeQ.dequeue();
						}

					}
				}
			}
			// catch IO exceptions, display error message and rethrow the exception
		}catch (IOException e) {System.out.println(LOAD_GENEALOGY_ERROR_MESSAGE);
		throw new IOException(); 
		}	
		// close the file scanner
		
	}           

	/**
	 * Display the contents of the tree in a horizontal tree form.
	 * 
	 * This method is a private recursive helper method for the
	 * printTree() method.
	 * 
	 * It uses the indentation levels to indicate how many 
	 * dots (two per each level) to print for the node
	 * 
	 * @param current node to print
	 * @param indent_count indicates how many dots .. to print for the current level
	 * @param indent_str indicates string of characters precede each print level
	 */
	private void printTreeWithIndent(TreeNode<String> current, int indent_count, String indent_str){
		//print out the root
		if (indent_count == 0 ){
			System.out.println(current.getData());
		}
		//return if the TreeNode does not have children
		if (current.getChildren() == null) {
			return;
		}
		//increase the indent_count each time we go to the children node
		else {
			if (current.getChildren()!= null){
				indent_count++;
			}
			//iterate over each children, traversing in pre-order
			for (int i=0; i<current.getChildren().size(); i++){
				//loop to print out indent_str ".." based on the level 
				//of the children
				for (int j=0; j< indent_count; j++){ //<=
					System.out.print(indent_str);
				}
				//print out the node in pre-order
				System.out.println(current.getChildren().get(i).getData());
				//recursive method to go to the node children
				printTreeWithIndent(current.getChildren().get(i), indent_count, indent_str);

			}

		}





	}

	/**
	 * Print a tree with indent.
	 *
	 * You should use pre-order to print a tree, which means:
	 * (1) Print the data at current node
	 * (2) For all children nodes of current node,
	 *       recursively use pre-order to print children nodes.
	 *
	 * Each line of output represents a node, use indent (number of spaces before node data)
	 * to indicate which level the current node belongs to.
	 * For root node (at level 0), use 0 spaces.
	 * For nodes at other levels, add 2 spaces of indent each level.
	 *
	 * Like for the following tree:
	 *     a
	 *  /  |  \
	 *  b  c  d
	 *  |     | \
	 *  e     f g
	 *
	 * The displayed output should be:
	 * <pre>
	 *  a
	 *  ..b
	 *  ....e
	 *  ..c
	 *  ..d
	 *  ....f
	 *  ....g
	 * </pre>
	 */
	public void printTree() {
		// call recursive helper method'

		printTreeWithIndent(root, 0, "..");
	}
//REMOVE?
//	public void printTree1(TreeNode<String> current, String target){
//		if (current.getData().equals(target)){
//			return;
//		}
//		for (int i=0; i<current.getChildren().size(); i++){
//
//			printTree1(current.getChildren().get(i), target);
//
//		}
//	}

}
