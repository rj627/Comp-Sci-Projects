import java.util.Scanner;

public class novG 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		
		
		
	}
	
	private void sumString(String s)
	{
		int k = s.indexOf("+");
		int j = s.indexOf("-");
		
		if (j == -1)
		{
			String sub = s.substring(0, j);
			s = s.substring(j+1);
		}
		
		if (k  == -1)
		{
			String sub = s.substring(0, k);
			s = s.substring(k+1);
		}
		
		if (j > k)
		{
			String sub = s.substring(0, k);
			s = s.substring(k+1);
		}
		
		if (j < k)
		{
			String sub = s.substring(0, j);
			s = s.substring(j+1);
		}
	}
}
