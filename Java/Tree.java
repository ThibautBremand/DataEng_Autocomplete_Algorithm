import java.util.ArrayList;

/**
 * This class represents a Tree.
 * The tree is characterized by :
 * - a first node, or root node, which is the starting point of all words
 * - a list of autocompleted results, which will vary depending upon the user's query
 */
public class Tree {

    private TreeNode firstNode;
    private ArrayList<String> results;

    /**
     * Constructor
     * @param node : the root node
     * The results list is instantiated on the fly with a new empty list of strings
     */
    public Tree(TreeNode node) {
        this.firstNode = node;
        this.results =  new ArrayList<>();
    }
    
    /**
     * This method will add a new word to the tree
     * @param elt : The new word needed to be added
     */
	public void addWord(String elt) {
        TreeNode currentNode = this.getFirstNode();
        // Loop through all chars in the current keyword
        for (int i = 0; i < elt.length(); ++i) {

            // If the current char is not already in the tree as a child of the current node, we add it
            currentNode = this.addNode(currentNode, Character.toLowerCase(elt.charAt(i)), i == elt.length() - 1);
        }
	}

    /**
     * This function will add a new node to the tree, e.g it will add a child to the current node
     * @param node : the parent node which will welcome the new child
     * @param c : the character of the new node
     * @param endOfWord : indicates whether the new node is an end of a word or not
     * @return the new created node
     */
    public TreeNode addNode(TreeNode node, char c, boolean endOfWord) {
        // Check if the child node with the character c already exists for the current node, using a Binary Search
        int binarySearchPos = BinarySearchWithComparator.binarySearchTreeNode(node, new TreeNode(c, null));
        if (binarySearchPos >= 0) {
        	return node.getChildren().get(binarySearchPos);
        }

        // The child doesn't exist in the tree yet for the current node : Return the new created child node
        return node.addChildren(c, endOfWord, -binarySearchPos-1);
    }

    /**
     * This function will retrieve all the children of the current node, and store the resulting words into the results list
     * @param node : the node to retrieve the children from
     * @param word : the current word, made of each node's character
     */
    public void buildWordsFromAllChildren(TreeNode node, String word) {
        // Add the current character to the word string in order to build the current word as a string
        word = word + node.getCharacter();

        // Add the word into the results only when all the characters have been found
        if (node.getEndOfWord()) {
            this.results.add(word.substring(1)); // Remove the root character
        }
        // Keep looping through the children of the current node to get all the result words
        for (int i = 0; i < node.getChildren().size(); ++i) {
            // Recursive call
            buildWordsFromAllChildren(node.getChildren().get(i), word);
        }
    }

    /**
     * This function will look for the nodes corresponding to the user input, in order to obtain the autocompleted results
     * @param node : current node in the tree
     * @param word : the current word, made of each node's character
     * @param input : the user's input used for the autocompletion
     */
    public void findNodesFromInputString(TreeNode node, String word, String input) {
        // Check if the user's input string has been completely scanned
        if (input.equals("")) {
            // The input string has been completely scanned : we can retrieve all the children nodes and the corresponding results
            buildWordsFromAllChildren(node, word);
        } else {
            // Add the current character to the word string in order to build the current word as a string
            word = word + node.getCharacter();
            if (!node.getEndOfWord()) {
            	char currentChar = input.charAt(0);
            	
            	// Use binary search to find in the tree the associated child, if it exists
            	int binarySearchPos = BinarySearchWithComparator.binarySearchTreeNode(node, new TreeNode(currentChar, null));
            	if (binarySearchPos >= 0) {
                    // Recursive call
                    findNodesFromInputString(node.getChildren().get(binarySearchPos), word, input.substring(1));
                }
            }
        }
    }

/**************************************************************
 * Function used to handle the results for the user's input string
 **************************************************************/

    /**
     * This function will display on the console the top current results
     * @param topNb : the maximum number of results to display
     */
    public void displayTopResults(int topNb) {
        if (topNb > this.results.size()) {
            topNb = this.results.size();
        }
        for (int i = 0; i < topNb; ++i) {
            System.out.println(this.results.get(i));
        }
    }

/**************************************************************
 * Getters and setters
 **************************************************************/
    public TreeNode getFirstNode() {
        return firstNode;
    }

    public void setResults(ArrayList results) {
        this.results = results;
    }

    public ArrayList<String> getResults() {
        return results;
    }
}
