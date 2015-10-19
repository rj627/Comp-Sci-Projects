import java.util.Scanner;

public class novB
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int d = 0;
		int c = 0;
		
		if (k == 1) 
		{
			System.out.print(2);
			return;
		}
		
		while ( d< k)
		{
			c++;
			
			if (isPrime(c))
				d++;
			
		}
		
		System.out.print(c);
	}
	
	public static boolean isPrime(int n)
	{
		return helpPrime(n,2);
	}
	
	private static boolean helpPrime(int n, int div)
	{
		if (n % div == 0 || n == div) 
			return false;
		else if (div > n/2) return true;
		
		return helpPrime(n, div+1);
	}
}


