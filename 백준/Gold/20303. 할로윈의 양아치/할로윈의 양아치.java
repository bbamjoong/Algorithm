import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n; // 거리에 있는 아이들 수
    static int m; // 친구 관계 수
    static int k; // 울음소리 공명 최소 아이 수

    static int[] candy; // 사탕의 개수
    static int[] parent;

    static Set<Integer> numInfo = new HashSet<>();
    static Group[] groupArray;
    static ArrayList<Group> groups = new ArrayList<>(); // 최종 그룹
    static int[] dp;

    static class Group {
        int size;
        int candy;

        public Group(int size, int candy) {
            this.size = size;
            this.candy = candy;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
            return;
        }
        parent[a] = b;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        candy = new int[n + 1];
        parent = new int[n + 1];
        groupArray = new Group[n + 1];
        dp = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) { // 사탕 분배
            candy[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
            groupArray[i] = new Group(0, 0);
        }

        for (int i = 0; i < m; i++) { // 친구 관계
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        for (int i = 1; i < n + 1; i++) {
            int num = find(parent[i]);
            groupArray[num].candy += candy[i];
            groupArray[num].size++;
            numInfo.add(num); // index 정보 추가
        }

        for (Integer i : numInfo) {
            groups.add(groupArray[i]);
        }

        // dp로 냅색 시작합니다.
        int[][] dp = new int[groups.size() + 1][k];

        for (int i = 1; i <= groups.size(); i++) {
            int size = groups.get(i - 1).size;
            int candyCnt = groups.get(i - 1).candy;

            for (int j = 1; j < k; j++) {
                if (j < size) { // 가방에 못 넣는 경우
                    dp[i][j] = dp[i - 1][j];
                } else { // 가방에 넣을 수 있는 경우
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - size] + candyCnt);
                }
            }
        }
        System.out.println(dp[groups.size()][k - 1]);
    }
}