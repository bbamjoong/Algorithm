import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int l;
    static int[] arr;
    static int n;
    static int start = 0;
    static int end = 1001;
    static int ans = 0;

    static void setInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        l = Integer.parseInt(br.readLine());

        arr = new int[l];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < l; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        n = Integer.parseInt(br.readLine());
    }

    static boolean setRange() {
        for (int i : arr) {
            if (i < n) {
                if (i > start) {
                    start = i;
                }
            } else if (i > n) {
                if (i < end) {
                    end = i;
                }
            } else {
                System.out.println(0);
                return false;
            }
        }
        return true;
    }

    static void calculateAnswer() {
        for (int i = start + 1; i <= n; i++) {
            for (int j = n; j <= end - 1; j++) {
                if (i != j) {
                    ans++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInputs();
        if (!setRange()) {
            return;
        }
        calculateAnswer();
        System.out.println(ans);
    }
}