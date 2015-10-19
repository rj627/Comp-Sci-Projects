import info.gridworld.actor.*;

/*
 * This class, DancingBug, creates a bug that moves based on values in an array. This array
 * is passed to the constructor, which passes each value to the Bug (which then turns
 * the indicated number of times). It can trace out intricate patterns using this.
 * @author Rahul Jayaraman
 * @version 121213
 */
public class DancingBug extends Bug
{
	private int[] numOfTurns; //array of number of turns
	private int currentStep; //a variable keeping track of how many steps it's taken
	
	/**
	 * Constructor for DancingBug, given an array specifying it when to turn.
	 * @param arrOfTurns contains values that tell it how many times to turn.
	 */
	public DancingBug(int[] arrOfTurns)
	{
		super(); //make a new bug and initialize the instance fields
		currentStep = 0;
		numOfTurns = arrOfTurns;
	} //close constructor
	
	/**
	 * This will tell the bug to rotate until it has finished the set number of times. 
	 * @param times
	 */
	public void turn(int times)
	{
		for (int i = 0; i <= times; i++)
		{
			turn(); //keep turning until the counter reaches times
		} //close loop
	} //close turn()
	
	/**
	 * This method will pass the current entry in the array to the turn() method and then increment
	 * the numOfSteps; if this equals numOfTurns.length, it will reset.
	 */
	public void act()
	{
		if (currentStep == numOfTurns.length)
			currentStep = 0; //conditions to reset the currentStep
		turn(numOfTurns[currentStep]); //turn as many times as specified by the array
		currentStep++; //increment currentStep so that you don't keep turning the same time
		super.act(); //do whatever it is a Bug does
	} //close act()

} //close DancingBug
