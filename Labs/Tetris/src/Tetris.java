

/*
 * This class implements the traditional game of Tetris, invented in Soviet Russia and then
 * brought over to the United States. It involves falling blocks that are able to be rotated
 * and translated; when a row is complete, it disappears and everything else is shifted down. 
 * Methods that this class implements include a zero-parameter constructor, methods that respond
 * accordingly when each of the arrow keys are pressed, a method that slowly moves the block
 * down, a method that checks whether a row is complete, a method that clears a row, and a method
 * that clears completed rows. In order to implement the key-press methods, the Tetris class 
 * inherits the methods from the ArrowListener interface - usable in a wide variety of cases. 
 * @author Rahul Jayaraman
 * @version 011613
 */
public class Tetris implements ArrowListener
{
	//define instance variables for current grid, display, and tetrad
	
	private MyBoundedGrid<Block> currentGrid; 
	private BlockDisplay currentDisplay; 
	private Tetrad currentTetrad; 
	
	/**
	 * Zero-parameter constructor for a new Tetris object. It creates a 20x10 grid, a new
	 * display, sets the title to Tetris, sets the ArrowListener to this class itself, makes
	 * a new Tetrad (using the tetrad constructor), and updates the display. 
	 * @post a new Tetris object is created, and methods can be called on it
	 */
	public Tetris()
	{
		//set grid and display with appropriate instance fields(create a new one)
		currentGrid = new MyBoundedGrid<Block>(20,10); 
		currentDisplay = new BlockDisplay(currentGrid);
		currentDisplay.setTitle("Tetris");
		currentDisplay.setArrowListener(this);
		
		//creates the actual meat of the game - the "tetrad" - set of four blocks
		currentTetrad = new Tetrad(currentGrid);
		currentDisplay.showBlocks();
		
	} //close constructor

	/**
	 * This is the implementation of the ArrowListener's upPressed() method. It is dealt
	 * with in the BlockDisplay class; we just need to decide what happens AFTER the up key
	 * is pressed. If the tetrad can be rotated, it will be rotated; the display will update.
	 * @post the Tetrad is rotated 90 degrees clockwise
	 */
	public void upPressed() 
	{
		//test for rotation and update
		
		if (currentTetrad.rotate())
			currentDisplay.showBlocks();
		
	} //close upPressed()

	/**This is the implementation of the ArrowListener's downPressed() method. It is dealt
	 * with in the BlockDisplay class; we just need to decide what happens AFTER the down key
	 * is pressed. If the tetrad can be translated down, it will be moved; the display will update.
	 * @post the Tetrad is moved down one space
	 */
	public void downPressed() 
	{
		//test for translation down and update
		
		if (currentTetrad.translate(1, 0))
			currentDisplay.showBlocks(); 
		
	} //close downPressed()

	/**This is the implementation of the ArrowListener's leftPressed() method. It is dealt
	 * with in the BlockDisplay class; we just need to decide what happens AFTER the left key
	 * is pressed. If the tetrad can be translated left, it will be moved; the display will update.
	 * @post the Tetrad is moved left one space
	 */
	public void leftPressed() 
	{
		//test for translation left and update
		
		if (currentTetrad.translate(0, -1))
			currentDisplay.showBlocks(); 
		
	} //close leftPressed()

	/**This is the implementation of the ArrowListener's rightPressed() method. It is dealt
	 * with in the BlockDisplay class; we just need to decide what happens AFTER the right key
	 * is pressed. If the tetrad can be translated right, it will be moved; the display will update.
	 * @post the Tetrad is moved right one space
	 */
	public void rightPressed() 
	{
		//test for translation left and update
		
		if (currentTetrad.translate(0, 1))
			currentDisplay.showBlocks();
		
	} //close rightPressed()
	
	/**
	 * This method "plays" a game of Tetris. In it, a while (true) construct is used in order
	 * to continuously move the active tetrad down, one space per 1000 milliseconds. If it
	 * can be translated (the tetrad), it is translated; else the completed rows (if any) are 
	 * cleared, and a new tetrad is created that will start to drop. The try-catch blocks
	 * repeatedly pause the thread, move the tetrad, and restart the thread. There is nothing
	 * to do if exceptions are thrown - because with the way this code works, it shouldn't.
	 * @post the display keeps changing, as do the associated instance variables - new tetrads
	 * are created and assigned, rows disappear, and the tetrad's location keeps changing
	 */
	public void play()
	{
		try
		{
			while (true) 
			{
				if (currentTetrad.translate(1,0))
				{
					//after moving the blocks, pause the thread for 1000 milliseconds, update
					
					Thread.sleep(1000);
					currentDisplay.showBlocks();
					
				} //close if
				
				else 
				{
					//clear all completed rows and make new active tetrad
					
					clearCompletedRows(); 
					currentDisplay.showBlocks();
					currentTetrad = new Tetrad(currentGrid); 
					currentDisplay.showBlocks();
					
				} //close else
				
			} //close while
			
		} //close try
		
		catch(InterruptedException e)
		{
			
		} //close catch
		
	} //close play()
	
	/**
	 * This method checks whether a row is fully occupied; i.e. if all the locations with row
	 * number equal to the parameter passed are occupied. It does this by checking if any of
	 * the locations are null; if they are, it returns false. If not, and the for loop is
	 * executed fully, it returns true - the row is, indeed, completely occupied.
	 * @param row is the row that we want to check for completion
	 * @return true if the row is fully occupied, false otherwise
	 * @throws IllegalArgumentException if the row if out of the grid's bounds
	 */
	private boolean isCompletedRow(int row)
	{
		if (row < 0 || row > currentGrid.getNumRows()) throw new IllegalArgumentException();
		
		//cycle through each column in the row and check if there's something there
		
		for (int i = 0; i < currentGrid.getNumCols(); i++)
		{
			Location tested = new Location(row, i); 
			
			if (currentGrid.get(tested) == null) 
			{
				return false; 
			} //close if
			
		} //close for
		
		return true; //if at all locations there is something there
		
	} //close isCompletedRow()
	
	/**
	 * This method clears a completed row. First, it clears the individual row, then
	 * it moves each block above the row down a space. It accomplishes this through
	 * the use of a nested for loop - this traverses the grid (or the section of the grid
	 * that is above the row passed as a parameter). It also goes up (rows), rather than down,
	 * to prevent block overlap, which could cause a whole host of problems. 
	 * @param row is the row that we want to remove all the blocks from
	 * @post the row is now empty of all blocks and is filled with blocks from above
	 * @throws IllegalArgumentException if the row is not valid for the current grid 
	 */
	private void clearRow(int row)
    {
		if (row < 0 || row > currentGrid.getNumRows()) throw new IllegalArgumentException();
		
    	for(int i=row; i>=0; i--) //nested for loops to traverse the entire 2-D grid
    	{
    		for(int j=0; j<currentGrid.getNumCols(); j++)
    		{
    			if(i==row)
    			{
    				currentGrid.get(new Location(row,j)).removeSelfFromGrid(); //remove everything from the row
    				//passed as a parameter
    			}
    			
    			else 
    			{
    				//declare a block and move it down, as long as it exists
    				
    				Block toMove = currentGrid.get(new Location(i,j)); 
    				
    				if(toMove !=null) 
    				{
    					toMove.moveTo(new Location(i+1,j));
    					
    				} //close if
    				
    			} //close else
    			
    		} //close nested for
    		
    	} //close outside for
    	
    } //close clearRows()
	
	/**
	 * This method clears all the completed rows in the associated grid. It accomplishes this
	 * by declaring a for loop that performs the clearRow() method on each row (remember: it 
	 * only removes if the whole row is occupied by blocks) and redraws the grid.
	 * @post all the completed rows are cleared from the grid
	 */
	private void clearCompletedRows()
	{
		for (int i = 0; i < currentGrid.getNumRows(); i++)
		{
			//test and clear
			
			if (isCompletedRow(i)) 
				clearRow(i);
			
		} //close for loop
		
	} //close clearCompletedRows()
	
} //close Tetris class
