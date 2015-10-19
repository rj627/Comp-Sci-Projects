import info.gridworld.actor.*;
import java.util.ArrayList;
import info.gridworld.grid.*;
import java.util.Random;

/*
 * This class, Wolf, is a type of Critter that is featured on the AP Computer Science A exam. A critter usually
 * eats all the neighboring actors and moves; however, a wolf eats the actors only in front of it (i.e. directly
 * in front of it) and reproduces once it has eaten five (5) actors consecutively. However, if it does not eat 
 * anything after a move, it becomes hungrier and hungrier, and if this repeats five (5) times, then it dies. 
 * @author Rahul Jayaraman
 * @version 012914
 */
public class Wolf extends Critter
{
	/*instance fields for the amount of meals it has had, the max meals, how many hunger points leads to 
	 *starvation, and a counter to keep track of hunger points
	 */
	
	private int meals;
	private int hunger;
	
	private static final int MAX_MEALS = 5;
	private static final int STARVATION = 5;
	
	
	/**
	 * Zero-parameter constructor. It makes a new Critter using super() and then adds the Wolf instance variables
	 * to it and initializes them to 0. 
	 * @post a new Wolf is created and placed in the grid at some location. 
	 */
	public Wolf()
	{
		super();
		meals = 0;
		hunger = 0;
	} //close zero-param constructor

	/**
	 * ProcessActors uses a for loop to process the actors that are nearest to it in the grid. In the list
	 * that is passed in, it checks if the actor is <i> directly in front of it </i> (or if it's a rock) and 
	 * then eats it, resets the hunger counter, increments the meal counter, as well as a local variable 
	 * currentMeals that is later used to determine whether the hunger variable should be incremented. 
	 * @param actors is the list of actors that we want to process in the above manner
	 * @post no change in loc and state of all things other than critter + actors is unchanged
	 */
	public void processActors(ArrayList<Actor> actors)
	{
		int currentMeals = 0;
		
		//traverse the actors ArrayList and process each actor if it's not a rock
		for (int i = 0; i < actors.size(); i++)
		{
			//local variables to minimize the amount of single-line code
			Actor actor = actors.get(i);
			Location currLoc = getLocation();
			Location actorLoc = actor.getLocation();
			
			if ((!(actor instanceof Rock)) && 
					actorLoc.getRow() + 1 == currLoc.getRow() &&
					actorLoc.getCol() == currLoc.getCol()) //if it's directly in front and if it's not a rock
			{
				//change the instance variables (side effect) and kill the actor
				hunger = 0; 
				meals++;
				currentMeals++;
				actors.get(i).removeSelfFromGrid();
			} //close if
		
		} //close for

		//if the creature is still hungry increase the hunger counter
		if (currentMeals == 0)
		{
			meals = 0;
			hunger++;
		} //close if
	} //close processActors()
	
	/**
	 * This code moves the wolf to a new location and then checks if it needs to breed or die. If it needs
	 * to breed, a new wolf is created at a random location and the meals & hunger counter is reset. However,
	 * if the hunger is greater than the starvation limit, it dies. 
	 * @param loc is the location that we want to move the Wolf to
	 * @post getlocation = loc, all actors at prevloc and loc are only ones w/ states changed
	 */
	public void makeMove(Location loc)
	{
		super.makeMove(loc); //move to the location
		
		if (meals == MAX_MEALS)
		{
			//get a list of empty locations and put in a new wolf at one of these
			ArrayList<Location> empty = getGrid().getEmptyAdjacentLocations(getLocation());
			new Wolf().putSelfInGrid(getGrid(), empty.get(new Random().nextInt(empty.size())));
			
			//reset instance variables
			meals = 0;
			hunger = 0;
		} //close if
		
		else
		{
			//die
			if (hunger >= STARVATION)
				removeSelfFromGrid();
		} //close else
		
	} //close makeMove()
	
} //close Wolf class
