import java.util.*;
import java.math.BigDecimal;

/**
 * This class implements the game of solitaire. A solitaire game consists of four underlying data structures,
 * namely two stacks of cards and two arrays of stacks of cards (to represent the stock/waste and the piles/
 * foundations, respectively). The solitaire class can get the cards that are on top of the foundation, pile,
 * waste, or stock; it can also respond to clicks on each of these aspects of the game. Helper methods
 * prepare the game board (deal(), dealThreeCards(), and resetStock()) as well as test whether moves are
 * valid (canAddToPile(), canAddToFoundation()) and then execute them (removeFaceUpCards(), addToPile()). 
 * This uses a display from the SolitaireDisplay class to visualize all these methods and let the user
 * play the "traditional" game of solitaire.
 * @author Rahul Jayaraman
 * @version 032914
 */
public class Solitaire
{
	/**
	 * Runs a new game of solitaire.
	 * @param args - legacy command-line
	 */
	public static void main(String[] args)
	{
		new Solitaire();
		
	} //close main()

	//stacks of cards for the stock and the waste
	private Stack<Card> stock;
	private Stack<Card> waste;
	
	//arrays of stacks for the four foundations and seven piles
	private Stack<Card>[] foundations;
	private Stack<Card>[] piles;
	
	//where all the moes are visualized, as well as where all the cards are displayed
	private SolitaireDisplay display;

	/**
	 * Zero-parameter constructor for a Solitaire object. In the constructor, each of the stacks is 
	 * initialized, and the arrays of foundations and piles are traversed, and each element initialized.
	 * A display is created, as well as a stock, and cards are dealt onto the waste.
	 */
	public Solitaire()
	{
		//declaring each of the Stacks in the foundations array
		foundations = new Stack[4];
		
		for (int i = 0; i < foundations.length; i++)
			foundations[i] = new Stack<Card>();
		
		//declaring each of the Stacks in the piles array
		piles = new Stack[7];
		
		for (int i = 0; i < piles.length; i++)
			piles[i] = new Stack<Card>();
		
		//initializing other instance variables (waste, stock, display)
		waste = new Stack<Card>();
		stock = new Stack<Card>();
		display = new SolitaireDisplay(this);
		
		//prepare the game-board for the first few moves
		createStock();
		deal();
		
	} //close zero-param constructor

	/**
	 * This method returns the card on top of the stock, or null if the stock is empty. We call the isEmpty()
	 * method as well as the peek() method to implement the two cases.
	 * @return the top card on the stock, or null if empty
	 */
	public Card getStockCard()
	{
		if (stock.isEmpty()) 
			return null;
		
		return stock.peek();
	} //close getStockCard()

	/**
	 * This method returns the card on top of the waste, or null if the waste is empty. We call the isEmpty()
	 * method as well as the peek() method to implement the two cases.
	 * @return the top card on the waste, or null if empty
	 */
	public Card getWasteCard()
	{
		if (waste.isEmpty())
			return null;
		
		return waste.peek();
	} //close getWasteCard()

	/**
	 * This method returns the card on top of the particular foundation (the index that is passed as the
	 * parameter) or null if that is empty. We call the isEmpty() method as well as the peek() method to 
	 * implement the two cases.
	 * @param index is the particular foundation that we seek the top card of
	 * @return the top card of the particular foundation, null if it's empty
	 * @pre: 0 <= index < 4
	 */
	public Card getFoundationCard(int index)
	{
		if (foundations[index].isEmpty()) 
			return null;
		
		else return foundations[index].peek();
	} //close getFoundationCard()

	/**
	 * This method returns a reference to a particular pile. We simply use the array operators to perform
	 * this action, as piles is an array, and we are seeking an object at a specific index.
	 * @param index is the particular pile that we want to investigate
	 * @return the pile at the input index
	 */
	public Stack<Card> getPile(int index)
	{
		return piles[index];
	} //close getPile()
	
	/**
	 * This method creates a stock pile consisting of all the cards that are found in a deck. It is called
	 * in the constructor, as it is the first part of the game. All the cards are loaded into an ArrayList,
	 * and random elements of this ArrayList are pushed onto the stock until the ArrayList is empty.
	 * @post the stock is not a randomized collection of 52 cards
	 */
	private void createStock()
	{
		ArrayList<Card> deck = new ArrayList<Card>();
		
		//add each card in a deck to the ArrayList... no jokers!
		for (int i = 1; i <=13; i++)
		{
			deck.add(new Card(i, "c"));
			deck.add(new Card(i, "h"));
			deck.add(new Card(i, "d"));
			deck.add(new Card(i, "s"));
		} //close for loop
		
		//push onto stock till ArrayList is empty using random elements
		while (deck.size() != 0)
		{
			int randIndex = (int) (deck.size() * Math.random());
			stock.push(deck.remove(randIndex));
		} //close while loop
		
	} //close createStock()
	
	/**
	 * This method deals the cards onto the piles in order from the stock. The stock has already been
	 * randomized in the createStock() method, so we just need to push the corresponding number of cards
	 * onto the corresponding pile. We use a nested for loop for this purpose, adding a number of cards
	 * equal to one more than the pile number (0, 1, 2, ... etc gets 1, 2, 3, ... etc cards). We then turn
	 * up the top card of the pile.
	 * @post the piles are filled with the correct number of cards, the stock now has 24 cards left
	 */
	private void deal()
	{
		//keep pushing the top card from the stock into each pile until the "capacity" is reached
		for (int i = 0; i < piles.length; i++)
		{
			for (int j = 0; j < i+1; j++)
			{
				Card card = stock.pop();
				piles[i].push(card);
			} //close nested for
			
			piles[i].peek().turnUp();
		} //close outer for
		
	} //close deal()
	
	/**
	 * This method takes three (or less) cards from the stock and puts them on the waste. While the stock
	 * is not empty and k is not 0 (it starts at 3), cards are pushed from the stock onto the waste, all
	 * the while decrementing k. Once k is 0, or the stock is empty, the transfer of cards stops.
	 * @post the waste now has three (more) cards on it (than it previously had)
	 */
	private void dealThreeCards()
	{
		int k = 3; //number of cards remaining
		
		while (!stock.isEmpty() && k != 0)
		{
			Card card = stock.pop();
			waste.push(card);
			card.turnUp(); 
			k--; //decrement because 1 less card remaining
		} //close while
	} //close dealThreeCards
	
	/**
	 * This method resets the stock. It pushes all the cards onto the waste back onto the stock; it's 
	 * called in the stockClicked() method if the stock is empty. 
	 * @post waste is empty, stock is now filled with waste cards, all turned down
	 */
	private void resetStock()
	{
		while (!waste.isEmpty())
		{
			Card card = waste.pop();
			stock.push(card);
			card.turnDown();
		} //close while loop
		
	} //close resetStock()
	
	/**
	 * This method deals with what happens when the stock pile is clicked. If the waste or a pile is 
	 * selected, it does nothing; if the stock is not empty, it calls dealThreeCards(), or if the stock
	 * is empty and nothing else is selected, it resets the stock.
	 * @post depending on the condition, the stock either loses or gains cards (the waste gains or loses
	 * cards, too, respectively)
	 */
	public void stockClicked()
	{
		if (display.isWasteSelected() || display.isPileSelected())
		{
			return;
		} //close if
		
		else if (!stock.isEmpty())
		{
			dealThreeCards(); //fill waste
		} //close else if
		
		else resetStock();
		
	} //close stockClicked()

	/**
	 * This method deals with what happens whenever the waste is clicked. If it's not empty nor anything
	 * is selected, then the waste is selected. If the waste is selected, it simply deselects the waste.
	 * @post selection or deselection of the waste pile, depending on the conditions
	 */
	public void wasteClicked()
	{
		if (!waste.isEmpty() && !display.isWasteSelected() && !display.isPileSelected())
			display.selectWaste();
		
		else if (display.isWasteSelected())
			display.unselect();
		
		else if (display.isPileSelected()) display.selectWaste();
		
	} //close wasteClicked()

	/**
	 * This method deals with what happens whenever a foundation is clicked. The action depends upon
	 * whether the waste is selected or a pile is selected; if it can be moved to a foundation,
	 * it is moved. Else, nothing is done.
	 * @param index is the foundation that we wish to add to. There is no particular order.
	 * @pre index is between 0 (inclusive) and 4 (exclusive)
	 * @post cards are added to the foundation, if possible
	 */
	public void foundationClicked(int index)
	{
		if (display.isPileSelected() && canAddToFoundation(piles[display.selectedPile()].peek(), index))
		{
			foundations[index].push(piles[display.selectedPile()].pop());
			display.unselect();
		} //close if
		
		else if (display.isWasteSelected() && canAddToFoundation(waste.peek(), index))
		{
			foundations[index].push(waste.pop());
			display.unselect();
		} //close else if
			
	} //close foundationClicked()

	/**
	 * This method deals with what happens whenever a pile is clicked. Here are all the different cases:
	 * 		(1) If the waste is selected and the move is legal, add the top waste card to the pile, unselect.
	 * 			If the move is illegal, then simply select the pile.
	 * 		(2) If a pile isn't selected, nor is a waste, and the card is turned up, select it. If it's not,
	 * 			turn it up (but it does not get selected - only for the purpose of visibility).
	 * 		(3) If the pile at index is already selected, unselect.
	 * 		(4) If the pile selected is a different pile, move all the possible face-up cards from 
	 * 			the previously selected pile to the pile at <code>index</code>, and if stuff has
	 * 			been moved, then turn up the card at the new pile. 
	 * @param index is the pile which has been clicked
	 * @pre index is between 0 and 6 inclusive
	 * @post some change has been done to the pile as described in the cases above
	 */
	public void pileClicked(int index)
	{
		if (display.isWasteSelected())
		{	
			if(canAddToPile(getWasteCard(), index))
			{
				piles[index].push(waste.pop());
				display.unselect();
			}
			
			else display.selectPile(index);
		} //close if
		
		else if (!display.isPileSelected() && !display.isWasteSelected())
		{
			if (piles[index].isEmpty())
				return;
			
			if (piles[index].peek().isFaceUp())
				display.selectPile(index);
			
			else piles[index].peek().turnUp();
		} //close else if
		
		else if (display.selectedPile() == index)
			display.unselect();
		
		else if (display.selectedPile() != index)
		{
			//remove the faceup cards from the old pile and add to the new one till not possible
			int oldIndex = display.selectedPile();
			Stack<Card> faceUp = removeFaceUpCards(oldIndex);
			addToPile(faceUp, index);
			
			//re add the cards remaining
			while(!faceUp.isEmpty())
				piles[oldIndex].push(faceUp.pop());
			
			//turn up and select the new pile
			if (!piles[index].isEmpty())
			{
				piles[index].peek().turnUp();
				display.selectPile(index);
			}
			
		} //close else if
		
	} //close pileClicked()
	
	/**
	 * This method checks if a card can be added to a pile. If the pile is empty and the card is a king,
	 * then it can be added; else, if the card's number is one lower than the one at the top of the pile,
	 * return true. Otherwise, return false - the card cannot be added.
	 * @param card is the card we wish to add to the pile
	 * @param index is the desired index at which we wish to add the card
	 * @return true if we can add to the pile, false otherwise
	 * @pre index is between 0 and 6 inclusive
	 */
	private boolean canAddToPile(Card card, int index)
	{
		//empty case
		if (piles[index].isEmpty())
		{
			if (card.getRank() == 13) //it's a king
				return true;
			
			return false;
		} //close if
		
		else //non-empty case
		{
			Card newCard = piles[index].peek();
			
			if (!newCard.isFaceUp())
				return false; //can't add on top of a facedown card
			
			//card is faceup, compare to existing card, return true if conditions are met
			else if (((!newCard.isRed() && card.isRed()) || (newCard.isRed() && !card.isRed()))
						&& card.getRank() == newCard.getRank() - 1)
					return true;
				
		} //close else
		
		return false;
		
	} //close canAddToPile
	
	/**
	 * This method removes the faceup cards from a given pile. It declares a stack and while the cards
	 * on the top of the pile are faceup, it transfers those cards to the new stack. It then returns
	 * the stack (which stops adding when the pile is empty or the card on top is facedown).
	 * @param index is the pile where we want to remove the cards from
	 * @return the stack of removed face up cards from that pile
	 * @pre index is between 0 and 6, inclusive
	 */
	private Stack<Card> removeFaceUpCards(int index)
	{
		Stack<Card> faceUpCards = new Stack<Card>();
	
		while (!piles[index].isEmpty() && piles[index].peek().isFaceUp())
		{
			faceUpCards.push(piles[index].pop()); //add to the declared stack, remove from piles[index]
		} //close while
		
		return faceUpCards;
		
	} //close removeFaceUpCards()
	
	/**
	 * This method adds a stack of cards to a pile. It keeps pushing the top element of cards onto the
	 * pile until the move is illegal or the parameter stack is empty.
	 * @param cards is the stack of cards that we wish to add to the pile from
	 * @param index is the pile that we want to add to
	 * @pre index is between 0 and 6, inclusive
	 * @post all elements of cards that can be added legally to piles[index] have been added
	 */
	private void addToPile(Stack<Card> cards, int index)
	{
		while (!cards.isEmpty() && canAddToPile(cards.peek(), index))
		{
			piles[index].push(cards.pop());
		} //close while
		
	} //close addToPile
	
	/**
	 * This method tests whether a card can legally be added to a given foundation. If the foundation is
	 * empty and the card is an ace, it can be added; if the foundation is nonempty, the card <i> must </i>
	 * have a rank one higher than the card currently at the top of the foundation. Return true if these
	 * conditions are met, otherwise return false (it cannot be added).
	 * @param card is the card that we wish to add to the foundation
	 * @param index is the pile which we wish to add the card to
	 * @return true if addition is legal, false otherwise
	 * @pre index is between 0 and 6, inclusive
	 */
	private boolean canAddToFoundation(Card card, int index)
	{
		if (foundations[index].isEmpty() && card.getRank() == 1) //is it an ace?
			return true;
		
		//test for proper rank
		else if (!foundations[index].isEmpty() && 
				foundations[index].peek().getSuit().equals(card.getSuit()) && 
				foundations[index].peek().getRank() == card.getRank() - 1) return true;
		
		return false;
		
	} //close canAddToFoundation()
	
} //close Solitaire class