import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.util.Random;

/*
 * This class, Follower, is a type of Critter from the GridWorld case study that is presented on the AP Computer
 * Science exam. A Follower randomly picks an actor from the grid and then follows it, picking a different actor
 * to follow after it has taken 15 steps around the previous actor. After it reaches the actor it should follow,
 * it then eats it no matter what the situation, as it is perpetually hungry. It then resets the actor variable.
 * If there is nothing in the grid besides the Follower, it dies, as it's not fulfilled its mission in life. 
 * 
 * @author Rahul Jayaraman
 * @version 013113
 */
public class Follower extends Critter
{
	//instance variables for steps, current actor, and a constant for the amount of steps to change actor
	private int steps;
	private Actor currentActor;
	private static final int NUM_OF_STEPS_TO_CHANGE = 15;
	
	/**
	 * The zero-parameter constructor for follower invokes the superclass constructor and then sets the
	 * actor to null as well as the steps to 0, as expected for a newly created object. 
	 */
	public Follower()
	{
		super();
		steps = 0;
		currentActor = null;
	} //close zero-param constructor

	/**
	 * Instead of the default behavior of a Critter, i.e. getting only the neighboring actors, this Follower
	 * can get all the actors in the grid, to provide it with a wider variety of actors to follow. If there is 
	 * nothing left in the grid other than Follower, then the Follower dies as it has not fulfilled its purpose. 
	 * @return an ArrayList of all the actors in the grid
	 * @post the state of all actors in the grid is unchanged 
	 */
	public ArrayList<Actor> getActors()
	{
		//find the actors at all the occupied locations in the grid
		ArrayList<Actor> actors = new ArrayList<Actor>();
		ArrayList<Location> locs = getGrid().getOccupiedLocations();
		
		//locs size can be no less than 1 because there's always the Follower in the grid
		
		if (locs.size() != 1)
		//if there's something else in the grid apart from the follower
			
		{
			//add the actors at each location to the newly initialized ArrayList and return it
			for (Location loc : locs)
			{
				actors.add(getGrid().get(loc));
			} //close for
			
			return actors;
		} //close if
		
		else //if there's only one in the grid
		{
			removeSelfFromGrid();
			return null;
		} //close else
		
	} //close getActors()
	
	/**
	 * This method randomly assigns an actor to the currentActor variable if it's null or not in a grid after 
	 * resetting the steps to 0. If not (i.e. the currentActor variable is not null and has a value),
	 * the Follower turns to face the actor and then faces a direction that may take it closer to the actor.
	 * @param the ArrayList of actors is the list of actors that we want to "process," i.e. select to follow
	 * @Postcondition: (1) The state of all actors in the grid other than this critter and the elements of actors 
	 * is unchanged. (2) The location of this critter is unchanged.
	 */
	public void processActors(ArrayList<Actor> actors)
	{
		//if it hasn't moved to the current actor yet or it's moved 15 times or if the actor isn't in a grid
		
		if (currentActor == null || currentActor.getGrid() == null) 
		{
			steps = 0;
			currentActor = actors.get(new Random().nextInt(actors.size())); //chose new actor
		} //close if
		
		else 
		{
			//get the location and turn to face the actor
			
			Location loc = currentActor.getLocation();
			int dir = getLocation().getDirectionToward(loc);
			setDirection(dir);
			
		} //close else
		
	} //close processActors
	
	/**
	 * This creates an ArrayList of one element (type Location) and then adds to it the location that is
	 * facing in the direction of the actor that the Follower wants to follow.
	 * @return an ArrayList of the location (one element) that is facing the actor that is being followed
	 */
	public ArrayList<Location> getMoveLocations()
	{
		//define new ArrayList and the current actor's location
		ArrayList<Location> followLocs = new ArrayList<Location>();
		Location currentActorLoc = currentActor.getLocation();
		Location currLoc = getLocation();
		
		//get the closest location in the direction toward the current actor and put it in the arraylist
		followLocs.add(currLoc.getAdjacentLocation(currLoc.getDirectionToward(currentActorLoc)));
		
		return followLocs;
		
	} //close getMoveLocations
	
	/**
	 * This method moves the Follower to a valid location that is returned by selectMoveLocation. If the location
	 * is invalid, then it picks a random adjacent location to move to and then makes the move. It then
	 * increments the amount of steps; if it's greater than 15, it resets the steps to 0. Because we don't
	 * care about the implementation of makeMove in the superclass, we can just call that to move the Follower
	 * to wherever is necessary on the grid.
	 * @Postcondition (1) getLocation() == loc. (2) The state of all actors other than those at the old and 
	 * new locations is unchanged.
	 */
	public void makeMove(Location loc)
	{
		//if it's valid, make the move
		if (getGrid().isValid(loc))
		{
			super.makeMove(loc);
		} //close if
		
		//if it's invalid choose a random empty adjacent location by creating an arraylist and then
		//randomly selecting a value from that to move to
		else
		{
			ArrayList<Location> validLocs = getGrid().getEmptyAdjacentLocations(getLocation());
			Random random = new Random();
			int numOfLocs = validLocs.size();
			super.makeMove(validLocs.get(random.nextInt(numOfLocs)));
		} //close else
		
		//increment steps
		steps++;
		
		//if the steps is greater than the limit, reset
		if (steps >= NUM_OF_STEPS_TO_CHANGE) steps = 0;
	} //close makeMove
	
} //close Follower 
