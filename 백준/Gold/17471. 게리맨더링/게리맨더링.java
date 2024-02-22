import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] peopleCnt;
    static HashSet<Integer>[] edgeList;

    static boolean[] visited;

    static int ans = (int) 1e9;
    static int tmpCnt;


    static int cnt = 0;


    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());

        peopleCnt = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            peopleCnt[i] = Integer.parseInt(st.nextToken());
        }

        edgeList = new HashSet[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edgeList[i] = new HashSet<>();
        }

        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int range = Integer.parseInt(st.nextToken());
            for (int j = 0; j < range; j++) {
                int num = Integer.parseInt(st.nextToken()); // 연결되는 정점의 정보
                edgeList[i].add(num);
                edgeList[num].add(i);
            }
        }

        int range = n / 2;
        for (int size = 1; size <= range; size++) {// range = n/2 -> nC1 ... nCn/2까지 조합을 뽑습니다.
            combination(0, size, 1);
        }

        if (ans == (int) 1e9) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
    }

    static void combination(int depth, int size, int idx) {
        if (depth == size) {
            boolean[] tmpVisitedA = visited.clone();
            boolean[] tmpVisitedB = visited.clone();
            if (checkA(tmpVisitedA, n - size) && checkB(tmpVisitedB, size)) { // 올바르게 나누어졌다면
                //답을 구해야합니다.
                calculateAnswer();
            }
            return;
        }

        for (int i = idx; i < n + 1; i++) {
            visited[i] = true;
            combination(depth + 1, size, i + 1);
            visited[i] = false;
        }
    }

    static boolean checkA(boolean[] tmpVisited, int size) {
        // false인 곳 아무데서나 시작
        for (int node = 1; node < tmpVisited.length; node++) {
            if (!tmpVisited[node]) {
                tmpCnt = 0;
                tmpVisited[node] = true;
                checkCnt(tmpVisited, 1, node);
                tmpVisited[node] = false;
                break;
            }
        }

        return tmpCnt == size;
    }

    static boolean checkB(boolean[] tmpVisited, int size) {
        // false인 곳 아무데서나 시작
        for (int i = 1; i < tmpVisited.length; i++) {
            tmpVisited[i] = !tmpVisited[i];
        }

        for (int node = 1; node < tmpVisited.length; node++) {
            if (!tmpVisited[node]) {
                tmpCnt = 0;
                tmpVisited[node] = true;
                checkCnt(tmpVisited, 1, node);
                tmpVisited[node] = false;
                break;
            }
        }

        return tmpCnt == size;
    }


    static void checkCnt(boolean[] tmpVisited, int cnt, int node) {
        tmpCnt++;

        for (Integer next : edgeList[node]) {
            if (!tmpVisited[next]) { // 방문 안했으면
                tmpVisited[next] = true;
                checkCnt(tmpVisited, cnt + 1, next);
            }
        }

    }

    static void calculateAnswer() {
        int trueAns = 0;
        int falseAns = 0;
        for (int i = 1; i < n + 1; i++) {
            if (visited[i]) {
                trueAns += peopleCnt[i];
            } else {
                falseAns += peopleCnt[i];
            }
        }

        ans = Math.min(ans, Math.abs(trueAns - falseAns));
    }
}