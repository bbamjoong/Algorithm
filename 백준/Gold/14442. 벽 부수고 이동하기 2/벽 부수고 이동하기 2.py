import sys
input = sys.stdin.readline
from collections import deque

m, n, k = map(int, input().split())
graph = [list(map(int, input().strip())) for _ in range(m)]
#공부하면서 알게된 3차원 배열에 대한 중요한 사항
# ex) [1000][1000][11] vs [11][1000][1000]
# visited[1000][1000][11]의 경우 11*1000의 배열이 1000개가 있다. -> 1000번을 매핑해야됨.
# visited[11][1000][1000]의 경우 1000*1000의 배열이 11개가 있다. -> 11번을 매핑하면됨.

# 따라서 m * n 배열을 k+1개로 쌓아올린 것이 속도가 더 빠르다.
visited = [[[0] * n for _ in range(m)] for __ in range(k+1)]

# (k+1) * n의 배열을 m번 쌓아올린 것은 속도가 더 느리다.
# visited = [[[0] * (k+1) for _ in range(n)] for __ in range(m)]

# -> 최악의 경우 문제의 조건에서 n = 1000, m = 1000, k = 10이다.


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
        if graph[nx][ny] == 1 and wall < k and visited[wall+1][nx][ny] == 0:
            visited[wall+1][nx][ny] = 1
            q.append((nx,ny,wall+1,cnt+1))

        # 빈 공간을 만났을 때 & 방문하지 않은 곳이라면
        if graph[nx][ny] == 0 and visited[wall][nx][ny] == 0:
            visited[wall][nx][ny] = 1
            q.append((nx,ny,wall,cnt+1))

        # 벽을 만났지만 부순 벽의 개수가 허용범위를 벗어난 경우는 아무 조건을 주지 않고 넘긴다.

if not q:
    print(-1)