import sys
input = sys.stdin.readline
import heapq

n = int(input())
m = int(input())

graph = [[] for _ in range(n+1)]

for i in range(m):
    a, b, c = map(int, input().split())
    graph[a].append([b,c])

start, end = map(int, input().split())
distance = [1e9] * (n+1)

ans = [[] for _ in range(n+1)]

def dijkstra(start):
    distance[0] = 0
    distance[start] = 0

    q = []
    heapq.heappush(q, [0, start, [start]])

    while q:
        dist, now, arr = heapq.heappop(q)

        if distance[now] < dist:
            continue

        for i in graph[now]:
            cost = dist + i[1]
            
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q,[cost, i[0], arr + [i[0]]])
                ans[i[0]] = arr + [i[0]]


dijkstra(start)

print(distance[end])
print(len(ans[end]))
print(*ans[end])
