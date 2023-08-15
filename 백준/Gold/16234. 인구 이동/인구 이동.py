import sys
input = sys.stdin.readline
from collections import deque

n, l, r = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

dx = [0,0,-1,1]
dy = [-1,1,0,0]


def bfs(a,b):
    q = deque()
    tmp = []
    
    q.append([a,b])
    visited[a][b] = True
    tmp.append([a,b])

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny  = x + dx[i], y + dy[i]

            # 그래프 범위 안 & 방문하지 않은 곳일 때
            if 0<=nx<n and 0<=ny<n and visited[nx][ny] == 0:
                # 인접 국가의 인구 차이가 l이상 r이하라면
                if l <= abs(graph[nx][ny] - graph[x][y]) <= r:
                    visited[nx][ny] = True
                    q.append([nx,ny])
                    tmp.append([nx,ny])
    return tmp


ans = 0

while True:
    visited = [[False] * n for _ in range(n)]
    stop = True

    for i in range(n):
        for j in range(n):
            if not visited[i][j]:
                # 만약 방문하지 않은 곳이라면 bfs를 진행한다.
                arr = bfs(i,j)

                # 인구이동이 한 번이라도 있었으면, 이동 후 While문을 한번 더 돌아야 하므로 stop = False로 초기화
                if len(arr) > 1:
                    stop = False
                    # 연합 국가의 인구 평균을 구한다.
                    sm = 0
                    for x, y in arr:
                        sm += graph[x][y]
                    sm //= len(arr)

                # 연합 국가의 인구 초기화
                    for x, y in arr:
                        graph[x][y] = sm

    if stop:
        break      

    ans+=1

print(ans)
