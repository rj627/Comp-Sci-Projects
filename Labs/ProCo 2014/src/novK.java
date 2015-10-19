import java.util.Scanner;


public class novK {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int numCols = sc.nextInt();
		int [] cols = new int[numCols];
		int counter = 0;
		int numEmpCols = 0;
		int posA;
		int posB;
		
		while (numEmpCols != 0)
		{
			if (numEmpCols == 1)
			{
				for(int i = 0; i < numCols; i++)
				{
					if (i != 0)
					{
						System.out.print(counter + cols[i]);
					}
				}
			}
			for (posA = 0; posA < numCols; posA++)
			{
				if (posA != 0)
				{
					break;
				}
			}
			for (posB = posA; posB < numCols; posB++)
			{
				if (posB != 0)
				{
					break;
				}
			}
			while (posA <= posB)
			{
				if (cols[posA] == 1)
				{
					numEmpCols++;
				}	
				cols[posA]--;
				posA++;
			}
			
		}
		System.out.println(counter);
	}
}
