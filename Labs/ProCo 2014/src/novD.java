import java.util.Scanner;

public class novD {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		
		
		for (int i = 1; i < (k+1)/2; i++)
		{
			for (int j = 0; j < i; j++)
			{
				System.out.print("x");
			}
			for (int a = 0; a < k-2*i;a++)
			{
				System.out.print(".");
			}
			for (int j = 0; j < i - 1; j++)
			{
				System.out.print("x");
			}
			System.out.println("x");

	}
		for (int y = 0; y < k; y++)
		{
			System.out.print("x");
		}

}
}
