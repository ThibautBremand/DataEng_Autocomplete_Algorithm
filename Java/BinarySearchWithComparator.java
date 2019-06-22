import java.util.Collections;
import java.util.Comparator;

public class BinarySearchWithComparator {
	
	/**
	 * This comparator will compare TreeNodes together, based on their respective characters
	 */
    static Comparator<TreeNode> c = new Comparator<TreeNode>() {
        public int compare(TreeNode t1, TreeNode t2) {
          return String.valueOf(t1.getCharacter()).compareTo(String.valueOf(t2.getCharacter()));
        }
      };
      
      /**
       * This method will look for a node with a specific character in a list of nodes
       * @param mother : The mother node containing the list of nodes to scan
       * @param child : The child node to be found
       * @return : the position of the found node
       */
      public static int binarySearchTreeNode(TreeNode mother, TreeNode child) {
      	return Collections.binarySearch(mother.getChildren(), child, c);
      }
    
    /**
     * This method will add the child node into the mother children list, alphabetically sorted, if it does not already exist
     * NOTE : This method is no longer used in the project but is still available here if needed
     * @param mother : the mother node which will welcome the child node
     * @param child : the child node to be added
     */
    public static void insert(TreeNode mother, TreeNode child) {
        int pos = Collections.binarySearch(mother.getChildren(), child, c);
        
        // pos < 0 means that the element does not exist in the children list
        if (pos < 0) {
        	mother.getChildren().add(-pos-1, child);
        	
        }
    }
    
}
