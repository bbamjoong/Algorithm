import sys
from collections import deque

input = sys.stdin.readline

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

knight_dx = [-1, -2, -2, -1, 1, 2, 2, 1]
knight_dy = [-2, -1, 1, 2, -2, -1, 1, 2]


def bfs():
    global w, h, k, ans
    queue = deque([[0, 0, 0]])
    while queue:
        x, y, now_k = queue.popleft()

        if x == h - 1 and y == w - 1:  # 도착
            ans = visited[x][y][now_k]
            return

        for idx in range(4):  # 4방 탐색
            nx = x + dx[idx]
            ny = y + dy[idx]

            if nx < 0 or ny < 0 or nx >= h or ny >= w:  # 범위 벗어나면 X
                continue

            if visited[nx][ny][now_k]: # 방문헀으면 X
                continue

            if arr[nx][ny] == 1: # 1이면 탐색 X
                continue

            queue.append([nx, ny, now_k])
            visited[nx][ny][now_k] = visited[x][y][now_k] + 1

        if now_k < k:
            for idx in range(8):
                nx = x + knight_dx[idx]
                ny = y + knight_dy[idx]

                if nx < 0 or ny < 0 or nx >= h or ny >= w:  # 범위 벗어나면 X
                    continue

                if visited[nx][ny][now_k + 1]: # 방문했으면 X
                    continue

                if arr[nx][ny] == 1:  # 1이면 탐색 X
                    continue

                queue.append([nx, ny, now_k + 1])
                visited[nx][ny][now_k + 1] = visited[x][y][now_k] + 1


k = int(input())
w, h = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(h)]
visited = [[[0] * (k + 1) for _ in range(w)] for _ in range(h)]

visited[0][0][0] = 0

ans = -1
bfs()
print(ans)
