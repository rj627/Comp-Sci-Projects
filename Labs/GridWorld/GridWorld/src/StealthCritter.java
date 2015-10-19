import java.util.Random;
import info.gridworld.grid.*;
import info.gridworld.actor.Critter;
import java.util.ArrayList;

/*
 * The StealthCritter class models a type of Critter that is on the AP Computer Science Exam's GridWorld Case Study.
 * A StealthCritter behaves almost exactly like a critter, with the major exception that it can teleport. This
 * teleportation is accomplished with an override of the selectMoveLocation() method from the Critter class; other
 * than that, all other methods are inherited and not overridden. 
 * @author Rahul Jayaraman
 * @version 012814
 */
public class StealthCritter extends Critter
{
	/**
	 * Zero-parameter constructor for a StealthCritter. It is the same as a critter, so we call super().
	 */
	public StealthCritter()
	{
		super();
	} //close zero-param constructor
	
	/**
	 * This method, which returns potential Locations that the <code>StealthCritter</code> could move to, 
	 * introduces the element of <i>teleportation</i> through the use of Math.random(). Math.random() produces
	 * a double that is stored locally as a teleportation factor; if this is greater than 0.7, then the 
	 * Critter generates an ArrayList of random locations throughout the grid. If it is not, then the superclass's
	 * version of the getMoveLocation() method is utilized, and it behaves like a normal critter. 
	 * @Postcondition The state of all actors is unchanged. 
	 * @param none
	 * @return an ArrayList of possible locations that the StealthCritter could move to 
	 */
	public ArrayList<Location> getMoveLocations()
	{
		//introduce a teleportation factor and a random number generator to use in the if/for statements
		double teleportationFactor = Math.random();
		Random randomNum = new Random();
		
		ArrayList<Location> possibleLocs = new ArrayList<Location>(); //declare a new ArrayList of locations
		
		if (teleportationFactor > 0.7)
		{
			for (int i = 0; i < 25; i++)
			{
				// get/create 25 random locations, add them to the ArrayList, move on
				int randomCol = randomNum.nextInt(getGrid().getNumCols());
				int randomRow = randomNum.nextInt(getGrid().getNumRows());
				Location newLoc = new Location(randomRow, randomCol);
				possibleLocs.add(newLoc);
				
			} //close for loop
			
			return possibleLocs;
		} //close if statement
		
		else
			return super.getMoveLocations(); //if the teleportation factor isn't above a certain limit, this
			//will behave just like a normal critter, hence the call to super
		
	} //close getMoveLocations()
	
} //close StealthCritter class
