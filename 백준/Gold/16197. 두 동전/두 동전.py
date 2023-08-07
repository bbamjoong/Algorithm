import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
board = [list(input().strip()) for _ in range(n)]
coin = []

# 동전의 좌표 저장
for i in range(n):
    for j in range(m):
        if board[i][j] == 'o':
            coin.append(i)
            coin.append(j)

dx = [0,0,-1,1]
dy = [-1,1,0,0]

def bfs():
    # 동전1의 x좌표, y좌표, 동전2의 x좌표, y좌표, 움직인 횟수
    q = deque([(coin[0],coin[1],coin[2],coin[3],0)])
    while q:
        x1,y1,x2,y2,cnt = q.popleft()
        # 10회이상 움직였을 시 break
        if cnt >= 10:
            break

        for i in range(4):
            nx1, ny1 = x1 + dx[i], y1 + dy[i]
            nx2, ny2 = x2 + dx[i], y2 + dy[i]
            # 동전 두개가 board를 벗어나면 안된다.
            if not (0 <= nx1 < n and 0 <= ny1 < m) and not (0 <= nx2 < n and 0 <= ny2 < m):
                continue    
            # 만약 동전 하나만 board를 벗어나게 된다면 cnt+1를 반환한다.
            if not (0 <= nx1 < n and 0 <= ny1 < m) or not (0 <= nx2 < n and 0 <= ny2 < m):
                return cnt + 1        
            
            # 벽을 만났을 땐 움직이지 못하기 때문에 다음에 움직일 좌표 (nx,ny)를 원래 좌표 (x,y)로 수정
            if board[nx1][ny1] == '#':
                nx1, ny1 = x1, y1
            if board[nx2][ny2] == '#':
                nx2, ny2 = x2, y2
            
            # 동전 두개가 같은위치에 있으면 안된다.
            if nx1 == x1 and nx2 == x2 and ny1 == y1 and ny2 == y2:
                pass
            else:
                # 동전 두개가 둘다 board에 위치하면서 다른 위치에 있다면
                q.append((nx1,ny1,nx2,ny2,cnt+1))
    
    # 10회 이상 움직였을 시 -1반환
    return -1

print(bfs())
