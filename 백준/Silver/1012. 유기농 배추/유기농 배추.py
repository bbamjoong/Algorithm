import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)
dx = [0,0,-1,1]
dy = [-1,1,0,0]

def dfs(x, y):
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]

        if nx < 0 or nx >= m or ny < 0 or ny >=n:
            continue

        if arr[nx][ny] == 1:
            arr[nx][ny] = 2
            dfs(nx, ny)

t = int(input())
for i in range(t):
    n, m, k = map(int, input().split())
    arr = [[0] * n for _ in range(m)]
    for _ in range(k):
        y, x = map(int, input().split())
        arr[x][y] = 1

    # visited를 따로 만들지 않고, `1` 즉 배추를 탐색하면 그 배추를 2로 바꾸자.
    cnt = 0
    for x in range(m):
        for y in range(n):
            if arr[x][y] == 1:
                cnt += 1
                arr[x][y] = 2
                dfs(x, y)
    
    print(cnt)