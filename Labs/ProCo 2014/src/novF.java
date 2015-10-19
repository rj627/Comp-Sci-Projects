import java.util.ArrayList;
import java.util.Scanner;


public class novF {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		double b = sc.nextDouble();
		double price = 0;
		int n = sc.nextInt();
		double util = 0;
		ArrayList<Item> items = new ArrayList<Item>();
		for(int i = 0; i < n; i++)
		{
			items.add(new Item(sc.nextDouble(), sc.nextDouble()));
		}
		while(b < price && items.size() > 0)
		{
			int index = 0;
			Item max = items.get(0);
			for (int i = 0; i < items.size(); i++)
			{
				if (max.getE() < items.get(i).getE()){
					max = items.get(i);
					index = i;
				}
			}
			price += max.getP(); util += max.getU(); items.remove(index);
		}
		System.out.print(util);
			}
		
		
	}


