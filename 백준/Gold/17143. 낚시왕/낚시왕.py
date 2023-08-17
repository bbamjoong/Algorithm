import sys
input = sys.stdin.readline

# 상어 잡기
def get():
    global site
    for i in range(R):
        for j in range(site, site+1):
            if graph[i][j]:
                x = graph[i][j][2]
                graph[i][j] = 0
                return x
    return 0

def shark_move():
    global graph

    graph2 = [[0] * C for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if graph[i][j]!=0:
                # 상어가 있다면 상어의 위치를 옮기고, 새로운 graph에 저장
                nx, ny, ndir = next_dist(i,j,graph[i][j][0], graph[i][j][1])
                # 상어의 위치가 겹친다면
                if graph2[nx][ny]:
                        # 새로 온 상어와 기존에 있던 상어 중에 크기가 큰 상어를 남긴다.
                        graph2[nx][ny] = max(graph2[nx][ny], (graph[i][j][0], ndir, graph[i][j][2]), key=lambda x: x[2])
                else:
                    graph2[nx][ny] = graph[i][j][0], ndir, graph[i][j][2]

    graph = graph2

def next_dist(i,j,speed,dir):
    if dir == up or dir == down:
        # ex) 4행까지 있을 경우 상어는  1 2 3(아래로움직일 때) 2 1 0(위로 움직일 때)의 사이클을 가진다.
        cycle = (R - 1) * 2

        # 항상 0행에서 아래쪽으로 출발하는 조건으로 맞춰줄 것이다.
        # 따라서 위쪽을 보고 있는 상어는 cycle - i
        # 아래쪽을 보고있는 상어는 i를 더해준다.
        if dir == up:
            speed += cycle - i
        else:
            speed += i

        # 여러번 반복하는 것은 불필요한 행위이므로 speed로 cycle을 나눠준다.
        speed %= cycle
        # 만약 speed가 행의 수 이상이라면 상어는 다시 위로 올라갈 것이다.
        if speed >= R:
            return (cycle - speed, j, up)
        return(speed, j, down)

    else:
        cycle = (C - 1) * 2
        if dir == left:
            speed += cycle - j
        else:
            speed += j

        speed %= cycle
        if speed >= C:
            return(i, cycle - speed, left)
        return(i, speed, right)


R,C,m = map(int, input().split())

graph = [[0] * C for _ in range(R)]

for _ in range(m):
    r,c,s,d,z = map(int, input().split()) 
    graph[r-1][c-1] = [s,d,z]

up, down, right, left = 1, 2, 3, 4

site = 0
ans = 0
for j in range(C):
    ans += get()
    shark_move()
    site += 1

print(ans)