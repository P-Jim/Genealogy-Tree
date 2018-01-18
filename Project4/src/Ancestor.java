/////////////////////////////////////////////////////////////////////////////
//
// Author1: (Jun Yu Ma,jma222@wisc.edu,jma222,Lec 002)
// Author2: (Sovankosal Ly ,sly5@wisc.edu, sly5,Lec 002)
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;

/**
 * The main program (class) for determining the 
 * closest (lowest) common ancestor for a pair of researchers.
 * 
 * A file with the parent-&gt;child (professor-&gt;student) relationship
 * is read and used to build a GenealogyTree of the relationships.
 * 
 * That tree is then used to find the closest common ancestor.
 * 
 * 
 */
public class Ancestor{

	// Use this STDIN to read input from command line.
	// Don't create new Scanner.
	private static final Scanner STDIN = new Scanner(System.in);

	private static final String MAIN_LOOP_MESSAGE = "(c)heck, (p)rint, (q)uit";
	private static final String UNRECOGNIZED_COMMAND_ERROR_MESSAGE = "Unrecognized command";
	private static final String UNABLE_TO_INITIALIZE = "Unable to initialize Ancestor";
	private static final String INPUT_1_PROMPT = "Please input name 1";
	private static final String INPUT_2_PROMPT = "Please input name 2";
	private static final String NAME_NOT_FOUND_MESSAGE = "Can not find name: ";
	private static final String PROGRAM_USAGE_MESSAGE = "Usage: \njava Ancestor ancestors_data.txt";

	private GenealogyTree g;
	//"array" will store the result of name that we are trying to find in the tree
	String array[] = new String[1];

	public Ancestor() {
		g = new GenealogyTree();
	}


	/**
	 * Given two names, return the lowest common ancestor 
	 * as found in the GenealogyTree.
	 *
	 *<pre>
	 * (1) If name does not exist in GenealogyTree, 
	 *     print NAME_NOT_FOUND_MESSAGE
	 *     and the corresponding name, like "Can not find name: leonhard_euler"
	 *
	 * (2) If both names are not found in GenealogyTree, 
	 *     do step (1) for both name1 and name2.
	 *
	 * (3) If common ancestor does not exist, return null.
	 *</pre>
	 *
	 * @param name1 of first researcher to find
	 * @param name2 of second researcher to find
	 * @return the name of the closest (lowest level) common ancestor researcher
	 */
	public String lowestCommonAncestor(String name1, String name2){
		//"flag" store the result of findMatch.
		//if findMatch find the name that we are looking for, it will return
		//that name, else it will return null
		
		String flag = findMatch(g.getRoot(), name1);
		
		//if the name does was not found, print the NAME_NOT_FOUND_MESSAGE
		if (flag == null){
			System.out.println(NAME_NOT_FOUND_MESSAGE + name1);
		}
		//if the name is found, set the first element of the array to null
		else {
			array[0]=null;
		}
		
		//"flag1" store the result of findMatch.
		//if findMatch find the name that we are looking for, it will return
		//that name, else it will return null
		String flag1 = findMatch(g.getRoot(), name2);
		
		//if the name does was not found, print the NAME_NOT_FOUND_MESSAGE
		if (flag1 == null){
			System.out.println(NAME_NOT_FOUND_MESSAGE + name2);
		}
		//if the name is found, set the first element of the array to null
		else {
			array[0]= null;
		}
		// Get the ancestors of each name
		StackADT<String> ancestor = g.getAncestorStack(name1);
		StackADT<String> ancestor1 = g.getAncestorStack(name2);
		if (ancestor.isEmpty() == true || ancestor1.isEmpty() == true){
			
			// return null if either stack is empty
			return null;
		}
		// display not found messages if appropriate
		else{
			// if neither stack is empty
			// reverse both stacks so that the ancestors can be compared
			ancestor = ancestor.reverse();
			ancestor1 = ancestor1.reverse();
		}
		//"lowest" store the lowest ancestor of both name1 and name2
		String lowest = null;
		
		//while the ancestor is not empty
		//keep popping the stack ancestor and ancestor1
		//the lowest ancestor will be the last 2 variables that
		//matches 
		while (!ancestor.isEmpty()){
			
			//"pop" and "pop1" store the element that was pop from the stack
			String pop = ancestor.pop();
			String pop1 = ancestor1.pop();
			//compare pop and pop1, if both are the same, lowest = pop
			if (pop.equals(pop1)){
				lowest = pop;
			}
		}
		// Return the lowest level name in the tree
		// that is an ancestor of both of the specified 
		// researcher names.
		return lowest;
		
	}

	/**
	 * This method determines if the target is present in the tree
	 * 
	 * @param current node to check
	 * @param the target string to locate in the tree
	 * @return target iff if the target string was found in one of the nodes of the array
	 * otherwise return null
	 */
	public String findMatch(TreeNode<String> current, String target){
		//if the data of the current node equals the target string
		if (current.getData().equals(target)) {
			//set the first index of array to the target string
			//and then return the target string
			array[0]=target;
			return array[0];
		}
		//recursive method, this will iterate through the tree in pre-order
		//checking if the data of the node equals the target each time
		for (int i=0; i<current.getChildren().size(); i++){
			findMatch(current.getChildren().get(i), target);
		}
		//return the first index of the array
		return array[0];
	}


	/** 
	 * Handles the main menu loop's check operation.
	 */
	private void handleCheck(){
		System.out.println(INPUT_1_PROMPT);
		String name1 = STDIN.nextLine();

		System.out.println(INPUT_2_PROMPT);
		String name2 = STDIN.nextLine();

		String result = lowestCommonAncestor(name1, name2);

		if(result != null){
			System.out.println(String.format("Lowest common ancester is %s", result));
		}
	}

	/**
	 * The main menu loop
	 */
	private void mainLoop(){
		String command = "";
		while(!command.equalsIgnoreCase("q")){
			System.out.println(MAIN_LOOP_MESSAGE);
			command = STDIN.nextLine().trim();
			switch(command){
			case "c": handleCheck(); break;
			case "p": g.printTree(); break;
			case "q": break;
			default:
				System.out.println(UNRECOGNIZED_COMMAND_ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Initialize the GenealogyTree with data
	 * from the specified file.
	 * 
	 * @param filename is the name of a file with (professor-&gt;student) research pairs
	 * @return true iff if the file was read successfully
	 */
	public boolean initialize(String filename){
		try {
			g.buildFromFile(filename);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/** 
	 * THE MAIN METHOD THAT STARTS THE APPLICATION 
	 * @param args Command Line arguments used for file name of genealogy data
	 */
	public static void main(String[] args) {
		Ancestor a = new Ancestor();
		try {
			if ( ! a.initialize(args[0]) ) {
				System.out.println(UNABLE_TO_INITIALIZE);
				return;
			}
			a.mainLoop();
		} catch( Exception e ) {
			System.out.println(PROGRAM_USAGE_MESSAGE);
		}
	}

}
