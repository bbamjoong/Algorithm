import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main {
    static int n;
    static List<int[]> lines;
    static List<Integer> lis;
    static List<int[]> dp;
    
    private static int read() throws Exception {
        int d, o;
        boolean negative = false;
        d = System.in.read();
        if(d == '-') {
            negative = true;
            d = System.in.read();
        }
        o = d & 15;
        while((d = System.in.read()) > 32)
            o = (o << 3) + (o << 1) + (d & 15);
        return negative ? -o : o;
    }
    
    public static void main(String[] args) throws Exception {
        n = read();
        lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = read();
            int b = read();
            lines.add(new int[]{a, b, a});
        }
        Collections.sort(lines, (o1, o2) -> o1[0] - o2[0]);
        lis = new ArrayList<>();
        lis.add(0);
        dp = new ArrayList<>();
        for (int[] line : lines) {
            int len = lis.size();
            int target = line[1];
            int start = line[2];
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
        for (int i = dp.size() - 1; i >= 0; i--) {
            if (dp.get(i)[0] == cnt) {
                res.add(dp.get(i)[2]);
                cnt--;
            }
            if (cnt < 0) break;
        }
        HashSet<Integer> keep = new HashSet<>();
        for (int num : res) {
            keep.add(num);
        }
        List<Integer> removed = new ArrayList<>();
        for (int[] line : lines) {
            int num = line[0];
            if (!keep.contains(num))
                removed.add(num);
        }
        Collections.sort(removed);
        StringBuilder sb = new StringBuilder();
        sb.append(removed.size()).append("\n");
        for (int num : removed)
            sb.append(num).append("\n");
        System.out.print(sb.toString());
    }
    
    static int binary(int target) {
        int left = 0;
        int right = lis.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (lis.get(mid) < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
}
