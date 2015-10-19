import java.io.*;
import java.util.*;

public class bug3 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		String[] str = new String[n];
		for (int i = 0; i < n; i++)
			str[i] = in.readLine();
		
		Arrays.sort(str, new Comp());
		
		for (String s : str)
			System.out.println(s);
	}
	
	private static class Comp implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			int ind1 = findNumberSuffix(o1);
			int ind2 = findNumberSuffix(o2);
			return (new Integer(Integer.parseInt(o1.substring(ind1)))).compareTo(new Integer(Integer.parseInt(o2.substring(ind2))));
		}
		
		public int findNumberSuffix(String s) {
			for (int i=s.length(); i>0; i--)
				if (!Character.isDigit(s.charAt(i-1)))
					return i;
			return 0;
		}
	}
}