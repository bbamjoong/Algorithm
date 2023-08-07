import sys
input = sys.stdin.readline
from collections import deque

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]

dx = [-1,1,0,0]
dy = [0,0,-1,1]

size, exp = 2, 0
x, y = 0, 0
for i in range(n):
    for j in range(n):
        if board[i][j] == 9:
            x, y = i, j

# 어디로 이동할건지 정하겠음
def bfs(x,y):
    res = []
    visited = [[False]*n for _ in range(n)]
    visited[x][y] = True
    board[x][y] = 0

    q = deque()
    q.append((x,y,0))
    
    cri = 0
    while q:
        x, y, cnt = q.popleft()
        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            # 범위 벗어나면 안됨
            if nx>=n or nx<0 or ny>=n or ny<0:
                continue
            # 방문했거나 상어의 크기보다 물고기의 크기가 크면 안됨
            if visited[nx][ny] or board[nx][ny] > size:
                continue

            # 위 두 조건을 충족시 방문처리
            visited[nx][ny] = True
            # 카운팅을 1 추가한다
            n_cnt = cnt+1


            # 위 두 조건을 만족했고, 물고기의 크기가 문제의 조건을 만족한다면
            # 결과 값 리스트에 원소를 추가해줄 것이다.
            if 0 < board[nx][ny] < size:

                # queue에서 가장 가까운 위치에서 조건을 만족하는 원소가 들어온다.
                # 이 때 cnt의 기준을 맨 처음 들어온 원소를 기준으로 한다.
                if cri == 0:
                    cri = n_cnt
                    res.append((nx,ny,n_cnt))
                # 이 후 가장 가까운 물고기만 추가해야하기 때문에 cnt가 cri보다 크면
                # res에 append하지 않는다.
                elif n_cnt == cri:
                    res.append((nx,ny,n_cnt))
                
            q.append((nx,ny,n_cnt))            

    return res

ans = 0
while True:
    res = bfs(x,y)

    # 먹을 수 있는 물고기가 없다면? 종료
    if not res:
        break

    res.sort()

    x,y,n_cnt = res[0]
    ans += n_cnt

    board[x][y] = 0
    exp+=1
    if size == exp:
        size +=1
        exp = 0

print(ans)