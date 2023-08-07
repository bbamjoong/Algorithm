import sys
input = sys.stdin.readline

m, n = map(int, input().split())
arr = [list(map(int, input().split())) for i in range(m)]
visited = [[False] * n for _ in range(m)]

dx = [0,0,-1,1]
dy = [-1,1,0,0]

mx = 0
def dfs(x, y, sm, cnt):
    global mx
    if cnt == 4:
        mx = max(mx, sm)
        return
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        # 그래프를 벗어나지 않고, 방문하지 않았을 때
        if nx < 0 or nx >= m or ny < 0 or ny >= n or visited[nx][ny]:
            continue

        # cnt가 2일 때 ㅗ 모양을 만들기 위함.
        if cnt == 2:
            visited[nx][ny] = True
            dfs(x,y,sm+arr[nx][ny], cnt + 1)
            visited[nx][ny] = False
        
        # 이후 cnt가 2일때 ㅗ 모양을 만들고 dfs를 빠져나오면 나머지 모양을 만들 수 있다.
        visited[nx][ny] = True
        dfs(nx, ny, sm + arr[nx][ny], cnt + 1)
        visited[nx][ny] = False        

for i in range(m):
    for j in range(n):
        visited[i][j] = True
        dfs(i,j,arr[i][j], 1)
        visited[i][j] = False
    
print(mx)
