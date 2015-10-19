import java.awt.Color;
import java.util.Iterator;

public class Game
{
	public static void main(String[] args)
	{
		Board board = new Board();

	
		Piece blackKing = new King(Color.BLACK, "black_king.gif"); 
		blackKing.putSelfInGrid(board, new Location(0, 4)); 
		
		Piece whiteKing = new King(Color.WHITE, "white_king.gif"); 
		whiteKing.putSelfInGrid(board, new Location(7, 4));
		
		
		
		BoardDisplay display = new BoardDisplay(board);
	
	}
}