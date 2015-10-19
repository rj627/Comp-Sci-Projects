import java.util.*;

public class novJ 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		
		ArrayList<Dwarf> dwarf = new ArrayList<Dwarf>();
		
		for (int i = 0; i < n; i++)
		{
			int num = sc.nextInt();
			String dir = sc.next();
			Dwarf d = new Dwarf(num, dir);
			dwarf.add(d);
		}
		for (int time = 0; time < t; time++)
		{
			for (int b = 0; b < n; b++)
			{
				if (dwarf.get(b).getLocation()== 0 && dwarf.get(b).getDirection().equals("-"))
				{
					dwarf.get(b).setDirection("+");
				}
				
				else
				{
					if (dwarf.get(b).getDirection().equals("+"))
					{
						dwarf.get(b).setLocation(dwarf.get(b).getLocation()+1);
					}
				
					else if (dwarf.get(b).getDirection().equals("-"))
					{
						dwarf.get(b).setLocation(dwarf.get(b).getLocation()-1);
					}
				}
			}
				for (int j = 0; j < dwarf.size(); j++)
				{
					for (int k = dwarf.size()-1; k > j; k--)
					{
						if (dwarf.get(j).getLocation() == dwarf.get(k).getLocation())
						{
							String strj = dwarf.get(j).getDirection();
							String strk = dwarf.get(k).getDirection();
							
							if (strj.equals("+")) 
							{
								dwarf.get(j).setDirection("-");
							}
							
							else if (strj.equals("-"))
							{
								dwarf.get(j).setDirection("+");
							}
							
							if (strk.equals("+")) 
							{
								dwarf.get(k).setDirection("-");
							}
							
							else if (strk.equals("-"))
							{
								dwarf.get(k).setDirection("+");
							}
						}
					}
				
			}
		
		
		}
		
		for (int z = 0; z < dwarf.size(); z++)
		{
			System.out.println(dwarf.get(z).toString());
		}
	}

}


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


