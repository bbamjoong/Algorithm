import sys
input = sys.stdin.readline
from collections import deque
sys.setrecursionlimit(100000)

m, n = map(int, input().split())
graph = [list(map(int,input().strip())) for _ in range(m)]

dx = [-1,1,0,0]
dy = [0,0,-1,1]

def bfs(x,y):
    queue = deque()
    queue.append((x,y))

    while queue:
        a, b = queue.popleft()
        for i in range(4):
            nx = a + dx[i]
            ny = b + dy[i]

            if 0<=nx<m and 0<=ny<n and graph[nx][ny]==1:
                graph[nx][ny] = graph[a][b] + 1
                queue.append((nx,ny))

    return graph[m-1][n-1]

print(bfs(0,0))