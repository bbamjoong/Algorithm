public class Main {

    static int n;
    static int m;
    static int k;
    static int[][] winterFood;
    static int[][] soil;
    static Tree[][] trees;
    static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dy = {-1, 1, 0, 0, 1, -1, 1, -1};
    static StringBuilder sb = new StringBuilder();

    static class Tree {
        int cnt = 0;
        int age = 0;
        Tree before;
        Tree next;

        public Tree() { // 헤더 트리 만들기
            before = next = this;
        }

        public Tree(int age) { // 문제에서 주어지는 트리 만들기
            this.age = age;
            cnt = 1;
        }

        public Tree(int age, int cnt) { // 번식할 트리 만들기(개수도 필요함)
            this.age = age;
            this.cnt = cnt;
        }

        // 문제에서 입력으로 주어지는 나무의 위치는 모두 다르다고 했다.
        // 이후, 나무가 번식을 하면 새로운 나무를 추가해 주어야 하는데,
        // 현재 Tree의 두번째 부분(첫번째는 편의상 빈 나무임 = 헤더)에 넣어주면 된다.

        // A(헤더)의 뒤에 B를 넣으면 된다.
        // A -> B -> C   =>    A -> D -> B -> C
        public void add(Tree tree) {
            this.next.before = tree;
            tree.next = this.next;
            tree.before = this;
            this.next = tree;
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
            tree = tree.before;
            tree.next = this;
            this.before = tree;
        }
    }

    static final int BUFFER_SIZE = 1 << 13;
    static byte[] buffer = new byte[BUFFER_SIZE];
    static int bufferLen, bufferIdx;

    static int nextInt() throws Exception { // Int 입력
        byte b;
        int n = 0;
        while ((b = read()) <= 32)
            ;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
        } while (isNumber(b = read()));

        return n;
    }

    static boolean isNumber(byte b) {
        return 47 < b && b < 58;
    }

    static byte read() throws Exception {
        if (bufferIdx == bufferLen) {
            bufferLen = System.in.read(buffer, bufferIdx = 0, BUFFER_SIZE);
            if (bufferLen == -1) {
                buffer[0] = -1;
            }
        }
        return buffer[bufferIdx++];
    }

    static void setInputs() throws Exception {
        n = nextInt();
        m = nextInt();
        k = nextInt();

        winterFood = new int[n][n];
        soil = new int[n][n];
        trees = new Tree[n][n];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                winterFood[r][c] = nextInt();
                soil[r][c] = 5;
                trees[r][c] = new Tree();
            }
        }

        for (int i = 0; i < m; i++) {
            int x = nextInt() - 1;
            int y = nextInt() - 1;
            int z = nextInt();

            trees[x][y].add(new Tree(z));
        }
    }

    private static void performSpringAndSummerOperation() { // 봄 행동(여름을 곁들인)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                boolean canAbsorbSoil = true;
                Tree treeToDelete = null;
                Tree tree = trees[i][j].next;

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
                Tree tree = trees[i][j].next;
                while (tree.age > 0) { // 헤더로 다시 돌아오면 종료.
                    // 나이가 5의 배수인 나무의 경우
                    if (tree.age % 5 == 0) {
                        for (int dir = 0; dir < 8; dir++) { // 8방 탐색 슛~~
                            int nx = i + dx[dir];
                            int ny = j + dy[dir];

                            if (nx < 0 || ny < 0 || nx >= n || ny >= n) { // 맵 밖으로 벗어나면 continue
                                continue;
                            }

                            if (trees[nx][ny].next.age == 1) { // 이미 해당칸에 다른칸에서 번식해온 나무가 있으면
                                // 헤더만 존재했다면 해더의 age는 0이라 조건을 만족 못해요.
                                trees[nx][ny].next.cnt += tree.cnt; // 이제 번식할 나무의 개수만 더해주면 된다.
                                continue;
                            }

                            trees[nx][ny].add(
                                    new Tree(1, tree.cnt)); // 해당 칸에 처음 번식을 하는거라면 나이는 1, 번식할 나무의 개수로 tree 생성

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
                Tree tree = trees[i][j].next;
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