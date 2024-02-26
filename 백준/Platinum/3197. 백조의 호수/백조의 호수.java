import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int r;
	static int c;
	static char[][] arr;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static int startX;
	static int startY;

	static int endX;
	static int endY;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new char[r][c];
		Queue<int[]> wq = new ArrayDeque<>(); // 물 Queue

		boolean isFirst = true;
		for (int i = 0; i < r; i++) {
			String word = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = word.charAt(j);

				if (arr[i][j] == 'L' && isFirst) { // 사람 위치 저장
					startX = i;
					startY = j;
					isFirst = false;
					wq.add(new int[] {i, j});
				} else if (arr[i][j] == 'L' && !isFirst) {
					endX = i;
					endY = j;
					wq.add(new int[] {i, j});
				} else if (arr[i][j] == '.') {
					wq.add(new int[] { i, j });
				}
			}
		}

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[r][c];

		q.add(new int[] { startX, startY });
		visited[startX][startY] = true;

		boolean canFinish = false;

		int cnt = 0;
		while (true) {
			// 서로 만날 수 있는지 확인
			Queue<int[]> nextQ = new ArrayDeque<>();
			while (!q.isEmpty()) {
				int[] pos = q.poll();
				int x = pos[0];
				int y = pos[1];

				if (x == endX && y == endY) {
					canFinish = true;
					break;
				}
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					// 범위 벗어나면 X
					if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
						continue;
					}

					// 이미 방문했으면 X
					if (visited[nx][ny]) {
						continue;
					}

					if (arr[nx][ny] == '.' || arr[nx][ny] == 'L') {
						visited[nx][ny] = true;
						q.add(new int[] { nx, ny });
					}

					// 얼음이라서 다음에 백조가 탐색할거다
					else if (arr[nx][ny] == 'X') {
						visited[nx][ny] = true;
						nextQ.add(new int[] { nx, ny });
					}
				}
			}

			q = nextQ;

			if (canFinish) {
				break;
			}

			/////////////////////////////////////////////////////
			int size = wq.size();
			for (int i = 0; i < size; i++) {
				int[] p = wq.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nx = p[0] + dx[dir];
					int ny = p[1] + dy[dir];

					if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
						continue;
					}

					if (arr[nx][ny] == 'X') {
						arr[nx][ny] = '.';
						wq.add(new int[] { nx, ny });
					}
				}
			}

			cnt++;
		}

		System.out.println(cnt);
	}
}
