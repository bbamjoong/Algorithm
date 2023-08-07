import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
parents = [0] * (n+1)
visited = [[] for _ in range(n+1)]
arr = [[] for _ in range(n+1)]

for i in range(n-1):
    a, b = map(int, input().split())
    arr[a].append(b)
    arr[b].append(a)

def bfs():
    q = deque()
    q.append(1)
    visited[1] = True
    while q:
        x = q.popleft()
        for i in arr[x]:
            if not visited[i]:
                visited[i] = True
                parents[i] = x
                q.append(i)

bfs()
for i in range(2,n+1):
    print(parents[i])