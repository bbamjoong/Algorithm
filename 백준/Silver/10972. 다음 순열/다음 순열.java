import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        nextPermutation(arr, n);

        System.out.println(sb);
    }

    // 뒤에서부터 탐색해옴.
    // 앞쪽에 더 작은 값이 있으면 스왑
    // 이후 앞쪽 값 뒤 인덱스부터 정렬
    static void nextPermutation(int[] arr, int n) {
        boolean isLastPermutation = true;

        loop:
        for (int i = n - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) { // 작고 큰걸 찾았음
                for (int j = n - 1; j > i - 1; j--) {
                    if (arr[j] > arr[i - 1]) { // 뒤쪽에 더 큰 값이 있으면 바꿔야합니다.
                        // 앞 : i-1, 뒤 : j
                        int tmp = arr[i - 1]; // 값 변경
                        arr[i - 1] = arr[j];
                        arr[j] = tmp;

                        // 앞쪽
                        int[] frontArr = new int[i];
                        for (int idx = 0; idx < frontArr.length; idx++) {
                            sb.append(arr[idx]).append(" ");
                        }

                        // 뒤쪽
                        int[] backArr = new int[n - i];
                        for (int idx = 0; idx < backArr.length; idx++) {
                            backArr[idx] = arr[i + idx];
                        }
                        Arrays.sort(backArr);

                        for (int value : backArr) {
                            sb.append(value).append(" ");
                        }

                        isLastPermutation = false;
                        break loop;
                    }

                }
            }
        }

        if (isLastPermutation) {
            sb.append(-1);
        }
    }
}