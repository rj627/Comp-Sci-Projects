import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class King extends Piece
{
	private Board board;

	private Location location;
	
	private Color color;

	private String imageFileName;

	public static final int VALUE_OF_KING = 1000;
	
	public King(Color col, String fileName)
	{
		super(col,fileName,VALUE_OF_KING);
	}

	public Iterator<Location> destinations() 
	{
		Set<Location> moveLocs = new TreeSet<Location>();
		for (int r = getLocation().getRow()-1; r <= getLocation().getRow()+1; r++)
		{
			for (int c = getLocation().getCol() - 1; c <= getLocation().getRow() + 1; c++)
			{
				Location loc = new Location(r, c);
				if (isValidDestination(loc))
					moveLocs.add(loc);
			}
		}
		
		return moveLocs.iterator();
	}
}
