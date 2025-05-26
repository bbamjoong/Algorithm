import java.util.*;

/** 테케
*   3, 5, ["AB.BC", ".DA..", "...DC"], "BACD"
**/

class Solution {
	static int[][] posX, posY;
	static Set<Character> set = new TreeSet();
	static char[][] map;
	static final int SIZE = 'Z' - 'A' + 1;

	public String solution(int m, int n, String[] board) {
		map = new char[m][n];
		for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }

		posX = new int[SIZE][2];
		posY = new int[SIZE][2];

		for (int i = 0; i < SIZE; i++) {
			posX[i][0] = -1;
			posX[i][1] = -1;
			posY[i][0] = -1;
			posY[i][1] = -1;
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				char c = map[i][j];
                if (c-'A' >= 0 && c-'A' <= 'Z'-'A') {
					set.add(board[i].charAt(j));
					if (posX[c - 'A'][0] == -1) posX[c - 'A'][0] = i;
					else posX[c - 'A'][1] = i;
                    
					if (posY[c - 'A'][0] == -1) posY[c - 'A'][0] = j;
					else posY[c - 'A'][1] = j;
				}
			}
		}

        StringBuilder sb = new StringBuilder();
		while (!set.isEmpty()) {
			int size = set.size();
            
            // 제거된 것이 있으면 처음부터 다시.
			for (char c : set) {
				if (check(c)) {
					sb.append(c);
					set.remove(c);
					break;
				}
			}
            // 한 바퀴 돌았는데도 제거된 것이 없다면 종료.
			if (size == set.size())
				break;
		}

		if (set.size() != 0)
			return "IMPOSSIBLE";
        
		return sb.toString();
	}

	static boolean check(char c) {
		int x1 = posX[c - 'A'][0];
		int x2 = posX[c - 'A'][1];
		int y1 = posY[c - 'A'][0];
		int y2 = posY[c - 'A'][1];

		// 대각선 위치. 꺾이는 점으로 갈 수 있어야 한다.
		if (x1 != x2 && y1 != y2) {
			if (canGoCheckX(y2, x1, x2, c) && canGoCheckY(x1, y1, y2, c)) {
				map[x1][y1] = '.';
				map[x2][y2] = '.';
				return true;
			}
			if (canGoCheckX(y1, x1, x2, c) && canGoCheckY(x2, y1, y2, c)){
				map[x1][y1] = '.';
				map[x2][y2] = '.';
				return true;
			}
		}

		// x좌표 동일. y축 검사
		else if (x1 == x2 && y1 != y2) {
			int x = posX[c - 'A'][0];
			if (canGoCheckY(x, y1, y2, c)) {
				map[x1][y1] = '.';
				map[x2][y2] = '.';
				return true;
			}
		}

		// y좌표 동일. x축 검사
		else if (x1 != x2 && y1 == y2) {
			int y = posY[c - 'A'][0];
			if (canGoCheckX(y, x1, x2, c)) {
				map[x1][y1] = '.';
				map[x2][y2] = '.';
				return true;
			}
		}

		return false;
	}

	static boolean canGoCheckY(int baseX, int y1, int y2, char c) {
		int bigger, smaller;
		if (y1 > y2) {
			bigger = y1;
			smaller = y2;
		} else {
			bigger = y2;
			smaller = y1;
		}
		for (int i = smaller; i <= bigger; ++i) {
			if (map[baseX][i] != '.' && map[baseX][i] != c) {
				return false;
			}
		}
		return true;
	}

	static boolean canGoCheckX(int baseY, int x1, int x2, char c) {
		int bigger, smaller;
		if (x1 > x2) {
			bigger = x1;
			smaller = x2;
		} else {
			bigger = x2;
			smaller = x1;
		}
		for (int i = smaller; i <= bigger; ++i) {
			if (map[i][baseY] != '.' && map[i][baseY] != c) {
				return false;
			}
		}
		return true;
	}
}