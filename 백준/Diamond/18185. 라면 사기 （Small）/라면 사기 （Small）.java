import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] arr;
    static int ans;

    static void setInputs() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[n] = 0;
        arr[n + 1] = 0;
    }

    static void buyRamens() {
        for (int i = 0; i < n; i++) {
            if (arr[i + 1] > arr[i + 2]) {
                ramenSpecial(i);
                ramen(i, 3);
                ramen(i, 1);
            } else {
                for (int num = 3; num > 0; num--) {
                    ramen(i, num);
                }
            }
        }
    }

    static void ramenSpecial(int k) {
        int value = min(arr[k], arr[k + 1] - arr[k + 2]);
        arr[k] -= value;
        arr[k + 1] -= value;
        ans += 5 * value;
    }

    static void ramen(int k, int num) {
        int value = (int) 1e9;

        for (int i = 0; i < num; i++) {
            value = min(value, arr[k + i]);
        }

        for (int i = 0; i < num; i++) {
            arr[k + i] -= value;
        }

        ans += (2 * num + 1) * value;
    }

    public static void main(String[] args) throws IOException {
        // 1개, 2개 사는 것을 순서대로 최소화 해야한다.
        // 2개 사는 것을 최소화하는 것이 전략.
        // i, i+1, i+2를 비교했을 때  i+2가 존재해야 3개 사는 것 최대화 가능 -> 2개 사는 것 최소화 가능
        // 따라서 i+1이 i+2보다 많을 때 min(li[i], li[i+1] - li[i+2])만큼 i번째에서 2개 사기를 진행해준다.
        // 이후 가능한 수 만큼 3개 사기를 진행한다.

        setInputs();
        buyRamens();
        System.out.println(ans);
    }
}