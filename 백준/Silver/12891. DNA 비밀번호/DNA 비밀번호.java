import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int activatedBits = 0;
    static int allBitsActivated =
            (1 << ('A' & 0b11111) - 1) + (1 << ('C' & 0b11111) - 1) + (1 << ('G' & 0b11111) - 1) + (1
                    << ('T' & 0b11111) - 1);
    static int[] sufficientAlphabets = new int['Z' - 'A' + 1];
    static int[] nowAlphabets = new int['Z' - 'A' + 1];
    static char[] keyAlphabets = {'A', 'C', 'G', 'T'};
    static int ans = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()); // s, p 입력
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        String inputWord = br.readLine(); // 입력받은 문자열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            char alphabet = keyAlphabets[i];
            int num = Integer.parseInt(st.nextToken());

            sufficientAlphabets[alphabet - 'A'] = num; // 필요 조건을 배열에 저장한다
            if (num == 0) { // 조건이 아닌 비트는 활성화 시킨다.
                activatedBits |= 1 << (alphabet & 0b11111) - 1;
            }
        }

        initialize(inputWord, p);
        slidingWindow(inputWord, p, s);

        sb.append(ans);
        System.out.println(sb);
    }

    static void initialize(String inputWord, int p) {
        for (int i = 0; i < p; i++) {
            nowAlphabets[inputWord.charAt(i) - 'A']++;
        }

        for (int i = 0; i < 4; i++) {
            char alphabet = keyAlphabets[i];
            // 알파벳이 조건을 만족했다. or 초기 조건에 들어가지 않아서 이미 활성화된 비트
            if (nowAlphabets[alphabet - 'A'] >= sufficientAlphabets[alphabet - 'A']) {
                activatedBits |= 1 << (alphabet & 0b11111) - 1;
            } else { // 현재 조건을 만족하지 않는다면 비트를 뺀다.
                activatedBits &= ~(1 << (alphabet & 0b11111) - 1);
            }
        }
        if (activatedBits == allBitsActivated) {
            ans++;
        }
    }

    static void slidingWindow(String inputWord, int p, int s) {
        int startIdx = 0, endIdx = p - 1;
        for (int i = 0; i < s - p; i++) {
            removeTail(inputWord, startIdx);
            startIdx++;
            addHead(inputWord, endIdx);
            endIdx++;
        }
    }

    static void removeTail(String inputWord, int startIdx) {
        char removeAlphabet = inputWord.charAt(startIdx);
        // 현재 알파벳이 필요한 알파벳의 개수와 같다면
        if (nowAlphabets[removeAlphabet - 'A'] == sufficientAlphabets[removeAlphabet - 'A']) {
            activatedBits &= ~(1 << (removeAlphabet & 0b11111) - 1); // 활성화된 비트에서 제거
        }
        nowAlphabets[removeAlphabet - 'A']--;
    }

    static void addHead(String inputWord, int endIdx) {
        char addAlphabet = inputWord.charAt(endIdx + 1);
        nowAlphabets[addAlphabet - 'A']++;
        if (nowAlphabets[addAlphabet - 'A'] == sufficientAlphabets[addAlphabet - 'A']) {
            activatedBits |= 1 << (addAlphabet & 0b11111) - 1;
        }

        if (activatedBits == allBitsActivated) {
            ans++;
        }
    }
}