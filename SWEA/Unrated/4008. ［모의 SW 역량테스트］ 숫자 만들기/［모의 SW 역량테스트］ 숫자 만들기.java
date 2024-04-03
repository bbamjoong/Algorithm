import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int plus;
    static int minus;
    static int multiple;
    static int divide;
    static int[] numbers;

    static int minAns;
    static int maxAns;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            minAns = (int) 1e8;
            maxAns = -(int) 1e8;

            sb.append("#").append(tc).append(" ");
            n = Integer.parseInt(br.readLine());
            numbers = new int[n];

            // 연산자 개수 저장
            st = new StringTokenizer(br.readLine());
            plus = Integer.parseInt(st.nextToken());
            minus = Integer.parseInt(st.nextToken());
            multiple = Integer.parseInt(st.nextToken());
            divide = Integer.parseInt(st.nextToken());

            // 피연산자 저장
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            // 숫자 하나 고르고 -> 연산자만 백트래킹
            operate(1, numbers[0], plus, minus, multiple, divide);
            sb.append(maxAns - minAns).append("\n");
        }
        System.out.println(sb);
    }

    static void operate(int depth, int total, int plusCnt, int minusCnt, int multipleCnt, int divideCnt) {
        if (depth == n) {
            minAns = Math.min(minAns, total);
            maxAns = Math.max(maxAns, total);
            return;
        }

        if (plusCnt > 0) {
            operate(depth + 1, total + numbers[depth], plusCnt - 1, minusCnt, multipleCnt, divideCnt);
        }
        if (minusCnt > 0) {
            operate(depth + 1, total - numbers[depth], plusCnt, minusCnt - 1, multipleCnt, divideCnt);
        }
        if (multipleCnt > 0) {
            operate(depth + 1, total * numbers[depth], plusCnt, minusCnt, multipleCnt - 1, divideCnt);
        }
        if (divideCnt > 0) {
            operate(depth + 1, total / numbers[depth], plusCnt, minusCnt, multipleCnt, divideCnt - 1);
        }
    }
}