import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
graph = [[] for _ in range(n + 1)]

for _ in range(n-1):
    a, b, c = map(int, input().split())
    graph[a].append([b,c])
    graph[b].append([a,c])
    


def bfs(start):
    visited = [-1] * (n+1)
    q = deque()
    q.append(start)
    visited[start] = 0
    mx = [0,0]

    while q:
        x = q.popleft()
        for node, dist in graph[x]:
            if visited[node] == -1:
                visited[node] = visited[x] + dist
                q.append(node)

                if mx[0] < visited[node]:
                    mx = [visited[node], node]
    return mx

# 1에서 가장 먼 노드
node = bfs(1)[1]

# 가장 먼 노드에서 가장 먼 노드
dist = bfs(node)[0]

print(dist)