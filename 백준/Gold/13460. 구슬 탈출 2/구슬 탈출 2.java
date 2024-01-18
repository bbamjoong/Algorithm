import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int rx1 = 0;
		int ry1 = 0;
		int bx1 = 0;
		int by1 = 0;

		char[][] arr = new char[n][m];
		for (int i = 0; i < n; i++) {
			String word = br.readLine();
			for (int j = 0; j < m; j++) {
				char c = word.charAt(j);
				arr[i][j] = c;

				if (c == 'R') {
					rx1 = i;
					ry1 = j;
				}
				if (c == 'B') {
					bx1 = i;
					by1 = j;
				}
			}
		}

		Queue<int[]> q = new LinkedList<>();
		List<int[]> visited = new ArrayList<>();

		q.offer(new int[] { rx1, ry1, bx1, by1, 0 });
		visited.add(new int[] { rx1, ry1, bx1, by1 });
		
		int rx, ry, bx, by, cnt;
		int ans = -1;

		while (!q.isEmpty()) {
			int[] qPoll = q.poll();
			rx = qPoll[0];
			ry = qPoll[1];
			bx = qPoll[2];
			by = qPoll[3];
			cnt = qPoll[4];

			if (arr[rx][ry] == 'O') {
				ans = cnt;
				break;
			}
			
			if (cnt > 10) {
				ans = -1;
				break;
			}
			
			for (int i = 0; i < dx.length; i++) {
				int nrx = rx + dx[i];
				int nry = ry + dy[i];
				int rmove = 1;

				while (true) {
					if (arr[nrx][nry] == '#') {
						nrx -= dx[i];
						nry -= dy[i];
						break;
					}

					else if (arr[nrx][nry] == 'O') {
						break;
					}

					nrx += dx[i];
					nry += dy[i];
					rmove++;
				}

				int nbx = bx + dx[i];
				int nby = by + dy[i];
				int bmove = 1;

				while (true) {
					if (arr[nbx][nby] == '#') {
						nbx -= dx[i];
						nby -= dy[i];
						break;
					}

					else if (arr[nbx][nby] == 'O') {
						break;
					}

					nbx += dx[i];
					nby += dy[i];
					bmove++;
				}

				if (arr[nbx][nby] == 'O') {
					continue;
				}
				
				if (nrx == nbx & nry == nby) {
					if (rmove > bmove) {
						nrx -= dx[i];
						nry -= dy[i];
					} else {
						nbx -= dx[i];
						nby -= dy[i];
					}
				}

				boolean isExist = false;
				for (int j = 0; j < visited.size(); j++) {
					if (Arrays.equals(new int[] { nrx, nry, nbx, nby }, visited.get(j))) {
						isExist = true;
						break;
					}
				}

				if (!isExist) {
					q.offer(new int[] { nrx, nry, nbx, nby, cnt + 1 });
					visited.add(new int[] { nrx, nry, nbx, nby });
				}
			}
		}
		sb.append(ans);
		System.out.println(sb.toString());
	}
}