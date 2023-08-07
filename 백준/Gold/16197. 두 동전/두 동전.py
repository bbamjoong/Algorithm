import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
board = [list(input().strip()) for _ in range(n)]
coin = []

for i in range(n):
    for j in range(m):
        if board[i][j] == 'o':
            coin.append(i)
            coin.append(j)

dx = [0,0,-1,1]
dy = [-1,1,0,0]

def bfs():
    q = deque([(coin[0],coin[1],coin[2],coin[3],0)])
    while q:
        x1,y1,x2,y2,cnt = q.popleft()
        if cnt >= 10:
            break

        for i in range(4):
            nx1, ny1 = x1 + dx[i], y1 + dy[i]
            nx2, ny2 = x2 + dx[i], y2 + dy[i]

            if not (0 <= nx1 < n and 0 <= ny1 < m) and not (0 <= nx2 < n and 0 <= ny2 < m):
                continue    

            if not (0 <= nx1 < n and 0 <= ny1 < m) or not (0 <= nx2 < n and 0 <= ny2 < m):
                return cnt + 1        
            

            if board[nx1][ny1] == '#':
                nx1, ny1 = x1, y1
            if board[nx2][ny2] == '#':
                nx2, ny2 = x2, y2
            if nx1 == x1 and nx2 == x2 and ny1 == y1 and ny2 == y2:
                pass
            else:
                q.append((nx1,ny1,nx2,ny2,cnt+1))
    return -1

print(bfs())