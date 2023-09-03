import sys
input = sys.stdin.readline
from collections import deque

n,m,h = map(int,input().split())
graph = []
q = deque([])

for z in range(h):
    tmp = []
    for x in range(m):
        tmp.append(list(map(int, input().split())))
        for y in range(n):
            if tmp[x][y]==1:
                q.append([x,y,z])
    graph.append(tmp)

dx = [-1,1,0,0,0,0]
dy = [0,0,1,-1,0,0]
dz = [0,0,0,0,-1,1]

while q:
    x, y, z = q.popleft()

    for i in range(6):
        nx, ny, nz = x + dx[i], y + dy[i], z + dz[i]

        if nx < 0 or nx >= m or ny < 0 or ny >= n or nz < 0 or nz >= h:
            continue
        
        if graph[nz][nx][ny] == 0 :
            q.append([nx,ny,nz])
            graph[nz][nx][ny] = graph[z][x][y] + 1

cnt = 0
for i in graph:
    for j in i:
        for k in j:
            if k == 0:
                print(-1)
                exit(0)
            else:
                cnt = max(cnt, k)

print(cnt-1)