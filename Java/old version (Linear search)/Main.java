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

        // Loop through all keywords
        for (String elt : KEYWORDS) {
            TreeNode currentNode = tree.getFirstNode();
            // Loop through all chars in the current keyword
            for (int i = 0; i < elt.length(); ++i) {

                // If the current char is not already in the tree as a child of the current node, we add it
                currentNode = tree.addNode(currentNode, elt.charAt(i), i == elt.length() - 1);
            }
        }

        // 2 - Read the input string from the user
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*** WELCOME TO OUR AUTOCOMPLETE PROGRAM ***");
        System.out.println("Please enter a string :");
        String input = br.readLine().toLowerCase();

        // 3 - Print the autocompleted results
        // Clear the results list
        tree.setResults(new ArrayList<String>());

        // Load the results list
        tree.findNodesFromInputString(tree.getFirstNode(), "", input);

        // Order the results list
        tree.orderResultsAlphabetically();

        // Display the top results on the console
        tree.displayTopResults(MAX_RESULTS);
    }
}
