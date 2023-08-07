import sys
input = sys.stdin.readline
from collections import deque

m, n, k = map(int, input().split())
graph = [list(map(int, input().strip())) for _ in range(m)]

visited = [[[0] * n for _ in range(m)] for __ in range(k+1)]


dx = [0,0,-1,1]
dy = [-1,1,0,0]

q = deque()
q.append((0,0,0,1))
for i in range(k+1):
    visited[i][0][0] = 1

while q:
    x,y,wall,cnt = q.popleft()
    if x==m-1 and y==n-1:
        print(cnt)
        exit()

    for i in range(4):
        nx, ny = x+dx[i], y+dy[i]

        # 범위를 벗어나면 continue
        if nx<0 or nx>=m or ny<0 or ny>=n:
            continue

        # 벽을 만났을 때 부순 벽의 개수가 허용 범위 이내 & 방문하지 않은 곳이라면
        if graph[nx][ny] == 1 and wall < k and visited[wall+1][nx][ny] != 1:
            # 낮 밤은 1번 간격으로 바뀐다. 따라서 날짜 % 2의 값에 따라 바뀔 것이다.
            # 낮이라면 벽을 부수고 그 칸(nx,ny)로 이동
            if cnt % 2 == 1:
                visited[wall+1][nx][ny] = 1
                q.append((nx,ny,wall+1,cnt+1))
            # 중요 : 밤이라면 벽을 부수지 않고 원래 칸(x,y에서 cnt+1
            else:
                visited[wall+1][nx][ny] = 2
                q.append((x,y,wall,cnt+1))


        # 빈 공간을 만났을 때 & 방문하지 않은 곳이라면
        if graph[nx][ny] == 0 and visited[wall][nx][ny] != 1:
            visited[wall][nx][ny] = 1
            q.append((nx,ny,wall,cnt+1))

        # 벽을 만났지만 부순 벽의 개수가 허용범위를 벗어난 경우는 아무 조건을 주지 않고 넘긴다.

if not q:
    print(-1)