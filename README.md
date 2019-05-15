
# Autocomplete program in Java  
  
## 1/ Introduction  
This program written in Java will display autocompleted results based on a user query input string.    
The program starts by **asking the user to enter a string** into the command prompt. It will then **look for the corresponding words** in the words referential, **sort the results** alphabetically, and **display the top 4 words** onto the command prompt.  
  
## 2/ Data structure and algorithm  
### 2-1/ Data structure  
The words from the words referential are stored into a **Tree**. The tree is made of **Tree Nodes**.    
Each tree node contains :   
- a character from a stored word  
- a list of children nodes, which will contain the following possible characters for each word from the words referential.  
- a Boolean indicating if the node is the last node of a word, e.g the list of children is empty and the character for this node is the last character of a word (this kind of node can be called "leaf").  
  
This program is partitioned into two object classes (Tree and TreeNode), a main class and a JUnit 5 test file.  
  
### 2-2/ Algorithm  
**Main**  
The main class first asks the user to enter an input query as string. Next, it instantiates a new empty Tree and loads it up with the words from the words referential. It can then use the Tree to obtain the list of autocompleted results corresponding to the user's query. Finally, it sorts the results alphabetically and displays the top 4 words to the user.  
  
**Tree**  
- *Adding a child to a node* : We loop through all children for the current node in order to check if a child node with the current character already exists. If not, we add the child to the children list.   
- *Traversing the tree to find words which start with the user's query* : Starting from the root node, we loop through each child for each character of the query. If we find a corresponding node, we recursively loop through children of this new node. Once we found out all the nodes for each character of the query, we can retrieve all the results in all children of the final node. If no nodes are found for any character of the query, no results are retrieved, meaning no words starting with the user's query exist in the words referential.  
  
**TreeNode**  
- *Add a child* : We just add a new child to the children list of the tree node. If the child represents the last character of a word, we set the dedicated Boolean to true.    
  
## 3/ Thoughts and limits of this approach  
I considered other approaches before I decided to use the Tree and TreeNodes :  
  
*a/ Naive approach* : Build a simple list of all words, and iterate the list of words in order to find the results words that start with the user's input.    
This approach is very slow and not scalable. The more words we have in the list, the slower the approach will be.    
  
*b/ Indexing using dictionaries / hash maps* : Just like the way we look for a word in a paper dictionary, we would store each word into a dictionary of dictionaries. The first dictionary would use the first character of a word as the key, and store other dictionaries for the other characters until we constitute a word. To find a word, we would compare the dictionaries' keys for each character of the word.    
This approach would instantiate a lot of dictionaries, and in my opinion it could take a lot of memory and could be hard to maintain (dictionaries manipulation, and lack of control of the structure of the dictionary).    
  
*c/ Tree of chained TreeNodes* : This is the approach I used for this program.   
- This approach is very scalable : if we have a lot of words, we just have to add nodes to the tree, reusing some nodes. For example, if we want to add the word **Board** and we already have the word **Boy** in the tree, we will reuse the nodes **B** and **O**, and add the nodes **A**, **R**, and **D** after the O in the tree.    
- This is easily maintainable, we have full control of the nodes and tree structures, as they are represented by specific Java classes. Also, this approach is fast to understand and to visually represent.  
- It uses a structure that looks like the chained list, which is a well-known structure, as each tree node is chained to its children.  

One limit of the algorithm I implemented is that when I traverse the tree to find the corresponding nodes of the user's query, I loop through all children each time, which is a linear search. This kind of search is still good in this case because the number of nodes each time cannot exceed 26.   

A solution would be to alphabetically order the children list for each node of the tree. Like that, it would be faster to find the nodes for each character of the query string, using a dichotomic search / binary search, instead of a linear search. This kind of search would be inevitable for trees that contain nodes that can have thousands of children each. 

**Note :** I will add very soon a version of this program using a Binary Search method. The performances comparison can be found below.  

## 4/ Performances comparison between Binary Search method and Linear Search  

| Binary Search (Average, in nanoseconds) | Linear Search (Average, in nanoseconds) |
|-----------------------------------------|-----------------------------------------|
| 285,100                                 | 60,565,560                              |

As expected, the binary search method is way faster.  

## Author : Thibaut BREMAND  
- thibaut.bremand [at] gmail.com
- https://github.com/ThibautBremand
