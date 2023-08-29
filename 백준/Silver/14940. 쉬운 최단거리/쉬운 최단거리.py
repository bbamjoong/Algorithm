import sys
input = sys.stdin.readline
from collections import deque

m, n = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(m)]
visited = [[-1] * n for _ in range(m)]

dx = [0,0,-1,1]
dy = [-1,1,0,0]

def bfs(i,j):
    q = deque()
    q.append([i,j])

    visited[i][j] = 0

    while q:
        x, y = q.popleft()
        
        for i in range(4):
            nx, ny = dx[i] + x, dy[i] + y
            
            if 0 <= nx < m and 0 <= ny < n and visited[nx][ny] == -1:
                if graph[nx][ny] == 0: 
                    visited[nx][ny] = 0
                elif graph[nx][ny] == 1:
                    visited[nx][ny] = visited[x][y] + 1
                    q.append([nx,ny])

for i in range(m):
    for j in range(n):
        if graph[i][j] == 2 and visited[i][j] == -1:
            bfs(i,j)

for i in range(m):
    for j in range(n):
        if graph[i][j] == 0:
            print(0, end=' ')
        else:
            print(visited[i][j], end=' ')
    print()