import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
  
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
  
    static int n;
    static int m;
    static int k;
  
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
  
    static PriorityQueue<Cell> cells;
    static ArrayDeque<Cell> q = new ArrayDeque<>();
    static boolean[][] visited;
  
    static class Cell implements Comparable<Cell> {
        int x;
        int y;
        int size;
        int time;
  
        public Cell(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.time = 0;
        }
  
        @Override
        public int compareTo(Cell c) { // //사이즈가 큰 순서대로 정렬
            return -Integer.compare(this.size, c.size);
        }
    }
  
    static void setInputs() throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
  
        int DEFAULT = k / 2; // 줄기세포 배양 시작 지점
  
        cells = new PriorityQueue<>();
        visited = new boolean[n + k][m + k];
  
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int cellSize = Integer.parseInt(st.nextToken());
                if (cellSize != 0) {
                    visited[DEFAULT + i][DEFAULT + j] = true;
                    cells.add(new Cell(DEFAULT + i, DEFAULT + j, cellSize));
                }
            }
        }
    }
  
    static void cultivateCells() {
        while (!cells.isEmpty()) {
            Cell cell = cells.poll();
  
            if (cell.time == cell.size) {
                for (int i = 0; i < 4; i++) {
                    int nx = cell.x + dx[i];
                    int ny = cell.y + dy[i];
  
                    if (visited[nx][ny]) { // 이미 세포 있으면 배양 못함
                        continue;
                    }
                    visited[nx][ny] = true;
                    q.add(new Cell(nx, ny, cell.size));
                }
            }
  
            cell.time++; // 살아있는 세포만 PriorityQueue에 넣을 것이기 때문에 시간을 먼저 더해주고
  
            if (cell.time == cell.size * 2) { // 조건에 해당되면 세포를 죽인다.
                continue; // 세포 죽음
            }
            q.add(cell);
        }
  
        while (!q.isEmpty()) {
            cells.add(q.poll());
        }
    }
  
  
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            sb.append("#").append(tc).append(" ");
            setInputs();
  
            for (int i = 0; i < k; i++) { // k회 배양
                cultivateCells();
            }
  
            sb.append(cells.size()).append("\n");
        }
        System.out.println(sb);
    }
}