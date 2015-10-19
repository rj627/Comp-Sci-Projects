/*
 * This class implements a set based upon a binary tree. A set is an unordered collection of data that can 
 * only have one copy of each element. This class supports finding the size of a set, determining whether
 * a set contains an object, adding an element to the set, or removing an element from a set.
 * @author Rahul Jayaraman
 * @version 031614
 */
public class MyTreeSet<E>
{
	private TreeNode root; //underlying data structure
	private int size; //so that it can get its size in O(1) time
	private TreeDisplay display;

	/**
	 * Default zero-param constructor; all instance fields are set to null except TreeDisplay - a new
	 * display is initialized, and it is made to run faster than normal (1 ms v/s 500 ms). 
	 */
	public MyTreeSet()
	{
		root = null;
		size = 0;
		display = new TreeDisplay();

		//wait 1 millisecond when visiting a node
		display.setDelay(1);
	} //close zero-param constructor

	/**
	 * Finds the size of a particular set; this is stored as an instance variable.
	 * @return the size of the set
	 */
	public int size()
	{
		return size;
	} //close size()

	/**
	 * Determines whether the set contains the object by using the BSTUtilities method contains().
	 * @param obj is the object whose presence we wish to determine
	 * @return true if it's there, false otherwise
	 */
	public boolean contains(Object obj)
	{
		return BSTUtilities.contains(root, (Comparable) obj, display);
	} //close contains()


	/**
	 * This method adds an object to the set using the BSTUtilities method insert(). If the set already
	 * contains the object, it cannot insert it and then returns false. It also increments the size.
	 * @param obj is the object we wish to add
	 * @return true if the add was successful, false otherwise
	 * @post there is a new element in the set and size is now size + 1
	 */
	public boolean add(E obj)
	{
		if (contains(obj)) return false;
		
		else //if the object is "foreign" to the set
		{
			root = BSTUtilities.insert(root, (Comparable) obj, display);
			size++;
			return true;
		} //close else
		
	} //close add()


	/**
	 * This method removes an object from the set using the BSTUtilities method. It checks whether it
	 * contains the method and then removes it, decrements the size, and returns true. 
	 * @param obj is the object which we want to remove from the set
	 * @return
	 */
	public boolean remove(Object obj)
	{
		if (!contains(obj)) return false;
		
		else //it's in the set
		{
			root = BSTUtilities.delete(root, (Comparable) obj, display);
			size--;
			return true;
		} //close else
		
	} //close remove()

	/**
	 * This method returns a string version of the set - it looks like an array. It calls a helper method
	 * to do its job.
	 */
	public String toString()
	{
		return toString(root);
	} //close toString()

	/**
	 * This method returns a string representation of the set in ascending order. It performs an in-order
	 * traversal of the BST to print out the values - in ascending order.
	 * @param t is the tree node which we wish to return a string of
	 * @return a string representation of the tree
	 */
	private String toString(TreeNode t)
	{
		if (t == null)
			return " ";
		return toString(t.getLeft()) + t.getValue() + "," + toString(t.getRight()); //recursive traversal
	} //close overloaded toString()
	
} //close TreeSet class