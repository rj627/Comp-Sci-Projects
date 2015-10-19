
public class Dwarf 
{
	private int loc;
	private String dir;
	
	public Dwarf(int l, String d)
	{
		loc = l;
		dir = d;
	}
	
	public int getLocation()
	{
		return loc;
	}
	
	public String getDirection()
	{
		return dir;
	}
	
	public void setLocation(int k)
	{
		loc = k;
	}
	
	public void setDirection(String d)
	{
		dir = d;
	}
	
	public String toString()
	{
		return loc + " " + dir;
	}
}
