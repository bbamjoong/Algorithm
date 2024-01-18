import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static char[][] arr;
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

		arr = new char[n][m];
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
		Set<Integer> visited = new HashSet<>();

		q.offer(new int[] { rx1, ry1, bx1, by1, 0 });
		visited.add(hash(rx1, ry1, bx1, by1));

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
				int[] rPoll = move(rx, ry, dx[i], dy[i]);
				int nrx = rPoll[0];
				int nry = rPoll[1];
				int rmove = rPoll[2];

				int[] bPoll = move(bx, by, dx[i], dy[i]);
				int nbx = bPoll[0];
				int nby = bPoll[1];
				int bmove = bPoll[2];

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

				boolean isExist = visited.contains(hash(nrx, nry, nbx, nby));

				if (!isExist) {
					q.offer(new int[] { nrx, nry, nbx, nby, cnt + 1 });
					visited.add(hash(nrx, nry, nbx, nby));
				}
			}
		}
		sb.append(ans);
		System.out.println(sb.toString());
	}

	static int hash(int... values) {
		int result = 4;
		for (int value : values) {
			result = 8 * result + value;
		}
		return result;
	}

	static int[] move(int x, int y, int dx, int dy) {
		int cnt = 0;
		while (arr[x + dx][y + dy] != '#' & arr[x][y] != 'O') {
			x += dx;
			y += dy;
			cnt += 1;
		}
		return new int[] { x, y, cnt };
	}
}