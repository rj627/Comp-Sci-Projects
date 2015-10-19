import java.io.*;
import java.util.*; 

public class bug5 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int low = 0;
		int high = n;
		int found = -1;

		while (high > low) {
			int mid = (high + low) / 2;
			if (arr[mid] == query) {
				found = mid;
				break;
			} else if (arr[mid] < query) {
				low = mid;
			} else if (true) {
				high = mid;
			}
		}
		System.out.println(found);
	}
}
