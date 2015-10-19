package wordGamesLab; //package declaration

import java.util.ArrayList; //import the ArrayList class
import java.util.Iterator; //import the Iterator from Java
import java.util.List; //import the List interface
import java.util.Random; //import a class that makes random numbers

/*
 * This class contains methods that help implement the game of Jotto, in which a player successively tries
 * to guess randomly selected words of increasing length from a given file. It includes a method that displays
 * the index of a given word, a method that finds the index of a given word, a method to generate a random word 
 * from the list with or without the length of the desired word, a method to find the amount of common letters 
 * between two words, and finally, a method that actually plays the game of Jotto, implementing all the other methods.
 */
public class WordGame 
{
	
	private WordGameDisplay display; //instance variable for the GUI of the game
	private List<String> wordsInFile; //load the words in the specified file
	
	/**
	 * Constructor for new objects of class WordGame. It creates a new display where all the action will
	 * take place; it also loads all the words in a 40000-word long file into an ArrayList that will
	 * be used as the source of the words for the games implemented in this class.
	 * @postcondition the wordsInFile instance variable is now filled with actual words
	 * @postcondition there is a new WordGameDisplay created to help play the games implemented in this class
	 */
	public WordGame()
	{
		display = new WordGameDisplay(); //create a new WordGameDisplay
		
		Iterator<String> it = display.loadWords("words.txt"); //return and store an iterator 
		//from the method contained in the class WordGameDisplay()
		
		wordsInFile = new ArrayList<String>(); //store the words in an ArrayList
		
		while (it.hasNext()) //use the iterator
		{
			wordsInFile.add(it.next()); //add words to the ArrayList
		}
		
	} //close constructor

	/**
	 * This method will ask the user to input a word, and then search the file for the word. If the word is found,
	 * using the binary search algorithm, it will return the index of the word in the dictionary; if the word
	 * isn't found, then it will say that "suchandsuch" is not a word. It uses an infinite construct, a while (true) 
	 * loop, to keep running and prompting the user for input.  
	 */
	public void echo()
	{
		//set the title and text for the GUI frame - this is game-specific
		display.setTitle("The Echo Game"); 
		display.setText("Enter a word.");
		
		while (true) //while loop - keep prompting the user for input
		{
			String userInput = display.getGuess(); //stores the user's guess in a variable
			
			if (dictionaryIndex(userInput)==-1) //if the word's not in the dictionary
				display.setText(" \"" + userInput + "\" is not a word.");
			else //if the word is in the dictionary
				display.setText(" \"" + userInput + "\" is word #" + dictionaryIndex(userInput));
		
		} //close while loop
	} //close echo() method
	
	/**
	 * This method uses a binary search algorithm to search the inputted file for the word
	 * that the user would like to find. This runs in O(log(n)) time, making it much
	 * more effective than a similar sequential search algorithm. 
	 * @param wordToSearch is the word that the user wants to find in the dictionary
	 * @return the index of the word in the file that is input; indexing starts at 0
	 */
	public int dictionaryIndex(String wordToSearch) 
	{
		int start = 0; //set a start integer variable to 0, the beginning of the list
		int end = wordsInFile.size()-1; //the end of the list is wordsInFile.size()-1
		
		while (end >= start) //a condition to keep continuing the binary search
		{
			int middle = (start+end)/2; //where should we test first?
			
			int comparedTo = wordsInFile.get(middle).compareTo(wordToSearch); 
			//stores the value of compareTo() in a temp variable
			
			if (comparedTo == 0) //successive iterations will bring 
				//the method to this point, and we can return the value of middle
				return middle;
			else if (comparedTo >0) //if it's before the middle
					end = middle-1;
			else //if it's after the middle, change the start
					start = middle+1;
		} //close while loop
		
		return -1; //if it's not at all in the list
	} //close dictionaryIndex(method)
	
	/**
	 * This method uses the Random class in java.util to generate a random number, which is then mapped
	 * to an index of the array with the words, and then the string at that index is returned to the user; this
	 * is length-independent, and there will not be an IndexOutOfBoundsException, as Random's nextInt() method
	 * sets an upper bound on the random number being generated - so get will not malfunction. 
	 * @return the word at a random index in the inputted file
	 */
	public String getRandomWord()
	{
		return wordsInFile.get(new Random().nextInt(wordsInFile.size()));
		//generate a random number and get the String at that particular index
		
	} //close getRandomWord()
	
	/**
	 * This method is almost exactly the same as above, except it takes in a length and returns a word
	 * of the particular length. If the first word selected is not the desired length, then it keeps picking
	 * random numbers until the word found IS of the desired length. Once again, it won't throw an IndexOutOf-
	 * BoundsException, as the random number is limited to the size of the array. Overloaded method - the pre-
	 * vious getRandomWord() took no parameters, and this one does. 
	 * @param length the length that we want the word to be
	 * @return the random word of the desired length from the array
	 * @throws IllegalArgumentException if the length is negative or 0 - there can't be a word like that!
	 */
	public String getRandomWord(int length)
	{
		if (length <=0) //check if an exception needs to be thrown
			throw new IllegalArgumentException();
		
		String temp = wordsInFile.get(new Random().nextInt(wordsInFile.size())); //find a random word
		
		if (temp.length()==length) //check if it satisfies the parameter length and return it
			return temp;
		else
			return getRandomWord(length); //nope, go back and try something else
	} //close getRandomWord(length) method
	
	/**
	 * This method takes in two words and finds the number of common letters they have. It accomplishes this
	 * through the use of a nested for loop - traversing the second word and the first word - if they have
	 * any same letters, it removes that common letter from the second word and keeps checking that word
	 * against the other one. Because it's a nested for loop, it will run in O(n^2) time. 
	 * @param word1 one of the words to be tested for common letters
	 * @param word2 the other word to be tested for common letters
	 * @return the amount of letters that appear in both words
	 */
	public int commonLetters(String word1, String word2)
	{
		int numOfCommonLetters = 0; //initially, there's 0 common letters
		
		for (int i = 0; i < word1.length(); i++) //traverse the first word
		{
			for (int j = 0; j < word2.length(); j++) //traverse the second word
			{
				if (word1.substring(i,i+1).equals(word2.substring(j,j+1))) //is it a common letter?
				{	
					word2 = word2.substring(0,j)+word2.substring(j+1); //remove that, check the rest
					numOfCommonLetters++; //one more common letter found!
				} //close if
				
			} //close for on the word2
			
		} //close for on the word1
		
		return numOfCommonLetters; //how many common letters?
	} //close commonLetters()
	
	/**
	 * This method implements the game of Jotto. In this game, a random word is selected (starting with length 2), and the user
	 * must guess the random word. If the wrong word is guessed, the WordGameDisplay will show how many letters it has in common; 
	 * if the user guesses a word of the wrong length, it will prompt them to guess again. If a user guesses correctly, they get a 
	 * word one more in length; if they type in pass, they will see the word and have to guess a word of the same length. This uses
	 * the infinite construct while(true) to keep the game running and keep prompting the user for input. 
	 */
	public void jotto()
	{
		int wordLength = 2; //what's the initial word length
		int count = 1; //what is the starting line number
		
		String previouslyGuessed = ""; //nothing's previously guessed at the beginning
		String secretWord = getRandomWord(wordLength); //get a random word of length 2
	
		while (true)
		{
			System.out.println(secretWord);
			String userInput = display.getGuess(); //what the user inputs
			display.setTitle("Jotto" + " (" + wordLength + " words)"); //change the display title to reflect the number of letters
			display.setText("Guess my " + wordLength + "-letter word!"); //remind the user how long the word is
			
			if (userInput.compareTo(secretWord)==0) //guess correctly
			{
				wordLength++; //increment the word length
				previouslyGuessed = previouslyGuessed + count + 
						".\tThat's it!\t Guess my " + (wordLength) + "-letter word.\n"; //prompt them for a longer word
				display.setText(previouslyGuessed); //show all the previous guesses
				count++; //add one to the number of guesses and the line number
				secretWord = getRandomWord(wordLength); //get a new word of the new length
			} //close if
			
			else //incorrect guess
			{
				if (userInput.equals("pass")) //user gives up
				{
					previouslyGuessed = previouslyGuessed  +"\n"  + 
							"The word was \"" + secretWord + "\". Play again!\n\n"; //give them the word, ask them to play again
					display.setText(previouslyGuessed); //show all the line numbers
					secretWord = getRandomWord(wordLength); //get a new word of same length
				} //close if
				
				else //user guesses a different word
				{
					if (userInput.length()!=secretWord.length()) //different length?
					{
						previouslyGuessed = previouslyGuessed + count + 
								"\t" + userInput + "\tmust be " + wordLength + " letters\n"; //remind them of the condition
						display.setText(previouslyGuessed); //show all the line numbers
						count++; //increment line number counter
					} //close if
					
					else //if the word is the desired length but NOT the actual word
					{
						if (dictionaryIndex(userInput)==-1) //it's not an actual word
						{
							previouslyGuessed = previouslyGuessed + count + 
									"\t" + userInput + "\t" + "is not a word.\n"; //tell them that they're being silly
							display.setText(previouslyGuessed); //show all the line numbers
							count++; //increment line number counter
						} //close if
						
						else //if it's a legitimate word, but just the wrong one
						{
							previouslyGuessed = previouslyGuessed + count + "\t" + userInput + 
									"\t" + commonLetters(userInput,secretWord) + "\n"; //tell them how close they were to the actual word
							display.setText(previouslyGuessed); //show all the line numbers
							count++; //increment line number counter
						} //close else
					} //close else
				} //close else
			} //close else
		} //close while
	} //close jotto()
	
	/**
	 * This method provides a menu through which the user can play a game. It compares the guess to the phrase
	 * "echo" or "jotto" and then starts playing the appropriate game. 
	 */
	public void menu()
	{
		display.setTitle("Shall we play a game?"); //set title 
		display.setText("echo\njotto"); //set text

		if (display.getGuess().equals("jotto")) //if they type in jotto
			jotto(); //play the game!
		else
			echo(); //do echo()
	} //close menu()
	
} //close WordGame class
