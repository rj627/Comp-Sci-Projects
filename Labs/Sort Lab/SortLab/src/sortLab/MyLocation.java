package sortLab; //package declaration

/*
* This class provides for the implementation of a MyLocation object; this object
* has two instance fields - row and column, perhaps corresponding to points on a
* graph or an element in a 2D array. The class includes methods to get the row, the
* column, the toString method, the equals method, and the compareTo method. 
* @author Mr. Richard Page & Rahul Jayaraman
* @version 112013
*/
public class MyLocation implements Comparable
{
	private int row; //first part of MyLocation object
	private int col; //second part of MyLocation object

	/**
	 * This constructor makes a new MyLocation object, given two parameters - its
	 * row and its column, which are input by the user. 
	 * @param r is the desired row of the MyLocation object
	 * @param c is the desired column of the MyLocation object
	 */
	public MyLocation(int r, int c)
	{
		row = r; //initialize instance variables
		col = c;
	} //close constructor

	/**
	 * This method gets the row of a particular MyLocation object by merely returning
	 * whatever is stored in the instance field of the object. 
	 * @return the row of the MyLocation object
	 */
	public int row()
	{
		return row; //return the instance variable
	} //close row

	/**
	 * This method gets the column of a particular MyLocation object by merely returning
	 * whatever is stored in the instance field of that object. 
	 * @return the column of the MyLocation object
	 */
	public int col()
	{
		return col; //return the instance variable
	} //close col

	/**
	 * This method tests if a MyLocation object is equal to another object by comparing
	 * their instance fields - if both the row and column match, then it will return true;
	 * if one or the other does not match, it will return false. However, first, we must
	 * cast the input object to a MyLocation - because it's passed in as an object!
	 * @return true if instance fields match; false otherwise
	 */
	public boolean equals(Object other)
	{
		if (!(other instanceof MyLocation)) //check if it's a MyLocation
			return false; //if it's not, then they can't be equal!
		MyLocation loc = (MyLocation)other; //cast it to MyLocation
		return row == loc.row() && col == loc.col(); //test for equality of instance fields
	} //close equals()

	/**
	 * This method provides for a toString representation of the MyLocation object - 
	 * it puts the row and column into an ordered pair and converts this to a string. 
	 * @return an ordered pair of the form (row, col) from the object's instance variables
	 */
	public String toString()
	{
		return "(" + row + ", " + col + ")"; //put the instance fields in parentheses
		//as an ordered pair - it can be an element in a 2D array or a point on a 
		//2D Cartesian coordinate plane
	} //close toString

	/**
	 * This method compares two MyLocation objects. MyLocation objects have a row and
	 * a column instance variable, and the row one is "prioritized" - it is arranged
	 * by increasing row first and then by increasing column. We have accounted for this
	 * by making the column comparisons last in the method. 
	 * @param x is the Object that we want to compare MyLocation to
	 * @return a number that can be either negative, 0, or positive - negative if 
	 * the object x comes AFTER, 0 if they're equal, and positive if the object x is BEFORE
	 */
	public int compareTo(Object x)
	{
		if (!(x instanceof MyLocation)) //if x isn't a MyLocation, what's the use?
			return -1;
		
		MyLocation loc = (MyLocation) x; //cast x to MyLocation otherwise
		
		if (this.row  < loc.row) //compare the row to the row of loc
		{
			return -1; //it's less, so it must come BEFORE
		} //close if
		else if (this.row > loc.row) //else if it's greater
		{
			return 1; //comes AFTER
		} //close else if
		else if (this.col < loc.col) //if rows are equal, but columns differ
		{
			return -1;  //it comes BEFORE
		} //close else if
		else if (this.col > loc.col) //final comparison
		{
			return 1; //it comes AFTER
		} //close else if
		
		return 0; //the two objects are "equal" - same row + column values
	} //close method
} //close class