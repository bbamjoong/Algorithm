import heapq
import sys

input = sys.stdin.readline

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]


def dijkstra(x):
    global n
    distance = [1e9] * (n * n)
    distance[x] = arr[x // n][x % n]

    q = []
    heapq.heappush(q, [arr[x // n][x % n], x])

    while q:
        dist, now = heapq.heappop(q)

        if distance[now] < dist:
            continue

        for i in li[now]:
            cost = dist + i[1]

            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, [cost, i[0]])

    return distance

idx = 1
while True:
    n = int(input())
    if n == 0:
        break;

    li = [[] for _ in range(n * n)]  # 2차원배열이니까 n*n개
    arr = [list(map(int, input().split())) for _ in range(n)]

    for i in range(n * n):
        x = i // n;
        y = i % n;
        for dir in range(4):
            nx = x + dx[dir];
            ny = y + dy[dir];

            # 범위 벗어나면 종료
            if nx < 0 or ny < 0 or nx >= n or ny >= n:
                continue;
            # 시작점에 -> [도착점, 가중치]
            li[i].append([nx * n + ny, arr[nx][ny]])

    res = dijkstra(0) # 0, 0이 시작점
    print(f"Problem {idx}: {res[-1]}")
    idx += 1
