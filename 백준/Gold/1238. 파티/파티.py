import sys
input = sys.stdin.readline
import heapq

# dijkstra는 O(MlogN) 의 시간 복잡도를 가진다.
# (n-1)개의 노드에서 x 노드로 가는 거리 측정, x노드에서 (n-1)개의 노드로 가는 거리 측정 시
# O( ((N-1) + 1) * MlogN ) = O(NM logN)의 시간 복잡도를 가진다.

# 하지만 그래프의 방향을 뒤집을 시 (n-1)개의 노드에서 x로 가는 거리 측정을 1번만 하면 되기 때문에
# O(2M logN)의 시간 복잡도를 가진다.
n, m, x = map(int, input().split())

graph = [[] for _ in range(n+1)]
reversed_graph = [[] for _ in range(n+1)]

for i in range(m):
    a, b, c = map(int, input().split())
    # a -> b까지의 비용 c
    graph[a].append([b, c])
    # 거꾸로
    reversed_graph[b].append([a, c])

def dijkstra(x, arr):
    q = []
    heapq.heappush(q, (0, x))
    distance = [1e9] * (n+1)
    distance[x] = 0

    while q:
        dist, now = heapq.heappop(q)

        # 기존의 거리가 더 짧다면 continue
        if distance[now] < dist:
            continue

        for i in arr[now]:
            cost = dist + i[1]
            # 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧다면
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost,i[0]))

    return distance

# x의 집에서 본인의 집으로 돌아갈 때의 거리
dist = dijkstra(x, graph)
dist[0] = 0

reversed_dist = dijkstra(x, reversed_graph)
reversed_dist[0] = 0

print(max([dist[i] + reversed_dist[i] for i in range(1,n+1) if i != x]))