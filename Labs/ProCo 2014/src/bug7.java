import java.io.*;
import java.util.*;

public class bug7 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] weights = new int[n];
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<n; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
			adjList.add(new ArrayList<Integer>());
		}
		
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(in.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adjList.get(n1).add(n2);
			adjList.get(n2).add(n1);
		}
		
		int[][] dp = new int[n][2];
		dfs(0,-1,dp,weights, adjList);
		System.out.println(dp[0][0]);
	}
	
	public static void dfs(int node, int parent, int[][] dp, int[] weights, ArrayList<ArrayList<Integer>> adjList) {
		dp[node][1] = weights[node];
		for (int child : adjList.get(node)) { 
			if (child != parent) {
				dfs(child, node, dp, weights, adjList);
				dp[node][0] += dp[child][1];
				dp[node][1] += dp[child][0];
			}
		}
	}
}
