import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n; // 접시수
    static int d; // 가지수
    static int k; // 연속
    static int c; // 쿠폰번호
    static int[] sushi;
    static int ans;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[2 * n];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            sushi[i] = num;
            sushi[n + i] = num;
        }

        int[] arr = new int[d + 1]; // 초밥의 종류를 count 할거임

        int start = 0;
        int end = k - 1;

        int count = 0; // 초밥의 개수 계산
        //초기 값 계산
        for (int i = start; i <= end; i++) {
            if (arr[sushi[i]] == 0) { //처음 들어온 초밥이면 count++
                arr[sushi[i]]++;
                count++;
            } else {
                arr[sushi[i]]++;
            }
        }

        boolean flag1 = false;
        if (arr[c] == 0) { // 쿠폰번호에 해당하는 초밥 없으면 count++
            count++;
            flag1 = true;
        }
        if (flag1) {
            count--;
        }

        ans = Math.max(ans, count);

        //슬라이딩 윈도우 출발
        for (int i = 0; i < n; i++) {
            // start 포인트의 스시 제거
            arr[sushi[start]]--;
            // 제거했는데 0이면 count 줄이기
            if (arr[sushi[start]] == 0) {
                count--;
            }
            // 시작 포인터 옮기기
            start++;

            // end 포인트 증가
            end++;
            // 추가할 초밥이 원래 없던거면 count++
            if (arr[sushi[end]] == 0) {
                count++;
            }
            //초밥 개수 증가
            arr[sushi[end]]++;

            boolean flag = false; // 쿠폰 썼냐 안썼냐
            if (arr[c] == 0) { // 쿠폰번호에 해당하는 초밥 없으면 count++;
                count++;
                flag = true;
            }
            ans = Math.max(ans, count);
            if (flag) {
                count--;
            }

        }

        System.out.println(ans);
    }
}