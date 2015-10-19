import java.util.*;

/*
 * This class models a Card to be used in the game of Solitaire. A card has a rank, a suit, and a boolean
 * saying whether it is faceup that is defaultly initialized to false; i.e. it is facedown upon a call to
 * the constructor. It can get its rank, get its suit, know whether it's red, know whether it's face up,
 * change its orientation, and get the associated file name. 
 * @author Rahul Jayaraman
 * @version 032814
 */
public class Card 
{
	//rank from 1-13 (11, 12, 13 are jack, king, queen; 1 is ace) and suit (c, s, h, d)
	private int rank;
	private String suit;
	private boolean isFaceUp = false; //initially facedown
	
	/**
	 * Two-parameter constructor for a Card that takes in a rank and a suit and assigns them to the
	 * corresponding instance variables.
	 * @param toBeRank is the desired rank of the new card
	 * @param toBeSuit is the desire suit of the new card
	 * @pre - 0 < toBeRank < 14, and toBeSuit is one of the following: "c", "s", "d", "h"
	 * @post creation of a new Card with rank toBeRank and suit toBeSuit
	 */
	public Card(int toBeRank, String toBeSuit)
	{
		rank = toBeRank;
		suit = toBeSuit;
	} //close two-param constructor
	
	/**
	 * This method returns the rank of the card.
	 * @return the value of the instance variable rank
	 */
	public int getRank()
	{
		return rank;
	} //close getRank()
	
	/**
	 * This method returns the suit of the card.
	 * @return the String corresponding to the instance variable suit - "s" means spades, "d" means 
	 * diamonds, "c" means clubs, and "h" means hearts
	 */
	public String getSuit()
	{
		return suit;
	} //close getSuit()
	
	/**
	 * This method determines whether the card is red by checking whether it's a heart or a diamond.
	 * @return false if it's a club or a spade, true if it's a heart or a diamond ("red cards")
	 */
	public boolean isRed()
	{
		return (suit.equals("h") || suit.equals("d"));
	} //close isRed()
	
	/**
	 * This method returns whether the card is face up by returning the isFaceUp instance variable's value.
	 * @return true if the card is faceup, false otherwise
	 */
	public boolean isFaceUp()
	{
		return isFaceUp;
	} //close isFaceUp()
	
	/**
	 * This method turns up the card by changing the instance variable.
	 * @post the card is now faceup
	 */
	public void turnUp()
	{
		isFaceUp = true;
	} //close turnUp()
	
	/**
	 * This method turns the card facedown by changing the instance variable
	 * @post the card is now facedown
	 */
	public void turnDown()
	{
		isFaceUp = false;
	} //close turnDown()
	
	/**
	 * This method returns the filename of a particular card. Each card's image is stored in a cards
	 * directory, and is named in the following way: cards/ + the rank + the suit character + .gif.
	 * However, aces, tens, jacks, queens, and kings are named with the first letter of their name.
	 * Therefore, these cases must be tested as well to return the proper card name. If the card is 
	 * facedown, then we return the image corresponding to the back of the card.
	 * @return the filename of a particular Card object
	 */
	public String getFileName()
	{
		if (!isFaceUp) 
			return "cards/back.gif";
		
		else //it's faceup
		{
			if (rank > 1 && rank < 10)
				return "cards/" + rank + suit + ".gif"; //general case
			
			if (rank == 1) return "cards/a" + suit + ".gif"; //ace
			if (rank == 10) return "cards/t" + suit + ".gif"; //ten
			if (rank == 11) return "cards/j" + suit + ".gif"; //jack
			if (rank == 12) return "cards/q" + suit + ".gif"; //queen
			if (rank == 13) return "cards/k" + suit + ".gif"; //king
			
			return ""; //all the cases have been exhausted, so we return the empty string
			
		} //close else
		
	} //close getFileName()
	
} //close Card class
