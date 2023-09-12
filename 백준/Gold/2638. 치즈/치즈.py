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

    # 치즈가 공기와 몇번 맞닿았는지 체크하는 리스트
    tmp =[[0] * m for _ in range(n)]

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            
            # 방문하지 않은 곳
            if not visited[nx][ny]:
                # 치즈라면? -> tmp += 1
                if graph[nx][ny] == 1:
                    tmp[nx][ny] += 1
                
                # 공기라면? deque에 원소 추가
                elif graph[nx][ny] == 0:
                    q.append([nx, ny])
                    visited[nx][ny] = True

    # 치즈가 공기와 두번이상 맞닿았다면 제거
    for i in range(n):
        for j in range(m):
            if tmp[i][j] >= 2:
                graph[i][j] = 0

ans = 0
while True:

    # 치즈가 놓이지 않은 곳에서부터 bfs시작
    # (근데 가장자리에 치즈가 놓이지 않으니까 bfs(0,0)로 코드를 짜도 된다.)
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
    
    # 모든칸이 0이라면 종료
    stop_while = True
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 1:
                stop_while = False

    if stop_while:
        print(ans)
        break