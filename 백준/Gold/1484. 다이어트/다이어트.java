import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 16);

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }

    private void solve() throws Exception {
        int targetDifference = Integer.parseInt(reader.readLine());
        List<Integer> candidateNumbers = generateCandidateNumbers(targetDifference);

        Set<Integer> squaredNumbers = new HashSet<>();
        StringBuilder resultBuilder = new StringBuilder();
        int resultCount = 0;

        for (int number : candidateNumbers) {
            int square = number * number;
            squaredNumbers.add(square);
            if (!squaredNumbers.contains(square - targetDifference)) {
                continue;
            }
            resultBuilder.append(number).append('\n');
            resultCount++;
        }

        if (resultCount == 0) {
            System.out.print(-1);
        } else {
            System.out.print(resultBuilder);
        }
    }

    /**
     * 현재 제곱수 - 이전 제곱수 라는 조건을 만족하는 모든 현재 제곱수의 근 숫자들을 구하기.
     */
    private List<Integer> generateCandidateNumbers(final int maxDifference) {
        List<Integer> candidateList = new ArrayList<>();
        int previous = 0;
        for (int i = 1; i * i - previous * previous <= maxDifference; i++) {
            previous = i;
            candidateList.add(i);
        }
        return candidateList;
    }
}
