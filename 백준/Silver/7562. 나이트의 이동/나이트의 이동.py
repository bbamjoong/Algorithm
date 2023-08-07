import sys
from collections import deque
input = sys.stdin.readline

t = int(input().strip())

dx = [1,2,2,1,-1,-2,-2,-1]
dy = [2,1,-1,-2,-2,-1,1,2]

def bfs(x,y):
    global cnt
    queue = deque()
    queue.append([x,y])

    while queue:
        x, y = queue.popleft()
        for i in range(8):
            if x == a and y == b:
                return 0
            nx = x + dx[i]
            ny = y + dy[i]
            if 0<=nx<n and 0<=ny<n:
                if arr[nx][ny] == 0:
                    arr[nx][ny] = arr[x][y] + 1
                    queue.append([nx,ny])
                if arr[nx][ny] == 'correct':
                    arr[nx][ny] = arr[x][y] + 1
                    return arr[a][b]                             

for i in range(t):
    n = int(input().strip())
    arr = [[0]*n for _ in range(n)]
    x, y = map(int, input().split())
    a, b = map(int, input().split())
    arr[a][b] = 'correct'
    print(bfs(x,y))