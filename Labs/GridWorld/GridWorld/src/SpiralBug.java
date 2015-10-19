
import info.gridworld.actor.Bug;

/*
 * This class, SpiralBug, is a specific implementation of the Bug class; this bug travels in a spiral, 
 * separated by squares (segments?) of width 1. It inherits all the methods of the Bug class but over-
 * rides the act() method. Instance fields include steps, sideLength, and a LENGTH_OF_SIDE constant 
 * input into a constructor (default). 
 * @author Rahul Jayaraman
 * @version 121213
 */
public class SpiralBug extends Bug
{
	//steps and sideLength represent how many steps the bug has taken and the length of the spiral's side
	 	private int steps;
	    private int sideLength;
	    public static final int LENGTH_OF_SIDE = 2; //default length of side

	    /**
	     * Constructs a box bug that traces a square of a given side length
	     * @param length the side length
	     */
	    public SpiralBug(int length)
	    {
	        steps = 0;
	        sideLength = length;
	    } //close SpiralBug
	    
	    /**
	     * 0-param constructor, specific value used is 2
	     */
	    public SpiralBug()
	    {
	    	this(LENGTH_OF_SIDE); //call the same constructor with a set value
	    }

	    /**
	     * Moves to the next location of the square and turns in a spiral. If it can move; it will move;
	     * if the steps have exceeded the sideLength; it resets and increases the sideLength to trace out
	     * a side that is one square longer.
	     */
	    public void act()
	    {
	        if (steps < sideLength && canMove())
	        {
	            move(); //move!
	            steps++; //increment steps
	        }
	        
	        else
	        {
	            turn(); //turn 90 degrees
	            turn();
	            steps = 0; //reset steps
	            sideLength++; //make a greater side length
	        }
	    } //close act()
} //close SpiralBug class
