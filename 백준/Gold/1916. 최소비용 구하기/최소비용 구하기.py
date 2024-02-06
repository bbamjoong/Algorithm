import sys
input = sys.stdin.readline
import heapq

n = int(input())
m = int(input())

graph = [[] for _ in range(n+1)]

for _ in range(m):
    a,b,c = map(int, input().split())
    graph[a].append([b,c])


def dijkstra(start):
    distance = [1e9] * (n+1)
    q = []
    heapq.heappush(q,[0, start])

    while q:
        dist, now = heapq.heappop(q)

        if dist > distance[now]:
            continue

        for i in graph[now]:
            cost = dist + i[1]

            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q,[cost, i[0]])

    return distance

start, end = map(int, input().split())

print(dijkstra(start)[end])