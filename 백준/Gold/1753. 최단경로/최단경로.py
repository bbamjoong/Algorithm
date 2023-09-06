import sys
input = sys.stdin.readline
import heapq

import sys
input = sys.stdin.readline

v, e = map(int, input().split())

start = int(input())

graph = [[] for _ in range(v+1)]

for i in range(e):
    li = list(map(int, input().split()))

    graph[li[0]].append([li[1],li[2]])

def dijkstra(x):
    distance = [1e9] * (v+1)
    distance[0] = 0
    distance[x] = 0
    
    q = []
    heapq.heappush(q, [0, x])

    while q:
        dist, now = heapq.heappop(q)

        if distance[now] < dist :
            continue

        for i in graph[now]:
            cost = dist + i[1]

            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q,[cost, i[0]])
    
    return distance

res = dijkstra(start)                

for i in res[1:]:
    if i == 1e9:
        print('INF')
    else:
        print(i)