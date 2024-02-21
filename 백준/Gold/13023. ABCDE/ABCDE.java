import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n; // 사람
    static int m; // 관계 수
    static ArrayList<Integer>[] arr; // 인접리스트
    static boolean[] visited; // 방문배열

    static void setInputs() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) { // 인접리스트 초기화
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 인접리스트에 원소 추가 (양방향)
            arr[a].add(b);
            arr[b].add(a);
        }
    }

    static void findFriends() {
        for (int i = 0; i < n; i++) {
            visited[i] = true;
            if (dfs(i, 0)) { // 조건을 찾아버렸으면 1을 출력하고 return
                System.out.println(1);
                return;
            }
            visited[i] = false;
        }

        System.out.println(0); // 조건을 못찾았으면 0을 출력하고 return
    }

    static boolean dfs(int idx, int number) {
        if (number == 4) {
            return true;
        }

        for (int i : arr[idx]) {
            if (!visited[i]) {
                visited[i] = true;
                if (dfs(i, number + 1)) { // number == 4가 되었으면 true를 반환하는데 이 때 true를 반환해서 메소드를 종료시키는 전략
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        setInputs();
        findFriends();
    }
}
