import java.util.Scanner;

public class novH 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		double d = sc.nextDouble();
		double k = (double) 1/366;
		double actualProb = Math.pow(k,2);
		
		for (int n = 1; n <= 80; n++)
		{
			
			
		}

	}
	
	private static int factorial(int n)
	{
		if (n == 1 || n == 0) return 1;
		return n * factorial(n-1);
	}
	
	private static int combo(int n, int k)
	{
		return factorial(n)/(factorial(k)*factorial(n-k));
	}

}
