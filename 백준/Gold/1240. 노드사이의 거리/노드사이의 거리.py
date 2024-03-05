import sys
input = sys.stdin.readline
import heapq

n, m = map(int, input().split())
li = [[] for _ in range(n + 1)]

for i in range(n - 1):
    a, b, c = map(int, input().split())
    li[a].append([b, c])
    li[b].append([a, c])


def bfs(start):
    q = []
    visited = [False] * (n + 1)

    heapq.heappush(q, [start, 0])
    visited[start] = True

    while q:
        now, sm = heapq.heappop(q)

        if now == end:
            return sm

        for next, weight in li[now]:
            if not visited[next]:
                visited[next] = True
                heapq.heappush(q, [next, sm + weight])


for i in range(m):
    start, end = map(int, input().split())

    ans = bfs(start)
    print(ans)