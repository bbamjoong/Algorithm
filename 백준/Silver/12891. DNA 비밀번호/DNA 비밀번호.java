import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int s;
    static int p;
    static String inputWord;
    // 내가 활성화 할 비트
    static int activatedBits = 0;
    // 모든 비트가 활성화 되었을 때
    static int allBitsActivated =
            (1 << ('A' & 0b11111) - 1) + (1 << ('C' & 0b11111) - 1) + (1 << ('G' & 0b11111) - 1) + (1
                    << ('T' & 0b11111) - 1);
    static int[] sufficientAlphabets = new int['Z' - 'A' + 1];
    static int[] windowAlphabets = new int['Z' - 'A' + 1];
    static char[] keyAlphabets = {'A', 'C', 'G', 'T'};
    static int ans = 0;
    static StringBuilder sb = new StringBuilder();

    /**
     * 모든 비트가 활성화 되었다면 답을 처리하는 방식으로 구현.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()); // s, p 입력
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        inputWord = br.readLine(); // 입력받은 문자열

        st = new StringTokenizer(br.readLine());
        for (char keyAlphabet : keyAlphabets) {
            int keyAlphabetCnt = Integer.parseInt(st.nextToken());
            
            sufficientAlphabets[keyAlphabet - 'A'] = keyAlphabetCnt; // 필요 조건을 배열에 저장한다

            if (keyAlphabetCnt == 0) { // 조건이 아닌 비트는 활성화 시킨다.
                activatedBits |= 1 << (keyAlphabet & 0b11111) - 1;
            }
        }

        initializeWindow(); // 초기 Window 설정
        slidingWindow(); // Window 이동 및 답 체크

        sb.append(ans);
        System.out.println(sb);
    }

    static void initializeWindow() {
        for (int i = 0; i < p; i++) { // 초기 Window에 있는 알파벳 char를 추가해준다.
            windowAlphabets[inputWord.charAt(i) - 'A']++;
        }

        for (char keyAlphabet : keyAlphabets) {
            /**
             *  1. 초기 조건에 들어가지 않아서 이미 활성화된 비트 -> 다시 활성화 시켜도 달라지는 건 없음.(가지치기 하려면 메모리를 써야하는데 굳이.?)
             *  2. 처음 Window에서 해당 알파벳이 조건의 개수 이상 존재한다면 비트를 활성화한다.
             */
            if (windowAlphabets[keyAlphabet - 'A'] >= sufficientAlphabets[keyAlphabet - 'A']) {
                activatedBits |= 1 << (keyAlphabet & 0b11111) - 1;
            }
        }

        if (activatedBits == allBitsActivated) { // 모든 비트가 활성화 되었다면 ans++;
            ans++;
        }
    }

    static void slidingWindow() {
        int startIdx = 0;
        int endIdx = p - 1;

        for (int i = 0; i < s - p; i++) { // s - p회 만큼 윈도우를 슬라이딩한다.
            removeTail(inputWord, startIdx); // 꼬리 자르고, idx++
            startIdx++;
            addHead(inputWord, endIdx); // 머리 더하고, idx++
            endIdx++;
        }
    }

    static void removeTail(String inputWord, int startIdx) {
        char removeAlphabet = inputWord.charAt(startIdx);
        // 현재 알파벳이 필요한 알파벳의 개수와 같다면
        // 현재 알파벳이 더 많은 경우는 비트를 비활성화 시킬 필요가 없습니다.
        windowAlphabets[removeAlphabet - 'A']--;
        if (windowAlphabets[removeAlphabet - 'A'] < sufficientAlphabets[removeAlphabet - 'A']) {
            activatedBits &= ~(1 << (removeAlphabet & 0b11111) - 1); // 활성화된 비트에서 제거
        }
    }

    static void addHead(String inputWord, int endIdx) {
        char addAlphabet = inputWord.charAt(endIdx + 1);
        windowAlphabets[addAlphabet - 'A']++;
        // 새로운 알파벳을 탐색하는데, 만약 현재 그 알파벳의 개수가 필요한 알파벳의 개수와 맞아떨어지게 되는 순간
        // 비트를 활성화 시킨다.
        if (windowAlphabets[addAlphabet - 'A'] == sufficientAlphabets[addAlphabet - 'A']) {
            activatedBits |= 1 << (addAlphabet & 0b11111) - 1;
        }

        if (activatedBits == allBitsActivated) {
            ans++;
        }
    }
}