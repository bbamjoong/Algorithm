import sys

input = sys.stdin.readline
from collections import deque

# 25C7 = 60720이니까 조합 이용해서 탐색해도 됨. 조합 쓰라고 범위 작게줬나?

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

arr = [list(input().strip()) for _ in range(5)]
dasoms = []  # 다솜이네


def dfs(depth, start, yCount):
    global ans

    if yCount >= 4:  # 도연이네는 4명 이상이면 안됨
        return

    if depth == 7:
        # 7명이면 연결되어있는지 확인하는 로직 필요함
        if checkPrincess():
            ans += 1
        return

    for i in range(start, 25):
        x = i // 5
        y = i % 5

        dasoms.append([x, y])

        if arr[x][y] == "Y":
            dfs(depth + 1, i + 1, yCount + 1)
        else:
            dfs(depth + 1, i + 1, yCount)

        dasoms.pop()  # 백트래킹


def checkPrincess():
    visited = [[True] * 5 for _ in range(5)]
    q = deque();
    q.append(dasoms[0])

    for x, y in dasoms:
        visited[x][y] = False

    visited[dasoms[0][0]][dasoms[0][1]] = True
    cnt = 1

    while len(q) > 0:
        x, y = q.pop()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or ny < 0 or nx >= 5 or ny >= 5:  # 범위 벗어나면 안됨
                continue;

            if visited[nx][ny]:  # 재방문 X
                continue;

            cnt += 1
            visited[nx][ny] = True
            q.append([nx, ny])

    return cnt == 7


ans = 0
dfs(0, 0, 0)
print(ans)