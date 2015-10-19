import java.util.*;

/* This class provides for the implementation of an entry in a map. It is a generic, because the 
 * key and value can be different types. It implements comparable, as it must be able to be compared -
 * this is done on the basis of the key, because keys are unique, but values are not. A MapEntry object
 * can get its key, get its value, change it value, or be compared to another MapEntry based upon their
 * key values; if the two keys are equal, then the entries are equal <i> in this particular implementation
 * </i>, seeing as two elements in a BST cannot be the exact same - in this case, have the same key.
 * @author Rahul Jayaraman
 * @version 031614
 */
public class MapEntry<K extends Comparable, V> implements Comparable
{
	//two pieces of info stored - key and value
	private K key;
	private V value;
	
	/**
	 * This two-parameter constructor takes in a key and a value and assigns them to the instance 
	 * variables.
	 * @param toBeKey is the key of the Map Entry object
	 * @param toBeVal is the associated value of the object
	 * @post creation of a new MapEntry with input key and value
	 */
	public MapEntry(K toBeKey, V toBeVal)
	{
		key = toBeKey;
		value = toBeVal;
	} //close 2-param constructor
	
	/**
	 * This zero-parameter (default) constructor initializes a MapEntry with both values as null by
	 * calling the other, two-parameter constructor with null parameters. 
	 * @post creation of a new MapEntry object with null instance variables
	 */
	public MapEntry()
	{
		this(null, null);
	} //close zero-param constructor
	
	/**
	 * This method returns the key that is stored in a MapEntry object (an instance variable).
	 * @return the object's key
	 */
	public K getKey()
	{
		return key;
	} //close getKey()
	
	/**
	 * This method returns the value that is stored in a MapEntry object (an instance variable).
	 * @return the object's value
	 */
	public V getValue()
	{
		return value;
	} //close getValue()
	
	/**
	 * This method changes the value associated with an entry by changing the associated instance variable.
	 * @param newVal is the new value to be stored in the entry
	 * @post the MapEntry now has a new value.
	 */
	public void setValue(V newVal)
	{
		value = newVal;
	} //close setValue()
	
	/**
	 * This method compares two MapEntries. It first casts the object to a MapEntry and then checks their
	 * keys; if two keys are equal, then they are the same map entry <i> for the purposes of our imple-
	 * mentation in the TreeMap</i> - after all, there cannot be two objects exactly the same in a BST,
	 * so we do not need to check value, but only for <i> this particular implementation. </i>
	 * @param o is the object that we want to compare to.
	 */
	public int compareTo(Object o)
	{
		if (o instanceof MapEntry)
		{
			MapEntry other = (MapEntry) o;
			return getKey().compareTo(other.getKey());
		} //close if
		
		return -1; //if it's not a MapEntry, what's the use of comparing?
	} //close compareTo()
	
} //close MapEntry
