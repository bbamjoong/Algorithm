import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //반복 횟수가 따로 정해져있지 않으니 무한루프 사용
        while (true) {
            String S = br.readLine();
            //10진수로 바꾼 값을 저장할 변수 선언
            int result = 0;
            //문자열 길이를 int 변수에 저장
            int n = S.length();

            //#이 오면 반복문 종료
            if (S.equals("#")) {
                break;
            }

            for (int i = 0; i < n; i++) {
                if (S.charAt(i) == '-') {
                    result += (int) (0 * Math.pow(8, ((n - 1) - i)));
                } else if (S.charAt(i) == '\\') {
                    result += (int) (1 * Math.pow(8, ((n - 1) - i)));
                } else if (S.charAt(i) == '(') {
                    result += (int) (2 * Math.pow(8, ((n - 1) - i)));
                } else if (S.charAt(i) == '@') {
                    result += (int) (3 * Math.pow(8, ((n - 1) - i)));
                } else if (S.charAt(i) == '?') {
                    result += (int) (4 * Math.pow(8, ((n - 1) - i)));
                } else if (S.charAt(i) == '>') {
                    result += (int) (5 * Math.pow(8, ((n - 1) - i)));
                } else if (S.charAt(i) == '&') {
                    result += (int) (6 * Math.pow(8, ((n - 1) - i)));
                } else if (S.charAt(i) == '%') {
                    result += (int) (7 * Math.pow(8, ((n - 1) - i)));
                } else {
                    result += (int) (-1 * Math.pow(8, ((n - 1) - i)));
                }
            }
            System.out.println(result);
        }
    }
}
