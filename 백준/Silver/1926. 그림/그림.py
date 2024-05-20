from collections import deque
import sys
input = sys.stdin.readline

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]


def bfs(x, y):
    li[x][y] = 2

    size = 1
    q = deque()
    q.append([x, y])

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = dx[i] + x
            ny = dy[i] + y

            if nx < 0 or ny < 0 or nx >= n or ny >= m: # 범위 벗어나면 안됨
                continue

            if li[nx][ny] == 0 or li[nx][ny] == 2: # 방문한 곳이거나, 색칠이 안된 부분은 안됨
                continue

            q.append([nx, ny])
            li[nx][ny] = 2
            size += 1

    return size


n, m = map(int, input().split())
li = [list(map(int, input().split())) for _ in range(n)]

cnt = 0
ans = 0

for i in range(n):
    for j in range(m):
        if li[i][j] == 1:
            cnt += 1 # 그림의 개수
            res = bfs(i, j)
            ans = max(res, ans)

print(cnt)
print(ans)