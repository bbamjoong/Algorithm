from collections import deque
import sys
input = sys.stdin.readline


m, n = map(int, input().split())
graph = [list(input().strip()) for _ in range(m)]
visited = [[0] * n for _ in range(m)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

q = deque()

for i in range(m):
    for j in range(n):
        if graph[i][j] == '*':
            q.appendleft((i, j))
        elif graph[i][j] == 'S':
            q.append((i, j))
        elif graph[i][j] == 'D':
            end_x, end_y = i, j


can_end = False
while q:
    x, y = q.popleft()
    if graph[end_x][end_y] == 'S':
        can_end = True
        break

    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]

        if nx < 0 or nx >= m or ny < 0 or ny >= n:
            continue

        # 고슴도치가 다음칸으로 이동할 때 . or D라면 / 이동이 가능하다.
        if graph[x][y] == 'S' and (graph[nx][ny] == '.' or graph[nx][ny] == 'D'):
            graph[nx][ny] = 'S'
            visited[nx][ny] = visited[x][y] + 1
            q.append((nx, ny))

        # 물이 다음칸으로 이동할 때 . or S라면 / 이동이 가능하다.
        # 물이 먼저 움직인다고 가정했을 때 물이 이동하는 방향이 S일 때는 고려안해도된다.
        if graph[x][y] == '*' and (graph[nx][ny] == '.'):
            graph[nx][ny] = '*'
            q.append((nx, ny))

if can_end:
    print(visited[end_x][end_y])
else:
    print("KAKTUS")
