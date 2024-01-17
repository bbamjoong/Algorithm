import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<String>[] ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		int n = (int) (Math.pow(2, k) - 1);
		String[] arr = br.readLine().split(" ");
		
		ans = new ArrayList[k];
		for (int i = 0; i < k; i++) {
			ans[i] = new ArrayList<>();
		}
		
		calculate(0, n-1, arr, 0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ans.length; i++) {
			sb.setLength(0);
			for (int j = 0; j < ans[i].size(); j++) {
				sb.append(ans[i].get(j) + " ");
			}
			sb.append("\n");
			System.out.print(sb.toString());
		}
	}
	static void calculate(int start, int end, String[] arr, int depth) {
		if (start == end) {
			ans[depth].add(arr[start]);
			return;
		}
		
		int mid = (start + end) / 2;
		ans[depth].add(arr[mid]);
		
		calculate(start, mid - 1, arr, depth + 1);
		calculate(mid + 1, end, arr, depth + 1);
	}
}