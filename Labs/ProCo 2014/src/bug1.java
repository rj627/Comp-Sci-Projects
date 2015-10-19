import java.io.*;
import java.util.*;

public class bug1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numLevels = Integer.parseInt(in.readLine());
		for (int i=0; i<numLevels; i++) {
			for (int j=0; j<=i; j++) {
				System.out.print('*');
			}
			System.out.println();
		}
	}
}
