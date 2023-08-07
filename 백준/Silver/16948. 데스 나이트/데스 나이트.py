import sys
input = sys.stdin.readline
from collections import deque

n = int(input())
r1, c1, r2, c2 = map(int, input().split())

dx = [-2,-2,0,0,2,2]
dy = [-1,1,-2,2,-1,1]

visited = [[0] * n for _ in range(n)]

q = deque()
q.append((r1,c1,0))

while q:
    x, y, cnt = q.popleft()
    if x==r2 and y==c2:
        print(cnt)
        break

    for i in range(6):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0<=nx<n and 0<=ny<n and visited[nx][ny] == 0:
            visited[nx][ny] = 1
            q.append((nx,ny,cnt+1))

if len(q) == 0:
    print(-1)