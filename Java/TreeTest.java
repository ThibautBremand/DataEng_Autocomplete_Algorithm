import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for the Tree class. These tests mainly check the autocompleted results using several input strings.
 */
class TreeTest {

    /**
     * List of keywords used to feed the tree
     */
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

    @org.junit.jupiter.api.Test
    void test() {
        // Load a new tree with the keywords
        Tree tree = loadTree();

        // Autocomplete 1 : input = "s"
        tree = getAutocompResultsFromTree("s", tree);
        assertEquals(tree.getResults().get(0), "sailing");
        assertEquals(tree.getResults().get(1), "skate");
        assertEquals(tree.getResults().get(2), "sun");
        assertEquals(tree.getResults().get(3), "sunny day");

        // Autocomplete 2 : input = "ca"
        tree = getAutocompResultsFromTree("ca", tree);
        assertEquals(tree.getResults().get(0), "car");
        assertEquals(tree.getResults().get(1), "card");
        assertEquals(tree.getResults().get(2), "cart");
        assertEquals(tree.getResults().size(), 3);      // only 3 words found

        // Autocomplete 3 : input = irrelevant string : no results must be retrieved
        tree = getAutocompResultsFromTree("z", tree);
        assertEquals(tree.getResults().size(), 0);

        // Autocomplete 4 : input = empty string : all results must be retrieved
        tree = getAutocompResultsFromTree("", tree);
        assertEquals(tree.getResults().size(), KEYWORDS.size());

        // Autocomplete 5
        tree = getAutocompResultsFromTree("cars", tree);
        assertEquals(tree.getResults().size(), 0);
    }

    /**
     * This function will instanciate a new empty tree and load it with the keywords
     * @return tree : the new loaded tree
     */
    Tree loadTree() {
        // New empty tree
        Tree tree = new Tree(new TreeNode('.', false));

        // Load the keywords into the tree
        for (String elt : KEYWORDS) {
            // Start with the root node
            TreeNode currentNode = tree.getFirstNode();
            for (int i = 0; i < elt.length(); ++i) {
                // Add a new node if it doesn't already exist, for each character of the current keyword
                currentNode = tree.addNode(currentNode, elt.charAt(i), i == elt.length() - 1);
            }
        }

        return tree;
    }

    /**
     * This function will retrieve the autocompleted results, alphabetically sorted, from the given input string
     * @param input : the user's input string used for autocompletion
     * @param tree : the tree previously loaded with the keywords
     * @return tree : the tree with the alphabetically ordered results for the user's input string
     */
    Tree getAutocompResultsFromTree(String input, Tree tree) {
        // Clear the results list
        tree.setResults(new ArrayList<String>());

        // Load the results list
        tree.findNodesFromInputString(tree.getFirstNode(), "", input);

        return tree;
    }
}