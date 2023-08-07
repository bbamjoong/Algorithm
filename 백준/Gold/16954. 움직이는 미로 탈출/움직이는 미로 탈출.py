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
    # ★매우 중요★ : sharp가 아래로 한칸 옮겨질 때 한번 옮긴 좌표의 수 만큼 탐색을 해야하기 때문에 범위를 넣어준다.
    for _ in range(len(q)):
        x, y = q.popleft()

        # 맨 아래에서 sharp가 아래로 한칸 옮겨진 new_sharp로 바뀌었을 때 위치가 겹치는지 매핑
        if [x,y] in sharp:
            continue

        # 목표위치에 도달 시 1을 출력
        if x == 0 and y == 7:
            print(1)
            exit()

        for i in range(9):
            nx, ny = x+dx[i], y+dy[i]

            # 범위를 벗어나면 continue
            if nx<0 or nx>=8 or ny<0 or ny>=8:
                continue
            
            # 방문하지 않은 곳이고, 그 곳에 sharp가 없다면 / 방문처리 후 q에 추가
            if visited[nx][ny] == 0 and not [nx,ny] in sharp:
                visited[nx][ny] = 1
                q.append((nx,ny))

    # 아직 맵에 sharp가 있을 때 / 만약 제자리에 있고 싶은데, 그대로 방문처리가 되어있다면 제자리에 있지 못한다.
    # 따라서 visited를 초기화 해준다. -> visited를 완전 제거하는 것 보단 속도가 10배가량 빠름
    if sharp:
        visited = [[0] * 8 for _ in range(8)]

    # 9방향중 한 곳으로 이동 후, sharp를 아래로 한칸 내려준다.
    new_sharp = []
    for x, y in sharp:
        if x < 7:
            new_sharp.append([x+1,y])
    # 기존의 sharp를 아래로 한칸 내려준 new_sharp로 바꿔준다.
    sharp = new_sharp

if not q:
    print(0)
