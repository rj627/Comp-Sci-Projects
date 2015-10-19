/**
* comment me completely
*/
public class MyLocation implements Comparable
{
	private int row;
	private int col;

	public MyLocation(int r, int c)
	{
		row = r;
		col = c;
	}

	public int row()
	{
		return row;
	}

	public int col()
	{
		return col;
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof MyLocation))
			return false;
		MyLocation loc = (MyLocation)other;
		return row == loc.row() && col == loc.col();
	}

	public String toString()
	{
		return "(" + row + ", " + col + ")";
	}

	public int compareTo(Object x)
	{
		throw new RuntimeException("Implement Me!");
	}
}