class Solution {
    static int n, m;
    static int ans = Integer.MAX_VALUE;

    static int [][] arr;
    static boolean[][] red, blue;
    static int [] dx = {1, -1, 0, 0};
    static int [] dy = {0, 0, 1, -1};
    static int rx, ry, bx, by;

    static void dfs(int count, int x1, int y1, int x2, int y2) {

        if(arr[x1][y1] == 3 && arr[x2][y2] == 4) { // 완성
            ans = Math.min(ans, count);
            return;
        }
        
        if (arr[x1][y1] == 3) { // 빨간수레는 도착. 파란색 이동
            for(int i = 0; i < 4; i++) {
                int nx = x2 + dx[i];
                int ny = y2 + dy[i];
                
                if(isOutside(nx, ny)) { // 범위체크
                    continue;
                }
                if(blue[nx][ny] || arr[nx][ny] == 3 || arr[nx][ny] == 5) { // 이미 방문한 곳 or 빨간수레가 도착했는데, 그곳에 간 경우 or 벽
                    continue;
                }
                
                blue[nx][ny] = true;
                dfs(count + 1, x1, y1, nx, ny); // 백트래킹
                blue[nx][ny] = false;
            }
        }
        else if(arr[x2][y2] == 4) { // 파란수레는 도착. 빨간색 이동
            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];
                
                if(isOutside(nx, ny)) {
                    continue;
                }
                
                if(red[nx][ny] || arr[nx][ny] == 4 || arr[nx][ny] == 5) {
                    continue;
                }
                
                red[nx][ny] = true;
                dfs(count + 1, nx, ny, x2, y2); // 백트래킹
                red[nx][ny] = false;
            }
        }
        else { // 아무것도 도착 못했으면 같은 방향으로 움직이기
            for(int i = 0; i < 4; i++) { // 빨간공
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];
                
                if(isOutside(nx, ny)) {
                    continue;
                }
                if(red[nx][ny] || arr[nx][ny] == 5) { // 방문했거나, 벽
                    continue;   
                }

                for(int j = 0; j < 4; j++) { // 파란공
                    int nnx = x2 + dx[j];
                    int nny = y2 + dy[j];
                    
                    if (isOutside(nnx, nny)) {
                        continue;
                    }
                    if (blue[nnx][nny] || arr[nnx][nny] == 5) {
                        continue;
                    }
                    
                    if(nx == nnx && ny == nny) { // 빨간공, 파란공의 좌표가 겹치면 안됨.
                        continue;
                    }
                    if((nx == x2 && ny == y2) && (nnx == x1 && nny == y1)) { // 같은곳 도착하면 안됨. ex) 파란공은 아래로, 빨간공은 위로 움직였는데 같은 곳에서 만남.
                        continue;
                    }
                    
                    red[nx][ny] = true;
                    blue[nnx][nny] = true;
                    dfs(count + 1, nx, ny, nnx, nny);
                    red[nx][ny] = false;
                    blue[nnx][nny] = false;
                }
            }
        }
    }
    
    static boolean isOutside(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= m) {
            return true;
        }
        return false;
    }
    

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        arr = new int[n][m];
        red = new boolean[n][m];
        blue = new boolean[n][m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = maze[i][j];
                
                if(arr[i][j] == 1) {
                    red[i][j] = true;
                    rx = i;
                    ry = j;
                }
                
                if(arr[i][j] == 2) {
                    blue[i][j] = true;
                    bx = i;
                    by = j;
                }
            }
        }

        dfs(0, rx, ry, bx, by);

        if(ans == Integer.MAX_VALUE)  {
            return 0;
        }
        return ans;
    }
}