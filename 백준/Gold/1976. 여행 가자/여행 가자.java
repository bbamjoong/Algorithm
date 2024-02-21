import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int n; // 도시의 수
    static int m; // 여행할 도시의 수
    static int[] parent; // 부모 정보
    
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        
        if (a < b) {
            parent[b] = a;
            return;
        }
        parent[a] = b;
    }
    
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        parent = new int[n+1];
        for (int i = 0; i < n+1; i++) { // parent 초기화
            parent[i] = i;
        }
        
        // 탐색 순서를 1부터 시작해서 n까지 지정해줌.
        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n+1; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    union(i, j);
                }
            }
        }
        
        st = new StringTokenizer(br.readLine()); // 여행계획 입력받기
        int standard = find(Integer.parseInt(st.nextToken())); // 시작점의 부모
        for (int i = 1; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (standard != find(num)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}