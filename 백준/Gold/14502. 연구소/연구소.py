import sys
input = sys.stdin.readline
from collections import deque
from copy import deepcopy

m, n = map(int, input().split())

graph = [list(map(int, input().split())) for _ in range(m)]

dx = [-1,1,0,0]
dy = [0,0,-1,1]

def wall(cnt):
    if cnt == 3:
        bfs()
        return
    # 벽을 둘 수 있는 모든 경우의 수를 check
    for i in range(m):
        for j in range(n):
            if graph[i][j] == 0:
                graph[i][j] = 1
                wall(cnt+1)
                graph[i][j] = 0


ans = 0
def bfs():
    global ans

    visited = [[0] * n for _ in range(m)]
    q = deque()
    # 그래프를 깊은복사하여, 깊은복사를 한 그래프에 벽을 세우고 시뮬레이션
    graph1 = deepcopy(graph)
    for i in range(m):
        for j in range(n):
            if graph1[i][j] == 2:
                q.append((i,j))
                visited[i][j] = 1

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
        
            # 다음 좌표가 그래프 내에 있고 / 방문하지 않은 곳이고 / 빈 공간이어야 한다.
            # 벽이 있을 때는 고려할 필요가 없다.
            if 0<=nx<m and 0<=ny<n and visited[nx][ny] == 0 and graph1[nx][ny] == 0:
                visited[nx][ny] = 1
                # 빈 공간을 바이러스로 최신화
                graph1[nx][ny] = 2
                q.append((nx,ny))
    
    # 안전구역 카운팅
    bfs_cnt = 0
    for i in range(m):
        for j in range(n):
            if graph1[i][j] == 0:
                bfs_cnt += 1
    ans = max(ans, bfs_cnt)

wall(0)
print(ans)
