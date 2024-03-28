import sys
input = sys.stdin.readline
from collections import deque


def bfs():
    q = deque()
    q.append((start_x, start_y))
    visited = [False for _ in range(n + 1)]

    while q:
        x, y = q.popleft()

        # 편의점에 도달할 수 있으면 happy
        if abs(x - end_x) + abs(y - end_y) <= 1000:
            return "happy"

        final_store = [-1, -1] # 현재 지점에서 가장 먼 편의점
        for i in range(n):
            if not visited[i]:
                nx, ny = stores[i]
                if abs(x - nx) + abs(y - ny) <= 1000:
                    visited[i] = True
                    final_store = [nx, ny]
                    q.append((nx, ny))

        if final_store[0] != -1: # final_store에 값이 들어왔으면
            q.append(final_store)

    return "sad"


t = int(input())

for _ in range(t):
    n = int(input())  # 편의점의 개수
    start_x, start_y = map(int, input().split())

    stores = []
    for _ in range(n):
        x, y = map(int, input().split())
        stores.append([x, y])

    end_x, end_y = map(int, input().split())

    print(bfs())
