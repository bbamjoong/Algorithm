import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int k;
    static int[][] winterFood;
    static int[][] soil;
    static ArrayList<Integer>[][] tree;

    static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dy = {-1, 1, 0, 0, 1, -1, 1, -1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        winterFood = new int[n][n];
        soil = new int[n][n];
        tree = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tree[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                winterFood[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                soil[i][j] = 5;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            tree[x][y].add(z);
        }

        for (int year = 0; year < k; year++) {

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!tree[i][j].isEmpty()) { // 어린나무부터 양분을 먹여야 하므로 정렬
                        tree[i][j].sort((x, y) -> Integer.compare(x, y));

                        ArrayList<Integer> tmp = new ArrayList<>();
                        int deathFood = 0;

                        // 나무의 나이가 현재 토양의 양분 이하라면
                        for (Integer age : tree[i][j]) {
                            if (age <= soil[i][j]) {
                                soil[i][j] -= age;
                                tmp.add(++age);
                            } else { // 나머지는 죽고 양분이 된다.
                                deathFood += age / 2;
                            }
                        }

                        // 토양에 양분을 더해준다.
                        soil[i][j] += deathFood;
                        // 살아남은 나무 최신화
                        tree[i][j] = tmp;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!tree[i][j].isEmpty()) {
                        //나무의 나이가 5배수
                        for (Integer age : tree[i][j]) {
                            if (age % 5 == 0) {
                                // 주변 8칸에 나이가 1인 나무 번식
                                for (int k = 0; k < 8; k++) {
                                    int nx = i + dx[k];
                                    int ny = j + dy[k];

                                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                                        continue;
                                    }
                                    tree[nx][ny].add(1);
                                }
                            }
                        }
                    }
                }
            }

            // 겨울에 양분 추가
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    soil[i][j] += winterFood[i][j];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += tree[i][j].size();
            }
        }

        sb.append(ans);
        System.out.println(sb);
    }
}