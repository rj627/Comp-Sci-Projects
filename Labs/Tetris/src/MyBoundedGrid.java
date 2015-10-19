//import necessary libraries for our project
import java.util.ArrayList;

import info.gridworld.grid.*;


/*
 * This class provides for usage of a grid for the GridWorld case study present
 * in the AP Computer Science course. Methods in this class can return the number of
 * rows or columns, check if a location is valid, put an object into the grid (and return
 * what was there previously), remove an object from the grid (and return it), return an
 * object from the grid, and get an ArrayList of the occupied locations in the grid. This
 * extends the abstract class AbstractGrid, which implements the Grid interface. 
 * @author Cynthia Hao and Rahul Jayaraman
 * @version 120313
 */
public class MyBoundedGrid<E> extends AbstractGrid<E> implements Grid<E>
{
	private Object[][] occupants; //a 2-D array of the occupants of the grid
	
	//default number of rows and columns
	public static final int NUM_OF_ROWS = 10; 
	public static final int NUM_OF_COLS = 10; 
	
	/**
	 * This constructor creates a new 2D array with the inputted values of rows and columns.
	 * The array is row-preferential. This is the constructor that will always be called no matter
	 * what, as it is implemented in our override of the default constructor. 
	 * @param rows is the number of rows in the grid
	 * @param cols is the number of columns in the grid
	 */
	public MyBoundedGrid(int rows, int cols)
	{
		occupants = new Object[rows][cols];
	} //close 2 parameter MyBoundedGrid constructor
	
	/**
	 * This constructor creates a 2D array with the default values of rows and columns,
	 * stored as constants at the level of the other instance variables. The first bracket
	 * represents the number of rows, and the second bracket represents the number of 
	 * columns. This implements the 2-parameter constructor defined above.
	 */
	public MyBoundedGrid()
	{
		this(NUM_OF_ROWS, NUM_OF_COLS); //use the constants as the rows and columns of the array
	} //close default constructor
	
	/**
	 * This method returns the number of rows in the 2D array of occupants. It will
	 * return the length of the array of occupants.
	 * @return the number of rows in the grid
	 */
	public int getNumRows()
	{
		return occupants.length;
	} //close getNumRows()
	
	/**
	 * This method returns the number of columns in the 2D array of occupants. It will
	 * return the length of an array at a given position in the main array. 
	 * @return the number of columns in the grid
	 */
	public int getNumCols()
	{
		return occupants[0].length;
	} //close getNumCols()
	
	/**
	 * This method will return whether an inputted location is within the range
	 * permitted by the array of occupants; that is, whether the row of the Location
	 * object is between 0 (inclusive) and the number of rows in the grid and whether 
	 * the column of the object is between 0 (inclusive) and the number of columns in 
	 * the grid. Both these conditions must be satisfied, so we use the && operator. 
	 * @param loc is the location that we want to check the validity of
	 * @return true if it's valid, false if it is not.
	 */
	public boolean isValid(Location loc)
	{
		if (loc.getRow() >=0 && loc.getRow() < getNumRows() && loc.getCol() >=0 && loc.getCol() < getNumCols())
			return true; //check all necessary conditions
		else
			return false; //one or more is not satisfied
	} //close isValid()
	
	/**
	 * This method will insert an object into the grid at the specified location. This method 
	 * will check whether the location is valid using the isValid() method and then insert it. It 
	 * will then return the object that was previously at the location; if none was there, then it
	 * will return null. 
	 * @param loc is the location where we want to place the object.
	 * @param obj is the object that we want to insert into the grid
	 * @return what was previously there (or null if nothing was there)
	 * @throws IllegalArgumentException if the location is not valid
	 */
	public E put(Location loc, E obj)
	{
		if (!isValid(loc))
			throw new IllegalArgumentException(); //how can you put if it's invalid?
		
		E temp = get(loc); //make sure to keep whatever was there
		occupants[loc.getRow()][loc.getCol()] = obj;
		return temp; //return what was there previously
	} //close put()
	
	/**
	 * This method removes an object from the grid. It first checks if the location is valid and then 
	 * stores whatever was there in a temporary variable. It sets the pointer at the location to null,
	 * and then returns the temporary variable.
	 * @param the location that we want to remove the object from
	 * @return what previously used to be at that location
	 * @throws IllegalArgumentException() if the location is invalid
	 */
	public E remove(Location loc)
	{
		if (!isValid(loc))
			throw new IllegalArgumentException(); //throw the exception
		
		//store it in a temporary variable and set the location's pointer to null
		E temp = get(loc); 
		occupants[loc.getRow()][loc.getCol()] = null; //nothing's there anymore
		return temp;
	} //close remove()

	/**
	 * This method gets whatever is stored at a particular location by locating the element
	 * in the 2D array and then casting it to an E and returning it. This method is also
	 * used in the remove() and put() methods.
	 * @param loc is the location that we want to find the object at
	 * @return what is stored at loc
	 * @throws ILlegalArgumentException if the location is invalid
	 */
	public E get(Location loc) 
	{
		if (!isValid(loc))
			throw new IllegalArgumentException(); //throw the exception
		else
			return (E) occupants[loc.getRow()][loc.getCol()]; //find it in the 2D array
	} //close get()

	/**
	 * This method traverses the 2D array and then adds to a local ArrayList; the bounds
	 * for the nested for loop for traversal are getNumRows() and getNumCols(). This 
	 * ensures that the location inputted is always valid, and we have no need to throw
	 * an IllegalArgumentException().
	 * @param none
	 * @return an array list of the Locations that are occupied by Bugs, etc. 
	 */
	public ArrayList<Location> getOccupiedLocations() 
	{
		ArrayList<Location> locs = new ArrayList<Location>(); //declare a local array list
		
		//traverse the 2D array with a nested for loop
		for (int i = 0; i < getNumRows(); i++)
		{
			for (int j = 0; j < getNumCols(); j++)
			{
				Location loc = new Location(i,j); //make a location with the loop index
				
				if (get(loc) != null) //if there's something actually at the location, add to locs
					locs.add(loc);
					
			} //close for loop 1
		} //close for loop 2
		return locs; //return the ArrayList
	} //close getOccupiedLocations()
	
} //close MyBoundedGrid class
