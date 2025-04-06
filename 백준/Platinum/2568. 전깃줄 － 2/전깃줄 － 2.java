import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<int[]> lines;

    static List<Integer> lis;
    static List<int[]> dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines.add(new int[]{a, b}); // 시작, 도착
        }
        lines.sort((a, b) -> a[0] - b[0]);

        lis = new ArrayList<>();
        lis.add(0);
        dp = new ArrayList<>();

        for (int[] line : lines) {
            int len = lis.size();
            int target = line[1];
            int start = line[0];

            if (lis.get(len - 1) < target) {
                lis.add(target);
                dp.add(new int[]{len, target, start});
            } else {
                int idx = binary(target);
                lis.set(idx, target);
                dp.add(new int[]{idx, target, start});
            }
        }

        int cnt = lis.size() - 1;
        List<Integer> res = new ArrayList<>();

        for (int i = dp.size() - 1; i > -1; i--) {
            if (dp.get(i)[0] == cnt) {
                res.add(dp.get(i)[2]);
                cnt--;
            }
            if (cnt < 0) {
                break;
            }
        }

        // LIS에 포함된 전깃줄 번호(a값)를 저장
        HashSet<Integer> keep = new HashSet<>(res);

        List<Integer> removed = new ArrayList<>();
        for (int[] line : lines) {
            int num = line[0];
            if (!keep.contains(num)) {
                removed.add(num);
            }
        }
        Collections.sort(removed);

        StringBuilder sb = new StringBuilder();
        sb.append(removed.size()).append("\n");
        for (int num : removed) {
            sb.append(num).append("\n");
        }
        System.out.print(sb.toString());
    }

    static int binary(int target) {
        int left = 0;
        int right = lis.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (lis.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
