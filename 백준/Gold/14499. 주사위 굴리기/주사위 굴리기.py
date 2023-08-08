import sys
input=sys.stdin.readline

n, m, x, y, k= map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
dir = list(map(int, input().split()))

dx = [0,0,-1,1]
dy = [1,-1,0,0]

dice = [0, 0, 0, 0, 0, 0]

def compare():
    global x, y
    if graph[x][y] == 0:
        graph[x][y] = dice[3]

    elif graph[x][y] != 0:
        dice[3] = graph[x][y]
        graph[x][y] = 0

def turn(where):
    global dice
    d1, d2, d3, d4, d5, d6 = dice
    # 동쪽
    if where == 1:
        dice = [d1, d5, d3, d6, d4, d2]
    # 서쪽
    elif where == 2:
        dice = [d1, d6, d3, d5, d2, d4]
    # 북쪽
    elif where == 3:
        dice = [d2, d3, d4, d1, d5, d6]
    # 남쪽
    elif where == 4:
        dice = [d4, d1, d2, d3, d5, d6]

    compare()
    print(dice[1])

for i in dir:
    nx, ny = x + dx[i-1], y + dy[i-1]

    # graph범위 안에 있을 시 주사위 굴리기
    if 0 <= nx < n and 0 <= ny < m:
        # 좌표 최신화
        x, y = nx, ny
        turn(i)