import java.util.*;

/*
 * A binary search tree is a data structure that is ordered - all values to the left of a root are less
 * than the root value; all values to the right are greater. This makes it easy to search for an element.
 * This class is used to implement the binary search tree methods contains (which checks if an element
 * is present in a binary search tree), insert (which inserts a node into a tree), and delete (which 
 * deletes a node from a tree). 
 * @author Rahul Jayaraman
 * @version 031113
 */

public abstract class BSTUtilities
{
	/**
	 * Contains checks if a binary tree contains a particular element. A null tree cannot contain anything;
	 * the second base case is if the value equals x, in which case the tree does contain it.
	 * Otherwise, x is compared to the value and if it's greater, the right sub-tree is searched, else
	 * the left is searched.
	 * @param t is the tree that we wish to search for the value
	 * @param x is the value that we are looking for
	 * @param display is the way that we will visualize the tree
	 * @return true if the value is in the tree, false otherwise
	 */
    public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
    {
    
    	if (t == null) return false; //base case 
    	
    	else if (x.compareTo(t.getValue()) < 0) //less than node's value
    	{
    		display.visit(t.getLeft());
    		return contains(t.getLeft(), x, display);
    	} //close else if
    	
    	else if (x.compareTo(t.getValue()) > 0)//greater than node's value
    	{
    		display.visit(t.getRight());
    		return contains(t.getRight(), x, display);
    	}
    	
    	display.visit(t); //if they are equal
		return true;
		
    } //close contains()
    
    /**
     * Insert puts a node into an input binary search tree. It checks if it contains the value, as a 
     * binary search tree cannot have duplicate values. Then, it checks if the value to be inserted is 
     * greater or less than the current value at the node. It then returns a pointer to the tree
     * with the newly inserted element.
     * @param t is the tree into which we want to insert the value
     * @param x is the value that we want to insert
     * @param display is a means of visualizing the insertion process
     * @return a pointer to the tree with the new element
     */
    public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
    {
    	if (t == null) 
    		return new TreeNode(x); //base case
    	
    	else if (x.compareTo(t.getValue()) < 0)
    	{
    		display.visit(t);
    		t.setLeft(insert(t.getLeft(), x, display)); //less than the node value
    	} //close if
    	
    	else if (x.compareTo(t.getValue()) > 0)
    	{
    		display.visit(t);
    		t.setRight(insert(t.getRight(), x, display)); //greater than the node value
    	} //close else
    	
    	return t;
    	
    } //close insert()
    
   /**
    * DeleteNode deletes the top node of a tree. In writing this method, we must analyze three cases - 
    * the case where the node does not have a successor (in which case we just return a pointer to the left
    * subtree); the case where the node's successor is its immediate right one; in which case we shift
    * it up and make that the new root. The last case occurs when the successor is elsewhere in the
    * tree - we splice it out, do a little bit of node manipulation at the bottom, and then swap the
    * values (for the root and the successor), and return a pointer to the changed tree.
    * @param t is the node which we wish to delete
    * @param display is the display with which to visualize it
    * @return a pointer to the treeNode with missing values
    * @post t now is missing its root and has a different element in its place
    */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    {
        if (t == null)
            return null; //base case
        
        else 
        {
            display.visit(t); //light up the node 
            TreeNode successor = t.getRight(); 
            
            if (successor == null)
                t = t.getLeft(); //no right subtree, so we just make t the left subtree
            
            else if (successor.getLeft() == null) //right node is successor, so we swap and return 
            {
                t.setValue(successor.getValue());
                t.setRight(successor.getRight());
            } //close else if
            
            else
            {
            	/*this searches for the successor, we maintain a pointer to the parent node
            	 *because we are bypassing the successor after swapping the value (eliminating
            	 *that node from the tree)
            	 */
            	
                TreeNode otherSuccessor = t.getRight(); //second leftmost value
                while (successor.getLeft() != null) //find the successor
                {
                    otherSuccessor = successor;
                    successor = successor.getLeft();
                } //close while loop
                
                //swap and eliminate
                t.setValue(successor.getValue());
                otherSuccessor.setLeft(successor.getRight());
            } //close else
            
            return t;
            
        } //close else
        
    } //close deleteNode() method
  
  
    /**
     * This method implements the deleteNode method written above. It searches an input tree for a given
     * value and then deletes that node (using the method above). The search is conducted in a normal
     * manner, comparing the keys (just as in contains()). 
     * @param t is the tree from which we wish to delete the value x
     * @param x is what we want to delete
     * @param display is how we visualize this (used in deleteNode())
     * @return a pointer to the updated tree sans that element
     */
    public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
    {
    	if (t == null) return null; //base case
    	
        if (t.getValue().equals(x)) 
        	return deleteNode(t, display); //base case 2
        
        if (x.compareTo(t.getValue()) < 0)
        	t.setLeft(delete(t.getLeft(), x, display)); //less than the node
        
        if (x.compareTo(t.getValue()) > 0)
        	t.setRight(delete(t.getRight(), x, display)); //greater than the node
        
        return t;
    } //close delete()

} //close BSTUtilities class