import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] arr;
	static int ans = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		// 배열 값 갱신
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(arr, 0); // 백트래킹 시작

		sb.append(ans);
		System.out.println(sb.toString());
	}

	static void dfs(int[][] arr, int cnt) {
		if (cnt == 5) {
			int value = findMax(arr);
			if (ans < value) {
				ans = value;
			}
			return;
		}

		dfs(goLeft(arr), cnt + 1);
		dfs(goRight(arr), cnt + 1);
		dfs(goUp(arr), cnt + 1);
		dfs(goDown(arr), cnt + 1);
	}

	static int[][] goLeft(int[][] arr) {
		int[][] newArr = cloneArr(arr);

		for (int i = 0; i < n; i++) {
			int mainPointer = 0;
			for (int j = mainPointer + 1; j < n; j++) {
				if (newArr[i][j] != 0) {
					if (newArr[i][mainPointer] == 0) {
						newArr[i][mainPointer] = newArr[i][j];
						newArr[i][j] = 0;
					} else if (newArr[i][mainPointer] == newArr[i][j]) {
						newArr[i][mainPointer] *= 2;
						newArr[i][j] = 0;
						mainPointer++;

					} else {
						mainPointer++;
						int tmp = newArr[i][mainPointer];
						newArr[i][mainPointer] = newArr[i][j];
						newArr[i][j] = tmp;
					}
				}
			}
		}
		return newArr;
	}

	static int[][] goRight(int[][] arr) {
		int[][] newArr = cloneArr(arr);

		for (int i = n - 1; i > -1; i--) {
			int mainPointer = n - 1;
			for (int j = mainPointer - 1; j > -1; j--) {
				if (newArr[i][j] != 0) {
					if (newArr[i][mainPointer] == 0) {
						newArr[i][mainPointer] = newArr[i][j];
						newArr[i][j] = 0;
					} else if (newArr[i][mainPointer] == newArr[i][j]) {
						newArr[i][mainPointer] *= 2;
						newArr[i][j] = 0;
						mainPointer--;
					} else {
						mainPointer--;
						int tmp = newArr[i][mainPointer];
						newArr[i][mainPointer] = newArr[i][j];
						newArr[i][j] = tmp;
					}
				}
			}
		}

		return newArr;
	}

	static int[][] goUp(int[][] arr) {
		int[][] newArr = cloneArr(arr);

		for (int j = 0; j < n; j++) {
			int mainPointer = 0;
			for (int i = mainPointer + 1; i < n; i++) {
				if (newArr[i][j] != 0) {
					if (newArr[mainPointer][j] == 0) {
						newArr[mainPointer][j] = newArr[i][j];
						newArr[i][j] = 0;
					} else if (newArr[mainPointer][j] == newArr[i][j]) {
						newArr[mainPointer][j] *= 2;
						newArr[i][j] = 0;
						mainPointer++;
					} else {
						mainPointer++;
						int tmp = newArr[mainPointer][j];
						newArr[mainPointer][j] = newArr[i][j];
						newArr[i][j] = tmp;
					}
				}
			}
		}

		return newArr;
	}

	static int[][] goDown(int[][] arr) {
		int[][] newArr = cloneArr(arr);

		for (int j = n - 1; j > -1; j--) {
			int mainPointer = n - 1;
			for (int i = mainPointer - 1; i > -1; i--) {
				if (newArr[i][j] != 0) {
					if (newArr[mainPointer][j] == 0) {
						newArr[mainPointer][j] = newArr[i][j];
						newArr[i][j] = 0;
					} else if (newArr[mainPointer][j] == newArr[i][j]) {
						newArr[mainPointer][j] *= 2;
						newArr[i][j] = 0;
						mainPointer--;
					} else {
						mainPointer--;
						int tmp = newArr[mainPointer][j];
						newArr[mainPointer][j] = newArr[i][j];
						newArr[i][j] = tmp;
					}
				}
			}
		}

		return newArr;
	}

	// 2차원 배열 복사
	static int[][] cloneArr(int[][] arr) {
		int[][] copy = new int[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			copy[i] = arr[i].clone();
		}
		return copy;
	}

	// 최대 값 찾기
	static int findMax(int[][] arr) {
		int max = 0;
		for (int[] line : arr) {
			for (int value : line) {
				if (value > max)
					max = value;
			}
		}
		return max;
	}
}