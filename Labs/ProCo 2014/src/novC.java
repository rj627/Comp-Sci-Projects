import java.util.Scanner;
public class novC {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String s= sc.nextLine();
		
		for (int i = 0; i < s.length(); i++)
		{
			if (s.substring(i,i+1).equals("&"))
			{
				s = s.substring(0,i) + "&amp;" + s.substring(i+1);
			}
		}
		
		System.out.print(s);

	}

}
