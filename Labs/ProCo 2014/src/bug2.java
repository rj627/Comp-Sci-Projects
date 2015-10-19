import java.io.*;
import java.util.*;

public class bug2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		Queue<Integer> prev = new LinkedList<Integer>();
		prev.add(1);
		prev.add(1);
		for (int i = 0; i < n-1; i++)
			prev.add(prev.poll() + prev.peek());
		System.out.println(prev.peek());
	}
}
