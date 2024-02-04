import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int k;
    static int[][] winterFood;
    static int[][] soil;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        winterFood = new int[n][n];
        soil = new int[n][n];
        Tree[][] trees = new Tree[n][n];
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                winterFood[r][c] = Integer.parseInt(st.nextToken());
                soil[r][c] = 5;
                trees[r][c] = new Tree();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            trees[x][y].add(new Tree(z));
        }

        for (int k = 0; k < Main.k; k++) {

            for (int i = 0; i < n; i++) { // 봄 여름
                for (int j = 0; j < n; j++) {

                    boolean eat = true;
                    Tree del = null;
                    Tree tree = trees[i][j].next;

                    while (tree.age > 0) {
                        // 양분 섭취
                        if (eat) {
                            // 나무가 모두 양분을 섭취했을 때 필요한 양을 구함
                            int sum = tree.cnt * tree.age;
                            // 모든 나무가 양분을 먹을 수 있을 때
                            if (soil[i][j] >= sum) {
                                soil[i][j] -= sum;
                                tree.age++;
                            }
                            // 일부만 양분을 먹을 수 있을 때
                            else {
                                // 양분을 먹을 수 있는 나무의 수
                                int able = soil[i][j] / tree.age;
                                // 죽을 나무의 수
                                int deadCnt = tree.cnt - able;
                                // 양분을 먹거나 양분이 되기
                                soil[i][j] -= able * tree.age;
                                soil[i][j] += deadCnt * (tree.age / 2);

                                // 아직 살아있는 나무가 있는지
                                if (able > 0) {
                                    tree.cnt = able;
                                    tree.age++;
                                    del = tree.next;
                                } else {
                                    del = tree;
                                }
                                eat = false;
                            }
                        }
                        // 양분이 되기(죽음)
                        else {
                            soil[i][j] += tree.cnt * (tree.age / 2);
                        }

                        tree = tree.next;
                    }
                    // 제거할 나무가 있고, 그게 헤더노드가 아닐 때
                    if (del != null && del.age > 0) {
                        trees[i][j].delete(del);
                    }
                }
            }

            for (int i = 0; i < n; i++) { // 가을
                for (int j = 0; j < n; j++) {
                    Tree t = trees[i][j].next;
                    while (t.age > 0) {
                        // 나이가 5의 배수인 나무의 경우
                        if (t.age % 5 == 0) {
                            for (int dir = 0; dir < 8; dir++) {
                                int nx = i + dx[dir];
                                int ny = j + dy[dir];

                                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                                    continue;
                                }
                                // 이미 나이가 1인 나무 그룹이 생성되 있는지
                                if (trees[nx][ny].next.age == 1) {
                                    trees[nx][ny].next.cnt += t.cnt;
                                } else {
                                    trees[nx][ny].add(new Tree(1, t.cnt));
                                }
                            }
                        }
                        t = t.next;
                    }
                }
            }

            for (int i = 0; i < n; i++) { // 겨울
                for (int j = 0; j < n; j++) {
                    soil[i][j] += winterFood[i][j];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Tree t = trees[i][j].next;
                while (t.age > 0) {
                    ans += t.cnt;
                    t = t.next;
                }
            }
        }

        System.out.println(ans);
    }

    static class Tree {
        int cnt;
        int age;
        Tree before;
        Tree next;

        // 처음 trees를 초기화할 때
        public Tree() {
            this.age = 0;
            before = next = this;
        }

        public Tree(int age) {
            this.age = age;
            cnt = 1;
        }

        public Tree(int age, int cnt) {
            this.age = age;
            this.cnt = cnt;
        }

        // This의 next에 tree를 넣는다.
        // A -> B -> C   =>    A -> D -> B -> C
        public void add(Tree tree) {
            this.next.before = tree;
            tree.next = this.next;
            tree.before = this;
            this.next = tree;
        }

        // This의 next노드를 없앤다.
        // A -> D -> B -> C   =>    A -> B -> C
        public void delete(Tree tree) {
//            this.next = tree.before;
//            tree.next.before = tree.before;
            tree = tree.before;
            tree.next = this;
            this.before = tree;
        }
    }

}