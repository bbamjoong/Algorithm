import sys
input = sys.stdin.readline
from collections import deque

m, n = map(int, input().split())

arr = []

start_x, start_y = 0, 0
for i in range(m):
    li = list(input().rstrip())
    arr.append(li)
    for j in range(n):
        if li[j] == 'I':
            start_x, start_y = i, j

dx = [0,0,-1,1]
dy = [-1,1,0,0]

def bfs(x, y):
    q = deque()
    q.append([x, y])
    visited = [[False] * n for _ in range(m)]

    ans = 0
    while q:
        x, y = q.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or nx >= m or ny < 0 or ny >= n:
                continue

            if arr[nx][ny] != 'X' and not visited[nx][ny]:
                q.append([nx,ny])
                visited[nx][ny] = True

                if arr[nx][ny] == 'P':
                    ans += 1
    
    return ans

ans = bfs(start_x, start_y)
print(ans if ans > 0 else "TT")