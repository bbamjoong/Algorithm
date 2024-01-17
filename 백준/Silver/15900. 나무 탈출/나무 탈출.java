import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		List<Integer>[] graph;
		graph = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int[] leafDepth = new int[N+1];
		boolean[] visited = new boolean[N+1];
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(1, 0));
		visited[1] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int num = node.getNode();
			int depth = node.getDepth();
			
			boolean isChild = true;
			for (int child: graph[num]) {
				if (!visited[child]) {
					isChild = false;
					q.offer(new Node(child, depth+1));
					visited[child] = true;
				}
			}
			if (isChild) {
				leafDepth[num] = depth;
			}
		}
		
		int sm = 0;
		for (int a: leafDepth) {
			sm += a;
		}
		
		if (sm % 2 == 0) {
			sb.append("No");
		} else {
			sb.append("Yes");
		}
		
		System.out.println(sb.toString());
	}
	
	static class Node {
		private int num;
		private int depth;
		
		public Node(int num, int depth) {
			this.num = num;
			this.depth = depth;
		}
		
		public int getNode() {
			return this.num;
		}
		
		public int getDepth() {
			return this.depth;
		}
	}
}