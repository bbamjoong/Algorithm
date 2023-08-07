import sys
input = sys.stdin.readline
from collections import deque

graph = [list(input().strip()) for _ in range(8)]
visited = [[0] * 8 for _ in range(8)]

dx = [-1,1,0,0,-1,-1,1,1,0]
dy = [0,0,-1,1,-1,1,-1,1,0]

sharp = []
for i in range(8):
    for j in range(8):
        if graph[i][j] == '#':
            sharp.append([i,j])

q = deque()
q.append((7,0))

while q:
    for _ in range(len(q)):
        x, y = q.popleft()

        if [x,y] in sharp:
            continue

        if x == 0 and y == 7:
            print(1)
            exit()

        for i in range(9):
            nx, ny = x+dx[i], y+dy[i]
            if nx<0 or nx>=8 or ny<0 or ny>=8:
                continue
            
            if visited[nx][ny] == 0 and not [nx,ny] in sharp:
                visited[nx][ny] = 1
                q.append((nx,ny))

    if sharp:
        visited = [[0] * 8 for _ in range(8)]

    new_sharp = []
    for x, y in sharp:
        if x < 7:
            new_sharp.append([x+1,y])
    sharp = new_sharp

if not q:
    print(0)