import sys
input = sys.stdin.readline
from collections import deque

m, n = map(int, input().split())
board = [list(input().strip()) for _ in range(m)]

for i in range(m):
    for j in range(n):
        if board[i][j] == 'R':
            r1,r2 = i,j
        elif board[i][j] == 'B':
            b1,b2 = i,j

dy = [-1,1,0,0]
dx = [0,0,-1,1]


q = deque()
q.append((r1,r2,b1,b2,0))
visited = []
visited.append((r1,r2,b1,b2))

ans = False

def move(x, y, dx, dy):
    cnt = 0
    while board[x+dx][y+dy] != '#' and board[x][y] != 'O':
        x += dx
        y += dy
        cnt += 1
    return x, y, cnt

while q:
    rx, ry, bx, by, cnt = q.popleft()

    # cnt가 10 초과일 경우 -1 호출
    if cnt>10:
        break

    # R이 O에 다다를 경우 cnt를 호출
    if board[rx][ry] == 'O':
        print(cnt)
        ans = True
        break

    for i in range(4):
        nrx, nry, r_move = move(rx, ry, dx[i], dy[i])
        nbx, nby, b_move = move(bx, by, dx[i],dy[i])
        # O에 구슬이 도달하는 경우는
        # 1. 빨간색만 도달한다.
        # 2. 파란색만 도달한다.
        # 3. 빨간색, 파란색이 동시에 도달한다.
        # 이 때 1번을 제외하고는 정답이 아니기 때문에 파란색이 O에
        # 도달 시 queue에 넣어주지 않기 위함임.
        if board[nbx][nby] == 'O':
            continue

        # R, B가 같은 좌표에 도달 시 더 많이 움직인 공이 뒤에 있었던 것
        # 더 많이 움직인 공을 한 좌표 뒤로 움직여준다.
        if nrx == nbx and nry == nby:
            if r_move>b_move:
                nrx -=dx[i]
                nry -=dy[i]
            else:
                nbx -=dx[i]
                nby -=dy[i]

        # R,B가 같은 좌표로 다시 돌아오게 되면 경우가 많아지므로 가지치기
        if not (nrx,nry,nbx,nby) in visited:
            visited.append((nrx,nry,nbx,nby))
            # queue에 새로운 좌표를 입력하여 bfs 진행
            q.append((nrx,nry,nbx,nby,cnt+1))

if ans == False:
    print(-1)
