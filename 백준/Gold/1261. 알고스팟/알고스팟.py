import sys
input=sys.stdin.readline
from collections import deque

def bfs():
    global arr
    q = deque()
    q.append((0,0))    
    cnt=[[-1]*n for _ in range(m)]
    cnt[0][0]=0
    dx=[0,0,-1,1]
    dy=[1,-1,0,0]

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if 0<=nx<m and 0<=ny<n and cnt[nx][ny]==-1:
                if arr[nx][ny] == 0:
                    cnt[nx][ny] = cnt[x][y]
                    q.appendleft((nx,ny))
                if arr[nx][ny] == 1:
                    cnt[nx][ny] = cnt[x][y] + 1
                    q.append((nx,ny))
    return cnt[m-1][n-1]

n, m = map(int, input().split())
arr = [list(map(int, input().strip())) for _ in range(m)]
print(bfs())