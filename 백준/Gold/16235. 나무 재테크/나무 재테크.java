import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int k;
    static int[][] winterFood;
    static int[][] soil;
    static TreeList[][] trees;
    static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dy = {-1, 1, 0, 0, 1, -1, 1, -1};
    static StringBuilder sb = new StringBuilder();

    static class TreeList {
        Tree head;

        TreeList() {
            head = new Tree(-1); // 쓰레기 헤드 만들어주기
            head.before = head;
            head.next = head;
        }

        // 문제에서 입력으로 주어지는 나무의 위치는 모두 다르다고 했다.
        // 이후, 나무가 번식을 하면 새로운 나무를 추가해 주어야 하는데,
        // 현재 Tree의 두번째 부분(첫번째는 편의상 빈 나무임 = 헤더)에 넣어주면 된다.

        // A(헤더)의 뒤에 B를 넣으면 된다.
        // A -> B -> C   =>    A -> D -> B -> C
        public void add(Tree tree) {
            head.next.before = tree;
            tree.next = head.next;
            tree.before = head;
            head.next = tree;
        }

        // X.delete(Y)
        // X 트리부터 Y트리 전까지 연결됨.

        // A -> D -> C -> B 가 있다고 할 때
        // A.delete(B) : A -> D
        // A.delete(C) : A -> D -> B
        // D.delete(B) : D -> C
        // X == Y라면 자기 자신만 남음.
        // X < Y라면 그대로.

        // add를 하면 나이 순서대로 정렬이 될텐데
        // 양분을 못 먹는 나무 이후로부터는 전부 죽어버린다.
        // A -> B -> C -> D 가 있을때, C부터는 양분을 못먹어서 죽어버린다고 할 때
        // A.delete(C)를 해주면 A -> B만 남게 되는 것이다.
        public void delete(Tree tree) {
            tree.before.next = head;
        }
    }

    static class Tree {
        int cnt = 0;
        int age = 0;
        Tree before = null;
        Tree next = null;

        public Tree(int age) { // 문제에서 주어지는 트리 만들기
            this.age = age;
            cnt = 1;
        }

        public Tree(int age, int cnt) { // 번식할 트리 만들기(개수도 필요함)
            this.age = age;
            this.cnt = cnt;
        }

    }

    static void setInputs() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        winterFood = new int[n][n];
        soil = new int[n][n];
        trees = new TreeList[n][n];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                winterFood[r][c] = Integer.parseInt(st.nextToken());
                soil[r][c] = 5;
                trees[r][c] = new TreeList();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            trees[x][y].add(new Tree(z));
        }
    }

    private static void performSpringAndSummerOperation() { // 봄 행동(여름을 곁들인)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                boolean canAbsorbSoil = true;
                Tree treeToDelete = null;
                Tree tree = trees[i][j].head.next; // 헤드의 다음 나무가 1번 나무

                while (tree.age > 0) { // 나무 끝까지 탐색해서 다시 헤더로 돌아 온다면 종료
                    // 양분 섭취
                    if (canAbsorbSoil) {
                        int sum = tree.cnt * tree.age; // 해당 나무가 양분을 얼마나 섭취할 수 있니?
                        if (soil[i][j] >= sum) { // 양분 섭취가 가능하다!
                            soil[i][j] -= sum;
                            tree.age++;
                        } else { // 양분 섭취를 못해요!
                            /**
                             * 여름
                             */
                            treeToDelete = performSummerOperation(i, j, tree);
                            canAbsorbSoil = false; // 더이상 양분을 먹을 나무는 없고 전부다 토양으로 돌아가요! 슬픕니다.
                        }
                    } else { // 너흰 이제 토양으로 돌아가라.
                        soil[i][j] += tree.cnt * (tree.age / 2);
                    }

                    tree = tree.next; // 다음 트리를 탐색합니다.
                }

                // 모든 트리를 탐색했으니, 이제 제거할 트리가 있는지 찾아볼까요?
                if (treeToDelete == null) {
                    continue;
                } // 제거할 트리가 있다면?? 제거해주면 됩니다.
                trees[i][j].delete(treeToDelete);
            }
        }
    }

    static Tree performSummerOperation(int i, int j, Tree tree) { // 여름 행동
        Tree treeToDelete;
        int ableTreesCount = soil[i][j] / tree.age; // 현재 토양에서 지금 나이의 나무 몇그루가 양분을 섭취할 수 있나?

        soil[i][j] -= ableTreesCount * tree.age; // 양분을 먹었어요!
        soil[i][j] +=
                (tree.cnt - ableTreesCount) * (tree.age / 2); // 죽은 나무는 나이의 절반만큼 양분으로 돌아갑니다!

        if (ableTreesCount > 0) { // 양분을 먹은 나무가 있었다면
            tree.cnt = ableTreesCount; // tree의 cnt를 다시 갱신해준다.
            tree.age++; // 한 살 더 먹으렴
            treeToDelete = tree.next; // 이 다음 나무부터는 제거 해버린다.
        } else { // 아무도 양분을 못먹었다면 이 나무부터 제거 해버린다.
            treeToDelete = tree;
        }
        return treeToDelete;
    }

    private static void performAutumnOperation() { // 가을 행동
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Tree tree = trees[i][j].head.next; // 헤드의 다음 나무가 1번 나무
                while (tree.age > 0) { // 헤더로 다시 돌아오면 종료.
                    // 나이가 5의 배수인 나무의 경우
                    if (tree.age % 5 == 0) {
                        for (int dir = 0; dir < 8; dir++) { // 8방 탐색 슛~~
                            int nx = i + dx[dir];
                            int ny = j + dy[dir];

                            if (nx < 0 || ny < 0 || nx >= n || ny >= n) { // 맵 밖으로 벗어나면 continue
                                continue;
                            }

                            if (trees[nx][ny].head.next.age == 1) { // 이미 해당칸에 다른칸에서 번식해온 나무가 있으면
                                // 헤더만 존재했다면 헤드의 age는 -1이라 조건을 만족 못해요.(이 때 헤드의 next는 헤드임)
                                trees[nx][ny].head.next.cnt += tree.cnt; // 이제 번식할 나무의 개수만 더해주면 된다.
                                continue;
                            }

                            // 해당 칸에 처음 번식을 하는거라면 나이는 1, 번식할 나무의 개수로 tree 생성
                            trees[nx][ny].add(new Tree(1, tree.cnt));

                        }
                    }
                    tree = tree.next; // 다음 트리 탐색
                }
            }
        }
    }

    private static void performWinterOperation() { // 겨울 행동
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                soil[i][j] += winterFood[i][j];
            }
        }
    }

    static int calculateAnswer() { // 답 계산
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Tree tree = trees[i][j].head.next;
                while (tree.age > 0) { // 헤더로 다시 돌아오면 종료
                    ans += tree.cnt; // 나무 count 해주기
                    tree = tree.next; // 다음 트리로 Go
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        setInputs();

        for (int year = 1; year < k + 1; year++) { // 1년부터 K년 까지

            performSpringAndSummerOperation();
            performAutumnOperation();
            performWinterOperation();
        }

        int ans = calculateAnswer();

        sb.append(ans);
        System.out.println(sb);
    }
}
