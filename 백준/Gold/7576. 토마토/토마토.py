import sys
from collections import deque
input = sys.stdin.readline

m, n = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]

dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]

def bfs():
    queue = deque()

    for i in range(n):
        for j in range(m):
            if arr[i][j] == 1:
                queue.append([i, j])    
    
    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0<=nx<n and 0<=ny<m and arr[nx][ny] == 0:
                arr[nx][ny] = arr[x][y] + 1
                queue.append([nx,ny])

bfs()
ans = 0
for i in arr:
    for j in i:
        if j == 0:
            print(-1)
            exit()
    ans = max(ans, max(i))

print(ans-1)