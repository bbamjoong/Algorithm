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

def south():
    global x, y
    nx, ny = x+1, y
    if nx < 0 or nx >= n or ny < 0 or ny >= m:
        return
    dice[0], dice[1], dice[2], dice[3] = dice[3], dice[0], dice[1], dice[2]
    x, y = nx, ny
    compare()

    print(dice[1])

def north():
    global x, y
    nx, ny = x-1, y
    if nx < 0 or nx >= n or ny < 0 or ny >= m:
        return
    dice[0], dice[1], dice[2], dice[3] = dice[1], dice[2], dice[3], dice[0]
    x, y = nx, ny
    compare()

    print(dice[1])

def east():
    global x, y
    nx, ny = x, y+1
    if nx < 0 or nx >= n or ny < 0 or ny >= m:
        return
    dice[1], dice[3], dice[4], dice[5] = dice[4], dice[5], dice[3], dice[1]
    x, y = nx, ny
    compare()

    print(dice[1])

def west():
    global x, y
    nx, ny = x, y-1
    if nx < 0 or nx >= n or ny < 0 or ny >= m:
        return
    dice[1], dice[3], dice[4], dice[5] = dice[5], dice[4], dice[1], dice[3]
    x, y = nx, ny
    compare()

    print(dice[1])

for i in dir:
    if i == 1:
        east()

    elif i == 2:
        west()

    elif i == 3:
        north()

    elif i == 4:
        south()