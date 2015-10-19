
public class Item 
{
	double p, u;
	
	public Item(double price, double utility)
	{
		p = price;
		u = utility;
	}
	
	public double getP()
	{
		return p;
	}
	
	public double getU()
	{
		return u;
	}
	
	public double getE()
	{
		return u/p;
	}
	
}
