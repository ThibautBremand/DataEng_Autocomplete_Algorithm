import java.util.ArrayList;

/**
 * This class represents a Tree Node.
 * Each node is characterized by :
 * - a character (the current character of the word)
 * - a list of child nodes (the following chars of the word)
 * - a boolean indicating if the current node is the last node of a word, e.g a leaf.
 */
public class TreeNode {
    private char character;
    private Boolean endOfWord;
    private ArrayList<TreeNode> children;

    /**
     * Constructor
     * @param character : character of the node
     * @param endOfWord : boolean indicating if this node represents the end of a word
     */
    public TreeNode(char character, Boolean endOfWord) {
        this.character = character;
        this.endOfWord = endOfWord;
        this.children = new ArrayList<TreeNode>();
    }

    /**
     * This function retrieves the child of the current node, whose character equals the one given in parameter.
     * @param c : the character used to find the corresponding children
     * @return the child node if it exists, else null
     */
    public TreeNode getChildrenByChar(char c) {
        for (TreeNode child : this.children) {		// TODO : Binary search
            if (child.character == c) {
                return child;
            }
        }
        return null;
    }

    /**
     * This function add a new child to the current node
     * @param c : the character of the new child
     * @param endOfWord : the boolean indicating if the child if the end of a word
     * @return the new created child
     */
    public TreeNode addChildren(char c, boolean endOfWord, int pos) {
        TreeNode newChild = new TreeNode(c, endOfWord);
        this.getChildren().add(pos, newChild);
        return newChild;
    }

/**************************************************************
 * Getters and setters
 **************************************************************/

    public char getCharacter() {
        return character;
    }

    public Boolean getEndOfWord() {
        return endOfWord;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }
}

