import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 용사 초기 레벨 빈도
        long[] freq = new long[201];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            freq[Integer.parseInt(st.nextToken())]++;
        }

        // 몬스터 레벨 ↔ 층 매핑
        int[] floorOfLevel = new int[201];
        boolean[] hasMonster = new boolean[201];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int lvl = Integer.parseInt(st.nextToken());
            hasMonster[lvl] = true;
            floorOfLevel[lvl] = i;
        }

        // bestFloor[L]: 레벨 L일 때 갈 층 번호
        int[] bestFloor = new int[201];
        int curMax = 0;
        for (int L = 1; L <= 200; L++) {
            if (hasMonster[L]) curMax = L;
            bestFloor[L] = (curMax == 0 ? 1 : floorOfLevel[curMax]);
        }

        // visits[L]: 전체 용사들이 레벨 L에서 사냥하는 횟수
        long[] visits = new long[201];
        for (int h = 1; h <= 200; h++) {
            if (freq[h] == 0) continue;
            int days = Math.min(K, 200 - h);
            for (int L = h; L < h + days; L++) {
                visits[L] += freq[h];
            }
        }

        // 기준(마법석 없음) 전체 이동 시간 합
        long baseTime = 0;
        for (int L = 1; L <= 200; L++) {
            if (visits[L] > 0) {
                baseTime += visits[L] * (bestFloor[L] - 1L);
            }
        }

        // 최적 마법석 위치 탐색
        long bestSaved = 0;
        int bestX = 1, bestY = 2;

        int[] dist = new int[M + 1];
        for (int x = 1; x <= M; x++) {
            for (int y = x + 1; y <= M; y++) {
                // BFS: 층 1에서 시작
                Arrays.fill(dist, Integer.MAX_VALUE);
                Deque<Integer> dq = new ArrayDeque<>();
                dist[1] = 0;
                dq.add(1);

                while (!dq.isEmpty()) {
                    int u = dq.poll();
                    int d = dist[u];
                    // 순간이동
                    if (u == x && d < dist[y]) {
                        dist[y] = d;
                        dq.addFirst(y);
                    }
                    if (u == y && d < dist[x]) {
                        dist[x] = d;
                        dq.addFirst(x);
                    }
                    // 계단 이동
                    if (u > 1 && d + 1 < dist[u - 1]) {
                        dist[u - 1] = d + 1;
                        dq.addLast(u - 1);
                    }
                    if (u < M && d + 1 < dist[u + 1]) {
                        dist[u + 1] = d + 1;
                        dq.addLast(u + 1);
                    }
                }

                // 설치 후 전체 이동 시간 계산
                long afterTime = 0;
                for (int L = 1; L <= 200; L++) {
                    if (visits[L] > 0) {
                        afterTime += visits[L] * dist[ bestFloor[L] ];
                    }
                }

                long saved = baseTime - afterTime;
                if (saved > bestSaved) {
                    bestSaved = saved;
                    bestX = x;
                    bestY = y;
                }
            }
        }

        System.out.println(bestX + " " + bestY);
        System.out.println(bestSaved);
    }
}
