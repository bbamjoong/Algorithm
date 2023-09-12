import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())

graph = [list(map(int, input().split())) for _ in range(n)]

dx = [0,0,-1,1]
dy = [-1,1,0,0]

def bfs(x, y):
    q = deque()
    q.append([x, y])
    visited = [[False] * m for _ in range(n)]
    visited[x][y] = True

    tmp =[[0] * m for _ in range(n)]

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue

            if not visited[nx][ny]:
                if graph[nx][ny] == 1:
                    tmp[nx][ny] += 1
                
                elif graph[nx][ny] == 0:
                    q.append([nx, ny])
                    visited[nx][ny] = True

    for i in range(n):
        for j in range(m):
            if tmp[i][j] >= 2:
                graph[i][j] = 0

ans = 0
while True:
    stop = False
    for i in range(n):
        if stop:
            break
        for j in range(m):
            if graph[i][j] == 0:
                bfs(i, j)
                ans += 1
                stop = True
                break
    
    stop_while = True
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 1:
                stop_while = False

    if stop_while:
        print(ans)
        break