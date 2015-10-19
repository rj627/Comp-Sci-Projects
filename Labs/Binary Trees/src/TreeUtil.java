import java.util.*;

/*
 * TreeUtil contains the following static methods for manipulating binary trees: leftmost (which
 * returns the leftmost value in a tree), rightmost (which returns the rightmost value in a tree),
 * maxDepth (which returns the maximum depth of the tree - i.e. the maximum amount of nodes on
 * a continuous path from the top to the bottom of the tree), createRandom (which creates a random
 * tree of user-input depth), countNodes (which counts the number of nodes [values] in a tree), 
 * countLeaves (which counts the number of nodes <i>without children</i>), preOrder (which allows
 * for a preorder traversal of the tree), inOrder (which allows for an inorder traversal of the 
 * tree), postOrder (which allows for a postorder traversal of the tree), fillList (which adds
 * values of a tree to a List<>), saveTree (which saves a binary tree to a text file), buildTree
 * (which builds a tree based on values returned by an iterator), and loadTree (which loads a 
 * binary tree given a filename of a valid document in a particular location). 
 * @author Rahul Jayaraman (and Mr. Richard Page for getUserInput() and debugPrint())
 * @version 020614
 *
 */
public class TreeUtil
{
	//used to prompt for command line input
	private static Scanner in = new Scanner(System.in);
	
	private static final boolean debug = false;

	/**
	 * This method returns the leftmost value in a given binary tree with root node t. We
	 * assign a pointer to the t, and then shift it down and left each level (as long as 
	 * getLeft() does not return null) iteratively. Once left.getLeft() is null, we then
	 * return that value, as there is nothing to the left of that in the tree.
	 * @param t is the root node of the tree which we wish to examine
	 * @return the object stored as the leftmost value in the binary tree
	 */
	public static Object leftmost(TreeNode t)
	{
		TreeNode left = t; //pointer to the root that will keep shifting left
		
		while (left.getLeft() != null)
		{
			left = left.getLeft();
		} //close while loop
		
		//once the loop breaks (it's null), return the value of the left
		return left.getValue();
		
	} //close leftmost()

	/**
	 * This method returns the rightmost value in a given binary tree with root node t. We
	 * assign a pointer to the t, and then shift it down and right each level (as long as 
	 * getLeft() does not return null) recursively. Once getRight() of a node is null, we then
	 * return that value, as there is nothing to the right of that in the tree.
	 * @param t is the root node of the tree that we wish to examine for the rightmost value
	 * @return the value that is at the rightmost location in the tree
	 */
	public static Object rightmost(TreeNode t)
	{
		//base case, if nothing to the right, then return the value
		if (t.getRight() == null)
		{
			return t.getValue();
		} //close if
		
		else
			return rightmost(t.getRight()); //shift down and right, check again
		
	} //close rightmost()

	/**
	 * This method returns the maximum depth of the tree. It uses recursion; the base case
	 * being when the root node is null. If it's not null, then it returns 1 + the maximum
	 * of the right depth and the left depth, as we must account for the root. 
	 * @param t is the tree whose maximum depth we want to ascertain
	 * @return the maximum depth of the tree (how many nodes are in the greatest consecutive
	 * sequence down any possible path, as long as it is not null)
	 */
	public static int maxDepth(TreeNode t)
	{
		if (t == null) return 0; //base case
		
		//account for the root
		else return 1 + Math.max(maxDepth(t.getRight()), maxDepth(t.getLeft()));
		
	} //close maxDepth()

	/**
	 * create a random tree of the specified depth.  No attempt to balance the tree
	 * is provided.
	 * @param depth of the tree
	 * @return TreeNode object that points to the generated tree
	 */
	public static TreeNode createRandom(int depth)
	{
		if (Math.random() * Math.pow(2, depth) < 1)
			return null;
		return new TreeNode(((int)(Math.random() * 10)),
			createRandom(depth - 1),
			createRandom(depth - 1));
	} //close createRandom()
	
	/**
	 * This method returns the number of total nodes in a tree. If the root node is null, 
	 * then it returns 0; else, it returns 1 + the total nodes in the tree that is t.getRight() +
	 * the total nodes in the tree that is t.getLeft(). The 1 is to account for the root node.
	 * @param t is the tree whose nodes we want to count
	 * @return the amount of nodes (branching points) in the input tree
	 */
	public static int countNodes(TreeNode t)
	{
		if (t == null)
			return 0; //no nodes in a null tree - base case
		
		//account for root
		return 1 + countNodes(t.getRight()) + countNodes(t.getLeft());
		
	} //close countNodes()
	
	/**
	 * This method counts the number of leaves in a binary tree. A leaf is defined as a node
	 * that has neither a right sub-tree nor a left sub-tree. A leaf cannot be null; however,
	 * its getRight() and getLeft() methods MUST return null. If they do, it will return 1, else
	 * it will return the sum of the leaves in getLeft() and getRight(). 
	 * @param t is the tree whose leaves we want to count
	 * @return the number of leaves in the tree
	 */
	public static int countLeaves(TreeNode t)
	{
		if (t == null) return 0; //no leaves in a null tree - base case
		
		//nothing below it
		if (t.getRight() == null && t.getLeft() == null) return 1;
		
		//no need to account for the root node
		else return countLeaves(t.getLeft()) + countLeaves(t.getRight());
		
	} //close countLeaves()

	/**
	 * This method illustrates a preorder traversal through an input binary tree on a
	 * given display. A preorder traversal is done by visiting the node, then resolving
	 * the left and then the right (in that order). If the node is null, nothing is done.
	 * @param t is the tree which we wish to traverse with preorder
	 * @param display is the display on which the traversal will show
	 */
	public static void preOrder(TreeNode t, TreeDisplay display)
	{
		//if it's null do nothing (base case)
		
		if (t != null)
		{
			display.visit(t); //illuminates the node
			preOrder(t.getLeft(), display);
			preOrder(t.getRight(), display);
		} //close if
		
	} //close preOrder()
    
	/**
	 * This method illustrates an inorder traversal through an input binary tree on a
	 * given display. An inorder traversal is done by resolving the left, visiting the node,
	 * and then resolving the right (in that order). If the node is null, nothing is done.
	 * @param t is the tree which we wish to traverse with inorder
	 * @param display is the display on which the traversal will show
	 */
	public static void inOrder(TreeNode t, TreeDisplay display)
	{
		//base case - t is null, do nothing
		
		if (t != null)
		{
			inOrder(t.getLeft(), display);
			display.visit(t); //light up the node
			inOrder(t.getRight(), display);
		} //close if
	
	} //close inOrder()
    
	/**
	 * This method illustrates a postorder traversal through an input binary tree on a
	 * given display. A postorder traversal is done by resolving the left, resolving the
	 * right, and then visiting the node (in that order). If the node is null, nothing is done.
	 * @param t is the tree which we wish to traverse with postorder
	 * @param display is the display on which the traversal will show
	 */
	public static void postOrder(TreeNode t, TreeDisplay display)
	{
		//if node is null do nothing - base case
		
		if (t != null)
		{
			postOrder(t.getLeft(), display);
			postOrder(t.getRight(), display);
			display.visit(t); //light up the node
		} //close if
		
	} //close postOrder()
	
	/**
	 * utility method that waits for a user to type text into Std Input and then press enter
	 * @return the string entered by the user
	 */
	private static String getUserInput()
	{
		return in.nextLine();
	} //close getUserInput()
	
	/**
	 * debug printout
	 * postcondition: out is printed to System.out
	 * @param out the string to send to System.out
	 */
	private static void debugPrint(String out)
	{
	    if(debug) System.out.println("debug: " + out);
	} //close debugPrint()
	
	/**
	 * This particular method puts a tree into string format and then adds it to a list passed
	 * as a parameter. If a particular node is null, then a "$" is added to the list to signify
	 * the lack of a node. For nodes that <i>do</i> have a value, it is converted to a string. 
	 * Then, a preorder traversal is followed - resolving the left and then the right - 
	 * recursively, using t.getLeft() and t.getRight(). 
	 * @param t is the root of the tree that we wish to put into the list
	 * @param list is the list into which we wish to add elements of the tree
	 */
	public static void fillList(TreeNode t, List<String> list)
	{
		if (t == null) list.add("$"); //base case, add a $ to mark null
		
		else
		{
			//calling toString() is important because it's a list of Strings; we could declare a 
			//generic list but then we would run into issues with type erasure
			
			list.add(t.getValue().toString()); 
			
			//resolve left and right
			fillList(t.getLeft(), list);
			fillList(t.getRight(), list);
			
		} //close else
		
	} //close fillList()
	
	/**
	 * This method saves a particular tree to a file with a name that is passed as a parameter.
	 * It declares a new list, fills the list using the previous method, <code>fillList</code>,
	 * and then saves the file using the method in FileUtil (by calling an iterator on the 
	 * full list, as the FileUtil method takes an iterator as a parameter).
	 * @param fileName is the name of the desired file
	 * @param t is the tree that we want to save to the file
	 */
	public static void saveTree(String fileName, TreeNode t)
	{
		//the declared list must NOT BE AN INSTANCE OF AN INTERFACE; it must be 
		//an instance of a specific class that implements the List interface, such as
		//ArrayList or LinkedList
		
		List<String> tree = new LinkedList<String>();
		fillList(t, tree);
		FileUtil.saveFile(fileName, tree.iterator());
		
	} //close saveTree()
	
	/**
	 * This method builds a tree from an iterator passed as an argument and then builds a 
	 * binary tree from it; it starts by declaring a local variable to store it.next(), and
	 * if it's "$", then there are no child nodes; if it isn't, then the recursive nature
	 * of the data structure enables us to make a new TreeNode with the current value
	 * as the root and build a tree on either side using the iterator's next values. We expect
	 * that the iterator will be proper (and that it is the result of a preorder traversal). 
	 * @param it is the iterator that we use to build the tree
	 * @return the tree from the elements in the iterator
	 */
	public static TreeNode buildTree(Iterator<String> it)
	{
		String current = it.next(); 
		
		if (current.equals("$")) return null; //base case - $ indicates no child nodes
		
		//we've already called it.next(); this will call it again each time in buildTree()
		else return new TreeNode(current, buildTree(it), buildTree(it));
		
	} //close buildTree
	
	/**
	 * This loads a tree from a file with the name fileName. It merely returns whatever the
	 * buildTree method returns; however, a specific iterator - the return value of FileUtil's 
	 * loadFile method - is passed as the parameter to buildTree(). 
	 * @param fileName is the name of the file from which we want to load the tree
	 * @return a binary tree with elements from the file
	 */
	public static TreeNode loadTree(String fileName)
	{
		return buildTree(FileUtil.loadFile(fileName));
		
	} //close loadTree()
	
	/**
	 * This method copies a tree and returns a pointer to the duplicate tree. It does this by 
	 * creating a new TreeNode with the same value as the parameter; the left and right subtrees
	 * are recursive calls to copy with arguments getLeft() and getRight(). Base case - the tree
	 * is null, then we return null (e.g. t is null or we reach a leaf's getLeft()/getRight()). 
	 * @param t is the tree that we wish to copy
	 * @return an exact copy of t, with same shape AND all elements in the same place
	 */
	public static TreeNode copy(TreeNode t)
	{
		if (t == null) 
			return null; //base case
		
		return new TreeNode(t.getValue(), copy(t.getLeft()), copy(t.getRight())); //recursive call
		
	} //close copy()
	
	/**
	 * This method checks whether a tree has the same shape as another tree; i.e. if they were drawn
	 * on paper and then placed on top of each other, the bottom tree would not be visible. It does 
	 * this by testing if either t1 or t2 is null; then, it will return true if <i>both</i> are null
	 * or false if only one is null. If neither are null, then it will test if the left sub-trees and
	 * the right sub-trees are the same shape. 
	 * @param t1 is one of the trees we are testing for same shape
	 * @param t2 is the other tree we are comparing to t1 for same shape
	 * @return true if the two trees have the same shape, false otherwise
	 */
	public static boolean sameShape(TreeNode t1, TreeNode t2)
	{
		if (t1 == null || t2 == null) 
			return ((t1 == null) == (t2 == null)); //base case
		
		else //recursive call, testing getLeft() and getRight()
			return (sameShape(t1.getLeft(),t2.getLeft()) && sameShape(t1.getRight(),t2.getRight()));
		
	} //close sameShape()
	
	/**
	 * Generate a tree for decoding Morse code
	 * @param display the display that will show the decoding tree
	 * @return the decoding tree
	 */
	public static TreeNode createDecodingTree(TreeDisplay display)
	{
		TreeNode tree = new TreeNode("Morse Tree");
		display.displayTree(tree);
		insertMorse(tree, "a", ".-", display);
		insertMorse(tree, "b", "-...", display);
		insertMorse(tree, "c", "-.-.", display);
		insertMorse(tree, "d", "-..", display);
		insertMorse(tree, "e", ".", display);
		insertMorse(tree, "f", "..-.", display);
		insertMorse(tree, "g", "--.", display);
		insertMorse(tree, "h", "....", display);
		insertMorse(tree, "i", "..", display);
		insertMorse(tree, "j", ".---", display);
		insertMorse(tree, "k", "-.-", display);
		insertMorse(tree, "l", ".-..", display);
		insertMorse(tree, "m", "--", display);
		insertMorse(tree, "n", "-.", display);
		insertMorse(tree, "o", "---", display);
		insertMorse(tree, "p", ".--.", display);
		insertMorse(tree, "q", "--.-", display);
		insertMorse(tree, "r", ".-.", display);
		insertMorse(tree, "s", "...", display);
		insertMorse(tree, "t", "-", display);
		insertMorse(tree, "u", "..-", display);
		insertMorse(tree, "v", "...-", display);
		insertMorse(tree, "w", ".--", display);
		insertMorse(tree, "x", "-..-", display);
		insertMorse(tree, "y", "-.--", display);
		insertMorse(tree, "z", "--..", display);
		return tree;
	} //close createDecodingTree()
	
	/**
	 * This method, given a tree, inserts a character's Morse code representation into the tree. If the
	 * code has a . in it, then the left subtree is traversed; if the code has a - in it, then the right
	 * subtree is traversed. We then reassign the decodingTree pointer to either left or right, depending
	 * on whether it was a . or a - and then we light up the particular node on the display. Finally, once
	 * the code has "run out," we add the letter to that particular location on the tree.
	 * @param decodingTree is the tree which we want to arrange based on Morse Code
	 * @param letter is the letter that we want to encode
	 * @param code is the Morse code representation of the other param letter
	 * @param display is the display where we want to display the tree; lighting up each node
	 * @pre decodingTree cannot be null, nor can letter or code
	 * @post the decodingTree now has values in it corresponding to Morse code (. left, - right)
	 */
	private static void insertMorse(TreeNode decodingTree, 
			String letter, String code, TreeDisplay display) 
	{
		for (int i = 0; i < code.length(); i++) //traverse the entire length of String code
		{
			String dotOrDash = code.substring(i,i+1); //take each individual character
			
			//move down left, light up
			if (dotOrDash.equals("."))
			{
				if (decodingTree.getLeft() == null) decodingTree.setLeft(new TreeNode(null));
				decodingTree = decodingTree.getLeft();
				display.visit(decodingTree);
			} //close if
			
			//move down right, light up
			else if (dotOrDash.equals("-"))
			{
				if (decodingTree.getRight() == null) decodingTree.setRight(new TreeNode(null));
				decodingTree = decodingTree.getRight();
				display.visit(decodingTree);
			} //close else
		} //close for loop
		
		decodingTree.setValue(letter);
	} //close insertMorse()
	
	/**
	 * Given a string of Morse code symbols, this method will traverse a binary tree that represents
	 * Morse code and decode the Morse code into plain English. We must add a space to the end of the
	 * input cipher, as this will mark the end. We also declare an empty string to hold the English 
	 * translated message, and we copy the TreeNode, as we are manipulating it. We traverse the String
	 * cipherText and check if the character is ., -, or a space. If it's a dot, we move left and 
	 * light it up; if it's a dash, we move right and light it up; if it's a space, we then add the
	 * letter at the current value to message and go back to the top. We then return message.
	 * @param decodingTree is the tree that we use to decode Morse code messages
	 * @param cipherText is the Morse code message that we wish to see in English
	 * @param display is the display on which we will perform the traversal/light up nodes
	 * @return the message encoded by the parameter cipherText
	 * @pre cipherText is not empty; it has at least a space; decodingTree isn't null
	 */
	public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
	{
		//local variables - one to be returned, others to help the method run more efficiently
		String message = "";
		TreeNode copy = decodingTree;
		cipherText = cipherText + " ";
		
		for (int i = 0; i < cipherText.length(); i++) //go through all characters in cipherText
		{
			//go left, light up
			if (cipherText.substring(i,i+1).equals("."))
			{
				copy = copy.getLeft();
				display.visit(copy);
			} //close if
			
			//go right, light up
			else if (cipherText.substring(i,i+1).equals("-"))
			{
				copy = copy.getRight();
				display.visit(copy);
			} //close else if
			
			//done with that particular character, add the value to message, go back to top
			else if (cipherText.substring(i,i+1).equals(" "))
			{
				message = message + copy.getValue();
				copy = decodingTree;
				display.visit(copy);
			} //close else if
		} //close for loop
		
		return message;
	} //close decodeMorse()
	
	/**
	 * This method evaluates an expression tree; i.e. one with numbers and operands (in this case, only
	 * + and *). + and - are stored as Strings in the tree; values are stored as integers. A further 
	 * limitation is that + and * can NOT be in a leaf node. If the expression tree is not null, and
	 * the root is +, then we resolve the subtrees and add them. We do the same for if the root if *; 
	 * if expTree is a leaf node, we return its value.
	 * @param expTree is the tree with all the expressions
	 * @return the value obtained by evaluating those expression tree in an in-order fashion
	 * @pre expTree is not null; it must have left and right sub-trees
	 */
	public static int eval(TreeNode expTree)
	{
		if (expTree != null) 
		{	
			//sum and product
			if (expTree.getValue().equals("+")) return eval(expTree.getLeft()) + eval(expTree.getRight());
			else if (expTree.getValue().equals("*")) return eval(expTree.getLeft())*eval(expTree.getRight());
		} //close if
		
		return (int) expTree.getValue(); //casting to int from an Object (Integer, specifically)
	} //close eval()
	
} //close TreeUtil class
	