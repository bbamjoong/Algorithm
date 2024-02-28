import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static ArrayList<osolGil>[] li;

    static class osolGil implements Comparable<osolGil> {
        int to;
        double weight;
        boolean fast;

        public osolGil(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }

        public osolGil(int to, double weight, boolean fast) {
            this.to = to;
            this.weight = weight;
            this.fast = fast;
        }

        @Override
        public int compareTo(osolGil o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static void setInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        li = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            li[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            li[a].add(new osolGil(b, w));
            li[b].add(new osolGil(a, w));
        }
    }

    static double[] fox() {
        double[] distance = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            distance[i] = 1e9;
        }

        distance[0] = 0;
        distance[1] = 0;

        PriorityQueue<osolGil> pq = new PriorityQueue<>();
        pq.offer(new osolGil(1, 0, true));

        while (!pq.isEmpty()) {
            osolGil current = pq.poll();
            int to = current.to;
            double dist = current.weight;

            if (distance[to] < dist) {
                continue;
            }

            for (osolGil next : li[to]) {
                double cost = dist + next.weight;

                if (cost < distance[next.to]) {
                    distance[next.to] = cost;
                    pq.offer(new osolGil(next.to, cost));
                }
            }
        }

        return distance;
    }

    static double[][] wolf() {
        double[][] distance = new double[2][n + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n + 1; j++) {
                distance[i][j] = 1e9;
            }
        }
        distance[0][0] = 0;
        distance[1][0] = 0;

        PriorityQueue<osolGil> pq = new PriorityQueue<>();
        pq.offer(new osolGil(1, 0, true));

        while (!pq.isEmpty()) {
            osolGil current = pq.poll();
            int to = current.to;
            double dist = current.weight;
            boolean fast = current.fast;

            if (fast) {
                if (distance[0][to] < dist) {
                    continue;
                }
            } else {
                if (distance[1][to] < dist) {
                    continue;
                }
            }

            for (osolGil next : li[to]) {
                if (fast) {
                    double cost = dist + next.weight / 2;

                    if (cost < distance[1][next.to]) {
                        distance[1][next.to] = cost;
                        pq.add(new osolGil(next.to, cost, false));
                    }
                } else {
                    double cost = dist + next.weight * 2;

                    if (cost < distance[0][next.to]) {
                        distance[0][next.to] = cost;
                        pq.add(new osolGil(next.to, cost, true));
                    }
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) throws Exception {
        setInputs();

        double[] resFox = fox();
        double[][] resWolf = wolf();

        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (resFox[i] < resWolf[0][i] && resFox[i] < resWolf[1][i]) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
