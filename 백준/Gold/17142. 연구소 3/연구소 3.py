import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

# 바이러스 위치 저장
virus = []
# 빈칸이 몇개인지 저장
cnt_void = 0

for i in range(n):
    for j in range(n):
        if graph[i][j] == 2:
            virus.append([i,j,0])
        elif graph[i][j] == 0:
            cnt_void += 1
        
dx = [0,0,-1,1]
dy = [-1,1,0,0]

def spread(res):
    global cnt_void, ans
    max_time = 0
    change_void = 0

    # 깊은복사
    tmp = [graph[i][:] for i in range(n)]

    while res:
        x, y, time = res.pop(0)
        tmp[x][y] = 3

        # 이제 빈칸이 없다면 답을 구한다.
        if cnt_void == change_void:
            ans = min(ans, max_time)
            return
        # ans보다 현재의 time이 같거나 크면 가지치기
        if time >= ans:
            return
        
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue

            # 빈칸이라면
            if tmp[nx][ny] == 0:
                change_void += 1    # 빈칸을 바이러스로 바꾼 횟수 + 1
                tmp[nx][ny] = 3     # 빈칸을 바이러스로
                res.append([nx,ny,time+1])
                max_time = time + 1 # 최대시간은 time + 1일 것이다.

            # 비활성바이러스 -> 활성바이러스로
            elif tmp[nx][ny] == 2:
                tmp[nx][ny] = 3
                res.append([nx,ny,time+1])



# 바이러스의 조합을 구하는 dfs()
def comb(idx, cnt, res):
    global m
    if cnt == m:
        res1 = [res[i][:] for i in range(m)]
        spread(res1)
        return


    for i in range(idx, len(virus)):
        res.append(virus[i])
        comb(i+1,cnt+1, res)
        res.pop(-1)

ans = sys.maxsize
comb(0,0,[])

if ans == sys.maxsize:
    print(-1)
else:
    print(ans)