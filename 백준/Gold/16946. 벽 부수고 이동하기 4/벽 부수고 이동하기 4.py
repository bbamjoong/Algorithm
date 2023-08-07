import sys
input = sys.stdin.readline
from collections import deque
from copy import deepcopy

m, n = map(int, input().split())
graph = [list(map(int, input().strip())) for _ in range(m)]
ans = deepcopy(graph)
visited = [[False] * n for _ in range(m)]
zeros = [[0] * n for _ in range(m)]

dx = [-1,1,0,0]
dy = [0,0,-1,1]
name = 2
dict = {}
def bfs(i,j):
    global name, cnt
    q = deque()
    q.append((i,j))

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            if nx<0 or nx>=m or ny<0 or ny>=n:
                continue
            if graph[nx][ny] == 0 and visited[nx][ny] == False:
                visited[nx][ny] = True
                graph[nx][ny] = name
                cnt += 1
                q.append((nx,ny))            




for i in range(m):
    for j in range(n):
        if graph[i][j] == 0:
            visited[i][j] = True
            graph[i][j] = name
            cnt = 1
            bfs(i,j)
            dict[name] = cnt
            name += 1


for x in range(m):
    for y in range(n):
        if graph[x][y] == 1:
            arr = list()
            sm = 1
            for i in range(4):
                nx, ny = x+dx[i], y+dy[i]
                if nx<0 or nx>=m or ny<0 or ny>=n:
                    continue
                if graph[nx][ny] != 1:
                    arr.append(graph[nx][ny]) 
            arr = set(arr)
            for i in arr:
                sm += dict[i]
            ans[x][y] = sm%10

for i in ans:
    for j in i:
        print(j,end='')
    print()
