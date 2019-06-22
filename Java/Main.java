import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    // KEYWORDS static variable containing the wanted keywords for autocompletion
    private static final List<String> KEYWORDS = Arrays.asList(
            "car",
            "card",
            "cart",
            "skate",
            "pro",
            "sun",
            "a&b",
            "project",
            "bike",
            "beach",
            "sunny day",
            "sunny month",
            "surf",
            "sailing",
            "swimming",
            "swing"
    );

    private static int MAX_RESULTS = 4;

    public static void main(String[] args) throws IOException {

        // 1 - Load the Tree with the keywords
        Tree tree = new Tree(new TreeNode('.', false));
        for (String elt : KEYWORDS) {
        	tree.addWord(elt);
        }

        // 2 - Read the input string from the user
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*** WELCOME TO OUR AUTOCOMPLETE PROGRAM ***");
        
        boolean quitProgram = false;
        while (quitProgram == false) {
        	printInstructions();
	        
	        String input = br.readLine().toLowerCase();
	        switch(input) {
	        case("1"):
	        	// Read the input and print the autocompleted results
	            handleChoiceFindWords(tree, br);
	        	break;
	        case("2"):
	        	// Read the input and add the word to the referential if it does not already exist
	        	handleChoiceAddWord(tree, br);
	        	break;
	        case("3"):
	        	// Quit the program
	        	System.out.println("Good bye !");
	        	quitProgram = true;
	        	break;
	    	default:
	    		System.out.println("Sorry I don't understand your choice... Please try again !");
	        }
        }
    }

	/**
     * This method will ask the user for an input and display the corresponding autocompleted words from the referential
     * @param tree : The referential tree containing all the words
     * @param br : The buffered reader used to ask the user enter an input string
     * @throws IOException
     */
	private static void handleChoiceFindWords(Tree tree, BufferedReader br) throws IOException {
    	System.out.println("Please enter a string :");
        String input = br.readLine().toLowerCase();

        // Clear the results list
        tree.setResults(new ArrayList<String>());

        // Load the results list
        tree.findNodesFromInputString(tree.getFirstNode(), "", input);

        // Display the top results on the console
        tree.displayTopResults(MAX_RESULTS);
	}
	
	/**
	 * This method will ask the user to enter a new word, and add this new word to the referential, if it does not already exist
	 * @param tree
	 * @param br
	 * @throws IOException
	 */
    private static void handleChoiceAddWord(Tree tree, BufferedReader br) throws IOException {
    	System.out.println("Please enter the word you want to add to the referential :");
    	String input = br.readLine().toLowerCase();
    	tree.addWord(input);
	}

	/**
	 * This method will print the instructions on the console
	 */
	public static void printInstructions() {
    	System.out.println("");
        System.out.println("What do you want to do ?");
        System.out.println("1 - Find words in the referential using an input string");
        System.out.println("2 - Add a new word to the referential");
        System.out.println("3 - Quit the program");
        System.out.println("*** Please enter the number corresponding to your choice ***");
        System.out.println("");
    }
}
