import java.io.*;
import java.util.*;

public class bug4 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(in.readLine());
		int[][] intervals = new int[num][2];
		for (int i=0; i<num; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			intervals[i][0] = Integer.parseInt(st.nextToken());
			intervals[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int size = 0;
		while(intervals.length > 0) {
			int point = findBest(intervals);
			intervals = removeOverlapping(point, intervals);
			size++;
		}
		System.out.println(size);
	}

	private static int findBest(int[][] intervals) {
		int best = intervals[0][1];
		for (int i=1; i<intervals.length; i++) {
			if (removeOverlapping(intervals[i][1], intervals).length < removeOverlapping(best, intervals).length)
				best = intervals[i][1];
		}
		return best;
	}

	private static int[][] removeOverlapping(int point, int[][] intervals) {
		ArrayList<int[]> remaining = new ArrayList<int[]>();
		for (int i=0; i<intervals.length; i++)
			if (intervals[i][0] > point || intervals[i][1] < point)
				remaining.add(intervals[i]);
		
		int[][] ret = new int[remaining.size()][2];
		remaining.toArray(ret);
		return ret;
	}
}
