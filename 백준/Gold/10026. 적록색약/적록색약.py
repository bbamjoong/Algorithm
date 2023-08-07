import sys
input = sys.stdin.readline
from collections import deque

n = int(input())
graph = [list(input().strip()) for _ in range(n)]
visited = [[False] * n for _ in range(n)]

dx, dy = [-1,1,0,0], [0,0,-1,1]
normal_cnt, rg_cnt = 0, 0

def bfs(x, y):
    q = deque()
    q.append((x, y))
    visited[x][y] = True
    color = graph[x][y]
    while q:
        x, y = q.popleft()        
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if (0 <= nx < n) and (0 <= ny < n):
                if graph[nx][ny] == color and visited[nx][ny] == False:
                    visited[nx][ny] = True
                    q.append((nx,ny))

for i in range(n):
    for j in range(n):
        if visited[i][j] == False:
            bfs(i,j)
            normal_cnt += 1

visited = [[False] * n for _ in range(n)]

for i in range(n):
    for j in range(n):
        if graph[i][j] == 'G':
            graph[i][j] = 'R'

for i in range(n):
    for j in range(n):
        if visited[i][j] == False:
            bfs(i,j)
            rg_cnt += 1

print(normal_cnt, rg_cnt)