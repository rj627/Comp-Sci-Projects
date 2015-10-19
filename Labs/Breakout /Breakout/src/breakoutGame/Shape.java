package breakoutGame;

import java.awt.Color;

public class Shape {
	
	boolean isRound;
	Color color;
	double x;
	double y;
	double width;
	double height;

	
	public Shape(boolean isRound, Color color, double x, double y,
			double width, double height)
	{
		
	}
	
	public boolean isRound()
	{
		return true;
	}
	
	public Color getColor() 
	{
		return color;
	}
	
	public double getX() 
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getWidth() 
	{
		return width;
	}
	
	public double getHeight()
	{
		return height;
	}
}
