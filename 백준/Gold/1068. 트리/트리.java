import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int node;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        node = Integer.parseInt(br.readLine());

        erase(node); // 노드를 지우는 메소드
        cnt = calculateAnswer(); // 리프 노드의 개수를 구하는 메소드
        
        sb.append(cnt);
        System.out.println(sb.toString());
    }

    static int calculateAnswer() {
        for (int i = 0; i < n; i++) {
            if (arr[i] == -2) { // -2는 지운 노드이기 때문에 리프 노드가 될 수 없다.
                continue;
            }

            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (i == arr[j]) { // (Index가 노드 넘버이다.) 만약 자식이 존재한다면 리프 노드가 될 수 없다.
                    found = true;
                    break;
                }
            }

            if (!found) { // 자식이 없다면 리프 노드이다.
                cnt += 1;
            }
        }
        return cnt;
    }

    static void erase(int node) {
        arr[node] = -2; // 지울 노드는 -2로 갱신

        for (int i = 0; i < n; i++) {
            if (node == arr[i]) { // 만약 지울 노드의 자식이 존재한다면? 자식도 지운다.
                erase(i);
            }
        }
    }
}