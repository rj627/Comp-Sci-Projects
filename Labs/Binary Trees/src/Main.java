/*
 * Tester for the TreeUtil class. The main method is the only method in here, and it 
 * runs each method in the TreeUtil class to make sure it works.
 * @author Rahul Jayaraman
 * @version 020614
 */

public class Main 
{
	/**
	 * tests the methods found in TreeUtil using a randomly generated binary tree. Two displays
	 * are created - one for the original, one loaded from the file. Methods tested include 
	 * maxDepth, leftmost, rightmost, countLeaves, countNodes, preOrder/inOrder/postOrder 
	 * (commented out), saveTree, and loadTree. 
	 * @param args - legacy command-line
	 */
	public static void main(String[] args)
	{
		 //new trees and TreeDisplays
		TreeNode tree = TreeUtil.createRandom(6);
	    //TreeDisplay display = new TreeDisplay();
	     TreeDisplay display2 = new TreeDisplay();
	     //display.displayTree(tree);

	     //printing out all the return values of the methods to System.out
		System.out.println(TreeUtil.maxDepth(tree));
		System.out.println(TreeUtil.leftmost(tree));
		System.out.println(TreeUtil.rightmost(tree));
		System.out.println(TreeUtil.countLeaves(tree));
		System.out.println(TreeUtil.countNodes(tree));
		//TreeUtil.preOrder(tree, display);
		//TreeUtil.inOrder(tree, display);
		//TreeUtil.postOrder(tree, display);
		
		//saving the tree to a text file and then reloading it
		/*
		TreeUtil.saveTree("doc", tree);
		TreeNode newTree = TreeUtil.loadTree("doc");
		display2.displayTree(newTree);
		*/
		
		
		TreeNode newTree = new TreeNode(1, new TreeNode(9, new TreeNode(7), new TreeNode(8, null, new TreeNode(6))), new TreeNode(6));
		TreeNode lolTree = new TreeNode(2, new TreeNode(5, new TreeNode(6), new TreeNode(7, null, new TreeNode(5))), new TreeNode(5));
		//display2.displayTree(newTree);
		//new TreeDisplay().displayTree(lolTree);
		
		//display2.displayTree(newTree);
		//new TreeDisplay().displayTree(TreeUtil.copy(newTree));
		//System.out.println(TreeUtil.sameShape(lolTree,newTree));
		
		//TreeNode decodingTree = TreeUtil.createDecodingTree(display2); 
		//display2.displayTree(decodingTree);
		//System.out.println(decodeMorse(decodingTree, ". .- -", display)); 

		/*
		tree = new TreeNode("*", 
				new TreeNode("+", new TreeNode(new Integer(7)), new TreeNode(new Integer(5))), 
				new TreeNode("+", 
				new TreeNode(new Integer(3)), 
				new TreeNode(new Integer(7)))); 
				System.out.println(TreeUtil.eval(tree)); 
				
			TreeNode decodingTree = TreeUtil.createDecodingTree(display2); 
			System.out.println(TreeUtil.decodeMorse(decodingTree, "... .-- .- --. --. .. .", display2));
*/
		tree = new TreeNode(1);
		tree.setRight(new TreeNode(2));
		tree.setLeft(new TreeNode(0));
		display2.displayTree(tree);
		
		BSTUtilities.insert(tree, 3, display2);
		BSTUtilities.insert(tree, 4, display2);
		BSTUtilities.delete(tree, 2, display2);
		
	} //close main

} //close Main class
