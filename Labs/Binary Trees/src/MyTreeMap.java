import java.util.*;

/*
 * This class, a TreeMap, implements a Map with a BST as the underlying data structure. We are using a 
 * BST of MapEntry objects. This map can check whether it contains a key, insert a new entry, get a 
 * value given an input key, remove an element given an input key, and compare itself to other maps using
 * the value that is stored at the root node.
 * @author Rahul Jayaraman
 * @version 031614
 */
public class MyTreeMap<K extends Comparable, V> implements Comparable
{
	private TreeNode root; //underlying data structure
	private int size; //so that we can do size() in O(1) time
	private TreeDisplay disp; //to visualize the traversal
	
	/**
	 * Zero-param constructor sets all instance variables to their default values and creates a new Tree
	 * Display with a delay of 1, making the traversal more rapid.
	 */
	public MyTreeMap()
	{
		root = null;
		size = 0;
		disp = new TreeDisplay();
		
		disp.setDelay(1);
	} //close constructor
	
	/**
	 * Returns how many elements are in the map.
	 * @return size of the map (# of nodes in the underlying BST)
	 */
	public int size()
	{
		return size;
	} //close size
	
	/**
	 * This method calls the BSTUtilities method contains() to check for a key in the BST. We know
	 * that there can not be more than one key with a given value; furthermore, the compareTo() method
	 * for a MapEntry only deals with the key in this particular implementation, so we can declare
	 * a new MapEntry with value null and then search for something in the tree such that compareTo
	 * for the two objects will return 0, and thus the method will return true.
	 * @param key is the key of the element, which we are searching for in the BST
	 * @return true if the MapEntry with the key is in the tree, false otherwise
	 */
	public boolean containsKey(Object key)
	{
		MapEntry<K,V> sample = new MapEntry<K,V>((K) key, null);
		return BSTUtilities.contains(root, sample, disp);
	} //close containsKey()
	
	/**
	 * This method puts in a new MapEntry. If it already contains the key, it removes the corresponding
	 * key using the remove() method and then inserts the new entry using the BSTUtilities method insert()
	 * and then returns the value that was previously associated with that key. Otherwise, it just
	 * inserts it as normal and returns null - signifying that there was nothing before.
	 * @param key is the key of the new MapEntry that we wish to insert
	 * @param value is the value of the new MapEntry that we wish to insert
	 * @return null if it's a new key, else the old value if the key used to be associated w/ a value
	 */
	public V put(K key, V value)
	{
		MapEntry<K,V> newEntry = new MapEntry<K,V>(key, value); //new entry to be inserted
		
		if (containsKey(key))  //remove and insert
		{
			V removed = remove(key);
			root = BSTUtilities.insert(root, newEntry, disp);
			return removed;
		} //close if
		
		root = BSTUtilities.insert(root, newEntry, disp); //just insert
		return null;
		
	} //close put()
	
	/**
	 * This is the only method for which we cannot use BSTUtilities. Instead, we must use the compareTo
	 * method in order to traverse the underlying tree and get the value. If it contains the key, we
	 * make a new MapEntry and then compare it to the existing values and traverse the tree as we would
	 * a normal BST. While compareTo isn't 0, we keep shifting depending on the value, and then we
	 * return the value that is there. If it doesn't contain the key, we simply return null.
	 * @param key
	 * @return
	 */
	public V get(Object key)
	{
		if (containsKey(key))
		{
			MapEntry<K,V> sample = new MapEntry<K,V>((K) key, null); //compare entries on key not value
			TreeNode duplicate = root; //don't want to change the root!
			
			//traverse the tree in the required direction
			while (sample.compareTo(duplicate.getValue()) != 0)
			{
				int comparison = sample.compareTo(duplicate.getValue());
				if (comparison > 0) duplicate = duplicate.getRight();
				else duplicate = duplicate.getLeft();
			} //close while
			
			return (V) ((MapEntry) duplicate.getValue()).getValue(); //return the value
		}
		
		return null; //nothing with the specified key
	} //close get()
	
	/**
	 * This method removes an entry from a TreeMap. It calls upon the delete() method from BSTUtilities
	 * to accomplish this task by using a "dummy" MapEntry with the value of key as the parameter and 
	 * the value as whatever is associated with that. Then, delete traverses the tree and deletes the
	 * node with the entry in it; the "dummy" Entry's value is then returned. Else, it returns null. 
	 * @param key
	 * @return
	 */
	public V remove(Object key)
	{
		if (containsKey(key)) //contains key? OK, save the entry, delete, return value
		{
			MapEntry<K,V> sample = new MapEntry<K,V> ((K) key, get(key));
			
			root = BSTUtilities.delete(root, sample, disp);
			return sample.getValue();
		}
		
		return null;
	} //close remove()

	/**
	 * This method compares two maps by comparing the MapEntries at their roots. 
	 * @param other is the other TreeMap that we wish to compare.
	 */
	public int compareTo(Object other) 
	{
		if (other instanceof MyTreeMap) //it must be a TreeMap!
			return ((MapEntry) root.getValue()).compareTo((MapEntry) other);
		
		return -1;
	} //close compareTo()

}
