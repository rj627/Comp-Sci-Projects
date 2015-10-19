import java.io.*;
import java.util.*;

public class bug6 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int numCuts = Integer.parseInt(st.nextToken());
		int angleDelta = Integer.parseInt(st.nextToken());
		
		int[] cuts = new int[numCuts];
		cuts[0] = 0;
		for (int i=1; i<numCuts; i++)
			cuts[i] = (cuts[i-1] + angleDelta) % 360;
		Arrays.sort(cuts);

		int biggest = 0;
		for (int i=0; i<cuts.length; i++) {
			int size = cuts[(i+1)%cuts.length] - cuts[i];
			if (size < 0) size += 360;
			biggest = Math.max(biggest, size);
		}
		
		System.out.println(biggest);
	}
}
