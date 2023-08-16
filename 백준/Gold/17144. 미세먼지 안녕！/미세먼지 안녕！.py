import sys
input = sys.stdin.readline

r,c,t = map(int, input().split())

graph = [list(map(int, input().split())) for _ in range(r)]

# 로봇의 위치 저장
robot = []
for i in range(r):
    for j in range(c):
        if graph[i][j] == -1:
            robot.append([i,j])

# 북 / 동 / 남 / 서
dx = [-1,0,1,0]
dy = [0,1,0,-1]

def condition1():

    # 확산된 먼지의 배열
    dust = [[0] * c for _ in range(r)]

    for x in range(r):
        for y in range(c):
            # 미세먼지가 있을 때
            if graph[x][y] > 0:
                # 5미만이면 확산될 먼지가 없다.
                if graph[x][y] < 5:
                    continue
                else:
                    minus = 0
                    for i in range(4):
                        nx, ny = x + dx[i], y + dy[i]
                        # 배열의 범위 내이고, 공기청정기를 안만난다면
                        if 0 <= nx < r and 0 <= ny < c:
                            if graph[nx][ny] != -1:
                                dust[nx][ny] += graph[x][y]//5
                                minus += graph[x][y]//5
                    # 먼지 확산 후 graph의 먼지에서 확산된 먼지의 양을 빼준다
                    graph[x][y] -= minus
    for x in range(r):
        for y in range(c):
            graph[x][y] += dust[x][y]

def top():
    i = 1
    save = 0
    x, y = robot[0][0], 1

    while True:
        # 다음으로 나아갈 칸
        nx, ny = x + dx[i], y + dy[i]
        # 다음으로 나아갈 칸이 graph의 경계라면?
        if nx == r or ny == c or nx == -1 or ny == -1:
            # 방향을 반시계로 전환
            i = (i-1) % 4
            continue
        # 순환 후 공기청정기에 도착하면
        if x == robot[0][0] and y == robot[0][1]:
            break
        graph[x][y], save = save, graph[x][y]
        x, y = nx, ny

def bottom():
    i = 1
    save = 0
    x, y = robot[1][0], 1

    while True:
        # 다음으로 나아갈 칸
        nx, ny = x + dx[i], y + dy[i]
        # 다음으로 나아갈 칸이 graph의 경계라면?
        if nx == r or ny == c or nx == -1 or ny == -1:
            # 방향을 시계로 전환
            i = (i+1) % 4
            continue
        # 순환 후 공기청정기에 도착하면
        if x == robot[1][0] and y == robot[1][1]:
            break
        graph[x][y], save = save, graph[x][y]
        x, y = nx, ny

for _ in range(t):
    condition1()
    top()
    bottom()

ans = 2
for i in range(r):
    ans += sum(graph[i])
print(ans)