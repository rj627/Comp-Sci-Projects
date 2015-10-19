import info.gridworld.grid.*;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.awt.Color;

/*
 * This class, Tetrad, provides for the functionality of making a collection of four adjacent
 * blocks in the MyBoundedGrid<E> interface that is present in the AP Computer Science A exam. 
 * The Tetrad class knows the blocks that a Tetrad contains, the grid that it is in, the various
 * colors possible for blocks, as well as a semaphore that prevents concurrent modification 
 * of the block from a different threat. It can translate, rotate, add itself to a location, 
 * remove itself from a location, and make sure a location is empty before moving there. 
 * @author Rahul Jayaraman
 * @version 011613
 */
public class Tetrad 
{
	/*defined instance variables (the blocks that it is comprised of) and the grid it is in as
	 *well as a construct to prevent concurrent modification (semaphore)
	 */
	
	private Block[] blocks;
	private MyBoundedGrid<Block> grid;
	private Semaphore lock;
	
	//the only initialized instance variable has all the colors possible
	private Color[] types = {Color.RED,Color.GRAY, Color.CYAN,Color.YELLOW,Color.MAGENTA,
			Color.BLUE,Color.GREEN};
	

	/**
	 * One-parameter constructor for a tetrad. In this constructor, the grid is initialized, 
	 * as is the array of blocks. A random color is chosen from the array, and depending on
	 * the color, each of the blocks is changed to fit the color and associated shape. The
	 * blocks appear in the grid at the very top center - each location is defined below.
	 * @param gr is the grid that the Tetrad will be placed in
	 * @post the grid will now have a tetrad inside it; only one is active in the Tetris class
	 */
	public Tetrad(MyBoundedGrid<Block> gr)
	{
		//initialize instance variables
		grid = gr; 
		lock = new Semaphore(1, true); 
		
		Color chosen = types[new Random().nextInt(types.length)]; //choose a random color
		
		blocks = new Block[4]; //represents a tetrad
		
		for (int i = 0 ; i < 4; i++)
			blocks[i] = new Block(); 
		
		//the below code deals with all the cases for the chosen color
		
		if (chosen.equals(Color.RED))
		{
			for (int i = 0; i < 4; i ++)
				blocks[i].setColor(Color.RED); 
			
			//indicates the location where each block of the Tetrad (shaped like I) should go
			Location[] locs = {new Location(1, 4), 
							   new Location(0, 4),
							   new Location(2, 4),
							   new Location(3, 4)}; 
			
			addToLocations(grid, locs); 
			
		} //close if
		
		else if (chosen.equals(Color.GRAY))
		{
			for (int i = 0; i < 4; i ++)
				blocks[i].setColor(Color.GRAY); 
			
			//indicates the location where each block of the Tetrad (T) should go
			Location[] locs = {new Location(0, 4), 
							   new Location(0, 3),
							   new Location(0, 5),
							   new Location(1, 4)};
			
			addToLocations(grid, locs); 
			
		} //close if
		
		else if (chosen.equals(Color.CYAN))
		{
			for (int i = 0; i < 4; i ++)
				blocks[i].setColor(Color.CYAN); 
			
			//indicates the location where each block of the tetrad (O) should go
			Location[] locs = {new Location(0, 4), 
							   new Location(0, 3),
							   new Location(1, 4),
							   new Location(1, 3)};
			
			addToLocations(grid, locs); 
			
		} //close if
		
		else if (chosen.equals(Color.YELLOW))
		{
			for (int i = 0; i < 4; i ++)
				blocks[i].setColor(Color.YELLOW); 
			
			//indicates the location where each block of the tetrad (L) should go
			Location[] locs = {new Location(1, 4), 
							   new Location(0, 4),
							   new Location(2, 4),
							   new Location(2, 5)};
			
			addToLocations(grid, locs); 
			
		} //close if
		
		else if (chosen.equals(Color.MAGENTA))
		{
			for (int i = 0; i < 4; i ++)
				blocks[i].setColor(Color.MAGENTA); 
			
			//indicates the location where each block of the tetrad (J) should go
			Location[] locs = {new Location(1, 4), 
							   new Location(0, 4),
							   new Location(2, 4),
							   new Location(2, 3)};
			
			addToLocations(grid, locs); 

		} //close if
		
		else if (chosen.equals(Color.BLUE))
		{
			for (int i = 0; i < 4; i ++)
				blocks[i].setColor(Color.BLUE); 
			
			//indicates the location where each block of the tetrad (S) should go
			Location[] locs = {new Location(1, 4), 
							   new Location(1, 3),
							   new Location(0, 4),
							   new Location(0, 5)};
			
			addToLocations(grid, locs); 

		} //close if
		
		else if (chosen.equals(Color.GREEN))
		{
			for (int i = 0; i < 4; i ++)
				blocks[i].setColor(Color.GREEN); 
			
			//indicates the location where each block of the tetrad (Z) should go
			Location[] locs = {new Location(0, 4), 
							   new Location(1, 4),
							   new Location(1, 5),
							   new Location(0, 3)};
			
			addToLocations(grid, locs); 

		} //close if

	} //close one-parameter constructor
	
	/**
	 * This helper method puts the set of blocks stored in the blocks instance variable 
	 * in the specified locations in the specified grid. It uses the putSelfInGrid method
	 * of each block in the array to insert the blocks into the grid at the corresponding
	 * locations - using a for loop construct to accomplish this end. 
	 * @param grid is the grid that the blocks are being inserted in, usually the value
	 * associated with the Tetrad's <i>grid</i> instance variable. 
	 * @param locs is the array of locations that the Tetrad will occupy; each block
	 * in the array maps to the corresponding element in the locations array
	 * @post the blocks are in the grid at the specified locations
	 */
	private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
	{
		for (int i = 0; i < locs.length; i++)
		{
			blocks[i].putSelfInGrid(grid, locs[i]); //insertion
			
		} //close for loop
		
	} //close addToLocations()
	
	/**
	 * This method removes the blocks associated with a particular tetrad by cycling through
	 * the blocks array, adding each location to a local Location array, and using the removeSelf-
	 * FromGrid method to exit the grid. The local Location array is then returned. 
	 * @return the Location array with all the locations of the blocks just removed from the grid
	 * @post the blocks are now not in the grid
	 */
	private Location[] removeBlocks()
	{
		Location[] prevLocs = new Location[4]; 
		
		for (int i = 0; i < 4; i ++)
		{
			//add and remove
			
			prevLocs[i] = blocks[i].getLocation(); 
			blocks[i].removeSelfFromGrid(); 
			
		} //close for loop
		
		return prevLocs; 
			
	} //close removeBlocks()
	
	/**
	 * This method checks whether all of an input array of locations are empty. It does this
	 * by cycling through the array using a for loop and then checking that there is nothing 
	 * at that location (making sure that it does return null). If at any point, it returns
	 * something other than null, it exits the loop and returns false. 
	 * @param grid is the grid in which we want to check whether the locations are empty
	 * @param locs is the array of locations in the grid that we want to check if empty
	 * @return true if all the locations are empty; false if at least one is occupied
	 */
	private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
	{
		for (int i = 0; i < locs.length; i++)
		{
			if (grid.get(locs[i])!= null) 
				return false; 
			
		} //close for 
		
		return true; //if all the locations store "null" then the row is empty
				
	} //close areEmpty()
	
	/**
	 * This method moves a Tetrad laterally or vertically, depending upon the input. It is
	 * especially useful in the Tetris class, in each of the key-press methods. In the method,
	 * a local array is created that holds the new locations of the blocks and tests their
	 * validity. Then, the previous Locations are stored in an array as the removeBlocks()
	 * method is called; the new locations are tested if they're empty. If they're empty,
	 * the move can proceed as normal; if not, the blocks are placed back in original locations.
	 * It employs a semaphore to prevent concurrent modification of the Tetrad by the Tetris
	 * class's play() method, which automatically moves the tetrad down one space. 
	 * @param deltaRow is the amount by which the row changes during the translation
	 * @param deltaCol is the amount by which the column changes during translation
	 * @return true if the translation was successful, false otherwise
	 * @post the block is moved deltaRow and deltaCol to a new location
	 */
	public boolean translate(int deltaRow, int deltaCol)
	{
		try
		{
			lock.acquire(); //acquire the semaphore
			
			Location[] newLocs = new Location[4]; 
		
			for (int i = 0; i < 4; i++)
			{
				//adding Locations based on deltaRow and deltaCol to the array
				
				newLocs[i] = new Location(blocks[i].getLocation().getRow() + deltaRow,
						 			  	  blocks[i].getLocation().getCol() + deltaCol);
				
				if (!grid.isValid(newLocs[i])) return false;
				
			} //close for loop
		
			//remove and add depending on situation - if new place is empty, move, else stay
			
			Location[] temp = removeBlocks(); 
			
			if (!areEmpty(grid, newLocs)) 
			{
				addToLocations(grid, temp);
				return false;
				
			} //close if
		
			else
			{
				addToLocations(grid,newLocs); 
				return true;
				
			} //close else
			
		} //close try
		
		catch(InterruptedException e) 
		{
			return false;
			
		} //close catch
		
		finally
		{
			lock.release(); 
			
		} //close finally
		
	} //close translate()
	
	/**
	 * This method rotates a block 90 degrees clockwise. It uses the formulae
	 * <br><br><b> row' = row_init - col_init + col </b><br> 
	 * <b> col' = row_init + col_init - row </b><br><br>
	 * (where row_init and col_init are the locations about which it is being rotated, and
	 * col and row are the block's current locations) in order to calculate the new location. 
	 * It creates a new array of these locations and tests their validity. After this, it 
	 * removes the blocks from their old location and tests if the new locations are empty. 
	 * If so, it proceeds with the rotation; if not, it puts the blocks back where they were.
	 * This method also uses a semaphore to prevent concurrent modification of the Tetrad. 
	 * @return true if the rotation was success, false otherwise
	 * @post the block is rotated 90 degrees clockwise
	 */
	public boolean rotate()
	{
		try
		{
			lock.acquire(); 
			
			Location[] newLocs = new Location[4]; //future locations
			
			Location currentLoc = blocks[0].getLocation(); //point to be rotated about
			
			for (int i = 0; i < 4; i++)
			{
				Location otherLoc = blocks[i].getLocation(); 
			
				//add the new location using the formula to the newLocs array
				newLocs[i] = new Location(currentLoc.getRow() - currentLoc.getCol() + otherLoc.getCol(),
						 			  currentLoc.getRow() + currentLoc.getCol() - otherLoc.getRow());
			
				if (!grid.isValid(newLocs[i])) //test validity
					return false;
				
			} //close for loop
		
			//remove blocks; if new locations are empty, put them there, else put them back
			
			Location[] temp = removeBlocks(); 
		
			if (areEmpty(grid, newLocs))  
			{
				addToLocations(grid, newLocs); 
				return true;
				
			} //close if
		
			else 
			{
				addToLocations(grid,temp); 
				return false;
				
			} //close else
			
		} //close try
		
		catch(InterruptedException e) 
		{
			return false;
			
		} //close catch
		
		finally
		{
			lock.release(); 
			
		} //close finally
		
	} //close rotate()
	
} //close Tetrad class
