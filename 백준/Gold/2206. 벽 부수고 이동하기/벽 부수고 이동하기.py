import sys
input = sys.stdin.readline
from collections import deque

m, n = map(int, input().split())
graph = [list(map(int, input().strip())) for _ in range(m)]

dx = [0,0,-1,1]
dy = [-1,1,0,0]


q = deque()
visited = [[[0]*2  for _ in range(n)] for _ in range(m)]
q.append((0,0,0,0))
visited[0][0][0] = 1
visited[0][0][1] = 1
while q:
    x, y, wall, cnt = q.popleft()

    if x == m-1 and y == n-1:
        print(cnt+1)
        exit()

    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]

        if nx<0 or nx >=m or ny<0 or ny>=n:
            continue

        # 1을 처음만났을 때.
        if graph[nx][ny] == 1 and wall == 0 and visited[nx][ny][1] == 0:
            visited[nx][ny][1] = 1
            q.append((nx,ny,wall+1,cnt+1))
        
        # 벽을 부수지 않은 상태 / 다음 노선이 0이고 / 방문하지 않은 곳이라면
        if graph[nx][ny] == 0 and visited[nx][ny][0] == 0 and wall == 0:
            visited[nx][ny][0] = 1
            q.append((nx,ny,wall,cnt+1))

        # 벽을 한번 부순 상태 / 다음 노선이 0이고 / 방문하지 않은 곳이라면
        if graph[nx][ny] == 0 and visited[nx][ny][1] == 0 and wall == 1:
            visited[nx][ny][1] = 1
            q.append((nx,ny,wall,cnt+1))
if not q:
    print(-1)