import sys
input = sys.stdin.readline
from collections import deque

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

n, m = map(int, input().split())
arr = [list(input().rstrip()) for _ in range(n)]

# z축 : 열쇠가 없으면 0, 열쇠가 있으면 1로 비트를 활성화 할 것임.
visited = [[[False] * m for _ in range(n)] for _ in range(1 << 6)]

sx, sy = 0, 0


for i in range(n):
    for j in range(m):
        if arr[i][j] == "0":
            sx, sy = i, j
            arr[i][j] = "."

visited[0][sx][sy] = True

ans = 1e9 # 정답


def bfs():
    global ans, sx, sy

    q = deque()
    q.append([sx, sy, 0, 0])

    while len(q) != 0:
        x, y, cnt, key = q.popleft()

        if cnt >= ans:
            return

        if arr[x][y] == "1":
            ans = min(ans, cnt)
            return

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            new_key = key

            if nx < 0 or ny < 0 or nx >= n or ny >= m:  # 범위를 벗어나면 안된다.
                continue

            ### 열쇠, 문, 벽, 빈칸(처리 안해줄거임)
            if ord('a') <= ord(arr[nx][ny]) <= ord('f'):  # 열쇠를 얻는 것이다.
                num = ord(arr[nx][ny]) - ord('a')
                # 열쇠를 이미 얻었으면 더 먹을 필요는 없음.
                if (new_key & (1 << num)) <= 0:
                    new_key |= 1 << num

            elif arr[nx][ny] == '#':  # 벽은 이동 못합니다.
                continue

            elif ord('A') <= ord(arr[nx][ny]) <= ord('F'):  # 문을 만났습니다.
                validate_num = ord(arr[nx][ny]) - ord('A')
                if (new_key & (1 << validate_num)) <= 0:  # 비트 활성화 안되어 있으면 안됩니다.
                    continue

            # 이미 방문한 곳이면 안됨
            if visited[new_key][nx][ny]:
                continue

            visited[new_key][nx][ny] = True
            q.append([nx, ny, cnt + 1, new_key])

bfs()

if ans == 1e9:
    ans = -1

print(ans)
