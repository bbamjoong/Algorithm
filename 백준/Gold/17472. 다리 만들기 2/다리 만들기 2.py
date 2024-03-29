import sys

input = sys.stdin.readline
from collections import deque

### 1. bfs로 그룹 나누기
### 2. 다리 전부 저장
### 3. 크루스칼
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]


def bfs(x, y):
    q = deque()
    visited[x][y] = True
    arr[x][y] = group_number
    q.append([x, y])

    while len(q) != 0:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= m:  # 범위 벗어나면 안됨
                continue

            if arr[nx][ny] == 0:  # 0인 곳은 가면 안됨
                continue

            if visited[nx][ny]:  # 이미 방문한 곳도 안감
                continue

            visited[nx][ny] = True
            arr[nx][ny] = group_number
            q.append([nx, ny])


def find_edges(x, y, num):
    for i in range(4):  # 동, 서, 남, 북 방향으로 쭉 나아갑니다.
        nx, ny = x, y
        move_cnt = 0
        while True:
            nx += dx[i]
            ny += dy[i]
            move_cnt += 1
            # 범위 벗어나도 종료
            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                break
            # 같은 번호 만나면 바로 종료
            if arr[nx][ny] == num:
                break
            # 0이면(바다) 일단 나아감
            elif arr[nx][ny] == 0:
                continue
            # 다른 섬 만나면 종료 후 edge 만들어주기. 단 길이가 2 이상이어야해
            else:
                if move_cnt >= 3:  # 섬에서 섬에서 이동하는 것이어서 다리 길이보다 1 큼
                    edges.append([x + dx[i], y + dy[i], nx - dx[i], ny - dy[i], num, arr[nx][ny], i, move_cnt - 1])
                    break
                else:
                    break


def find(x):
    if x == parent[x]:
        return x
    parent[x] = find(parent[x])  # 경로 압축
    return parent[x]


def union(a, b):
    global cnt
    a = find(a);
    b = find(b);

    if a == b:
        return;

    # 어떤 노드의 부모가 0으로 바뀌게 되면 cnt를 증가시켜줍니다.
    if a == 0 or b == 0:
        cnt += 1

    if a < b:
        parent[b] = a;
    elif a > b:
        parent[a] = b;


def update_visited(start_x, start_y, end_x, end_y, direction):
    x = start_x
    y = start_y
    visited[x][y] += 1

    nx = x
    ny = y
    while True:
        nx += dx[direction]
        ny += dy[direction]
        visited[nx][ny] += 1

        if nx == end_x and ny == end_y:
            break


n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
visited = [[False] * m for _ in range(n)]

### 1단계 시작
group_number = 1
for i in range(n):
    for j in range(m):
        if arr[i][j] == 1 and not visited[i][j]:  # 섬이고 아직 방문 안한 곳이다.
            bfs(i, j)
            group_number += 1
### 1단계 끝

### 2단계 시작
edges = []  # 간선의 정보 저장 [시작x, 시작y, 도착x, 도착y, 시작섬 number, 도착섬 number, direction, cost]
for i in range(n):
    for j in range(m):
        num = arr[i][j]
        if num != 0:  # 섬이면 엣지찾기 시작
            find_edges(i, j, num)
### 2단계 끝

### 3단계 시작
parent = [i for i in range(group_number)]
edges.sort(key=lambda x: x[-1])

visited = [[0] * m for _ in range(n)]  # 저장
for start_x, start_y, end_x, end_y, start_num, end_num, direction, cost in edges:
    if find(start_num) != find(end_num):
        union(start_num, end_num)
        update_visited(start_x, start_y, end_x, end_y, direction)

### 답 구하기

cnt = 0
for i in range(1, group_number):
    if i == parent[i]:
        cnt += 1

if cnt > 1:  # 그룹이 여러개면
    print(-1)
else:
    ans = 0
    for i in range(n):
        for j in range(m):
            ans += visited[i][j]

    print(ans)
