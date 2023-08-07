import sys
input = sys.stdin.readline

n, m, v = map(int, input().split())
arr = [[] for i in range(n+1)]

for i in range(m):
    a, b = map(int, input().split())
    arr[a].append(b)
    arr[b].append(a)

for i in range(len(arr)):
    arr[i].sort()

def dfs(graph, start, visited):
    visited[start] = True
    print(start, end=' ')
    
    for i in graph[start]:
        if not visited[i]:
            dfs(graph, i, visited)
from collections import deque

def bfs(graph, start, visited):
    queue = deque([start])
    visited[start] = True
    while queue:
        v = queue.popleft()
        print(v, end=' ')
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True


visited = [False] * (n+1)
dfs(arr, v, visited)
print()
visited = [False] * (n+1)
bfs(arr, v, visited)