import sys
import heapq
input = sys.stdin.readline


n, m, x = map(int, input().split())

li = [[] for _ in range(n + 1)]
reversed_li = [[] for _ in range(n + 1)]

for i in range(m):
    a, b, c = map(int, input().split())
    # 비용, 도착점
    li[a].append([c, b])
    reversed_li[b].append([c, a])


def dijkstra(x, arr):
    global n

    q = []
    heapq.heappush(q, [0, x])  # 비용 0, 시작지점 = x
    d = [1e9] * (n + 1)
    d[0] = 0
    d[x] = 0

    while q:
        dist, now = heapq.heappop(q)

        if d[now] < dist:
            continue

        for next in arr[now]:
            cost = dist + next[0]

            if cost < d[next[1]]:
                d[next[1]] = cost
                heapq.heappush(q, [cost, next[1]])

    return d


dist = dijkstra(x, li)

reversed_dist = dijkstra(x, reversed_li)

ans = 0
for i in range(1, n+1):
    ans = max(ans, dist[i] + reversed_dist[i])

print(ans)
