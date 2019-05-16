
# Autocomplete program in Java  
  
## 1/ Introduction  
This program written in Java will display autocompleted results based on a user query input string.    
In order to display these results, the program **asks the user to enter a string** into the command prompt. It will then **look for the corresponding words** in the words referential, and **display the top 4 words** onto the command prompt. Theses words will be **displayed alphabetically sorted as they are stored as such**.  
  
## 2/ Data structure and algorithm  
### 2-1/ Data structure  
The words from the words referential are stored into a **Tree**. The tree is made of **Tree Nodes**.    
Each tree node contains :   
- a character from a stored word  
- a list of children nodes, which will contain the following possible characters for each word from the words referential. These nodes are stored alphabetically sorted.    
- a Boolean indicating if the node is the last node of a word, e.g the list of children is empty and the character for this node is the last character of a word (this kind of node can be called "leaf").  
  
This program is partitioned into two object classes (Tree and TreeNode), a main class and a JUnit 5 test file. ALso, a specific static class, BinarySearchWithComparator, will perform the binary searches based on the BinarySearch class from Java.util.Collections.  
  
### 2-2/ Algorithm  
**Main**  
The main class first asks the user to enter an input query as string. Next, it instantiates a new empty Tree and loads it up with the words from the words referential. The nodes are stored alphabetically sorted. It can then use the Tree to obtain the list of autocompleted results corresponding to the user's query. Finally, it displays the top 4 words to the user.  
  
**Tree**  
- *Adding a child to a node* : We perform a binary search in all children of the current node in order to check if a child node with the current character already exists. If not, we add the child to the children list, alphabetically sorted.   
- *Traversing the tree to find words which start with the user's query* : Starting from the root node, we perform a binary search for each character of the query. If we find a corresponding node, we recursively loop. Once we found out all the nodes for each character of the query, we can retrieve all the results in all children of the final node. If no nodes are found for any character of the query, no results are retrieved, meaning no words starting with the user's query exist in the words referential.  
  
**TreeNode**  
- *Add a child* : We add a new child to the children list of the tree node, alphabetically sorted (using the same logic as the binary search). If the child represents the last character of a word, we set the dedicated Boolean to true.    
  
## 3/ Thoughts and limits of this approach  
Some other other approaches could have been possible :  
  
*a/ Naive approach* : Build a simple list of all words, and iterate the list of words in order to find the results words that start with the user's input.    
This approach is very slow and not scalable. The more words we have in the list, the slower the approach will be.    
  
*b/ Indexing using dictionaries / hash maps* : Just like the way we look for a word in a paper dictionary, we would store each word into a dictionary of dictionaries. The first dictionary would use the first character of a word as the key, and store other dictionaries for the other characters until we constitute a word. To find a word, we would compare the dictionaries' keys for each character of the word.    
This approach would instantiate a lot of dictionaries, and in my opinion it could take a lot of memory and could be hard to maintain (dictionaries manipulation, and lack of control of the structure of the dictionary). Also, Trees are better to work with ordered data.    
  
*c/ Tree of chained TreeNodes* : This is the approach I used for this program.   
- This approach is very scalable : if we have a lot of words, we just have to add nodes to the tree, reusing some nodes. For example, if we want to add the word **Board** and we already have the word **Boy** in the tree, we will reuse the nodes **B** and **O**, and add the nodes **A**, **R**, and **D** after the O in the tree.    
- This is easily maintainable, we have full control of the nodes and tree structures, as they are represented by specific Java classes. Also, this approach is fast to understand and to visually represent.  
- It uses a structure that looks like the chained list, which is a well-known structure, as each tree node is chained to its children.  

Two versions of the program are available in this repo : a version with a Linear Search O(N) -in the "old version" folder, and the main version with a Binary Search O(Log(N)). I've performed performances comparison between both programs, that you can find below.   

## 4/ Performances comparison between Binary Search method and Linear Search  

| Binary Search (Average, in nanoseconds) | Linear Search (Average, in nanoseconds) |
|-----------------------------------------|-----------------------------------------|
| 304,860                                 | 60,565,560                              |

As expected, the binary search method is way faster.  

## Author : Thibaut BREMAND  
- thibaut.bremand [at] gmail.com
- https://github.com/ThibautBremand
