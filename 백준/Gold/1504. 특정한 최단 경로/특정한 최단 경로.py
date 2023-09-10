import sys
input = sys.stdin.readline
import heapq

n, e = map(int, input().split())

graph = [[] for _ in range(n+1)]

for _ in range(e):
    a,b,c = map(int, input().split())
    graph[a].append([b,c])
    graph[b].append([a,c])

node1, node2 = map(int, input().split())

def dijkstra(start):
    distance = [1e9] * (n+1)
    distance[start] = 0
    q = []
    heapq.heappush(q, [0, start])

    while q:
        dist, now = heapq.heappop(q)

        if distance[now] < dist:
            continue

        for i in graph[now]:
            cost = dist + i[1]

            if distance[i[0]] > cost:
                distance[i[0]] = cost
                heapq.heappush(q, [cost, i[0]])


    return distance

distance = dijkstra(1)
node1_distance = dijkstra(node1)
node2_distance = dijkstra(node2)

# 1 -> node1 -> node2 -> n
node1_path = distance[node1] + node1_distance[node2] + node2_distance[n]
# 1 -> node2 -> node1 -> n
node2_path = distance[node2] + node2_distance[node1] + node1_distance[n]

ans = min(node1_path, node2_path)

if ans >= 1e9:
    print(-1)
else:
    print(ans)