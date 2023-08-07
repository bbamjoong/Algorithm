import sys

sys.setrecursionlimit(5000)
input = sys.stdin.readline


import sys
input = sys.stdin.readline

from collections import deque
sys.setrecursionlimit(5000)

n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]

for i in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def bfs(start):
    queue = deque([start])
    visited[start] = True

    while queue:
        node = queue.popleft()
        for i in graph[node]:
            if not visited[i]:
                visited[i] = True
                queue.append(i)

visited = [False] * (1+n)
cnt = 0
for i in range(1, n+1):
    if not visited[i]:
        bfs(i)
        cnt += 1

print(cnt)