import sys
input=sys.stdin.readline
from collections import deque
sys.setrecursionlimit(10**6)

n = int(input())
graph = [list(map(int, input().split())) for i in range(n)]
dx = [0,0,1,-1]
dy = [1,-1,0,0]
cnt = 2
ans = sys.maxsize

def dfs(x,y):
    global cnt
    if x<0 or x>=n or y<0 or y>=n:
        return False
    if graph[x][y] == 1:
        graph[x][y] = cnt
        for i in range(4):
            dfs(x+dx[i],y+dy[i])
        return True
    return False

def bfs(num):
    global ans
    check = [[-1]*n for i in range(n)]
    q = deque()

    for i in range(n):
        for j in range(n):
            if graph[i][j] == num:
                q.append((i,j))
                check[i][j] = 0

    while q:
        x,y = q.popleft()
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            # 행렬 밖이면 조건만족 X
            if nx<0 or nx>=n or ny<0 or ny>=n:
                continue
            # 다른 섬에 도착 시 최소값 저장
            if graph[nx][ny] > 0 and graph[nx][ny] != num:
                ans = min(ans,check[x][y])
                return
            # 도착한 위치가 바다라면?
            if graph[nx][ny] == 0 and check[nx][ny] == -1:
                check[nx][ny] = check[x][y] + 1
                q.append((nx,ny))



for i in range(n):
    for j in range(n):
        if dfs(i,j) == True:
            cnt+=1


for i in range(2,cnt):
    bfs(i)

print(ans)
