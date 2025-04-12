import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, L, C;
    static int ans = 1;
    static int[] pipe;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        pipe = new int[N];
        for (int i = 0; i < N; i++) {
            pipe[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pipe);
        C = pipe[0] + (L - 1);
        for (int i = 1; i < N; i++) {
            if (pipe[i] <= C) {
                continue;
            }
            C = pipe[i] + (L - 1);
            ans++;
        }
        System.out.println(ans);
    }
}