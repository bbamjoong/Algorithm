import sys
input = sys.stdin.readline
import heapq

n, m, x = map(int, input().split())

graph = [[] for _ in range(m+1)]
for i in range(m):
    a, b, c = map(int, input().split())
    # a -> b까지의 비용 c
    graph[a].append([b, c])

def dijkstra(x):
    q = []
    heapq.heappush(q, (0, x))
    distance = [1e9] * (n+1)
    distance[x] = 0

    while q:
        dist, now = heapq.heappop(q)

        # 기존의 거리가 더 짧다면 continue
        if distance[now] < dist:
            continue

        for i in graph[now]:
            cost = dist + i[1]
            # 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧다면
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost,i[0]))

    return distance

# x의 집에서 본인의 집으로 돌아갈 때의 거리
dist = dijkstra(x)
dist[0] = 0

# 다같이 x의 집으로 모일 때의 거리
for i in range(1, n+1):
    if i != x:
        res = dijkstra(i)
        # x의 집에서 본인의 집으로 돌아갈 때의 거리 += 다같이 x의 집으로 모일 때의 거리
        dist[i] += res[x]

print(max(dist))