import sys
input = sys.stdin.readline

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
direction = ['L', 'R', 'U', 'D']

x, y = map(int, input().split())
arr = [[i for i in input()] for _ in range(x)]
visited = [[-1 for _ in range(y)] for _ in range(x)]


def move(x, y, num):
    global ans
    if visited[x][y] != -1:  # 방문한 적이 이미 있다.
        if visited[x][y] == num:  # 나와 num이 같다면 사이클
            ans += 1
        return
    # 방문한 적이 없으면 visited 갱신
    visited[x][y] = num
    dir = direction.index(arr[x][y])
    nx = x + dx[dir]
    ny = y + dy[dir]

    move(nx, ny, num)


num = 0
ans = 0
for i in range(x):
    for j in range(y):
        move(i, j, num)
        num += 1

print(ans)