import java.util.Scanner;

public class novE 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int col = sc.nextInt();
		String str = sc.next();
		int i = 0;
		
		String[][] arr = new String[row][col];
		
		for (int c = 0; c < col; c++)
		{
			for (int r = 0; r < row; r++)
			{
				arr[r][c] = str.substring(i,i+1);
				i++;
			}
		}
		
		String out = "";
		
		for (int r = 0; r < row; r++)
		{
			for (int c = 0; c < col; c++)
			{
				out+=arr[r][c];
			}
		}
		
		System.out.print(out);

	}

}
