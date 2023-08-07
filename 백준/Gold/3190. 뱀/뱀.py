import sys
input = sys.stdin.readline
from collections import deque

n = int(input())
k = int(input())

board = [[0] * n for _ in range(n)]
for i in range(k):
    x, y = map(int, input().split())
    board[x-1][y-1] = 1

visited = [[False] * n for _ in range(n)]

l = int(input())
dict = {}
for i in range(l):
    a, b = map(str, input().split())
    dict[int(a)] = b

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

x, y = 0, 0
cnt = 0
direction = 0

q = deque()
q.append((0,0))
visited[0][0] = True

while True:
    cnt += 1
    x += dx[direction]
    y += dy[direction]

    if x < 0 or x >= n or y < 0 or y >= n:
        break

    if board[x][y] == 1 and visited[x][y] == False:
        board[x][y] = 0
        q.append((x,y))
        visited[x][y] = True

        if cnt in dict:
            if dict[cnt] == 'D':
                direction = (direction + 1) % 4
            elif dict[cnt] == 'L':
                direction = (direction - 1) % 4
    
    elif board[x][y] == 0 and visited[x][y] == False:
        q.append((x,y))
        visited[x][y] = True
        nx, ny = q.popleft()
        visited[nx][ny] = False

        if cnt in dict:
            if dict[cnt] == 'D':
                direction = (direction + 1) % 4
            elif dict[cnt] == 'L':
                direction = (direction - 1) % 4        
    
    else:
        break
print(cnt)