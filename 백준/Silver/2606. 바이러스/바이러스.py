import sys
input = sys.stdin.readline
from collections import deque

n = int(input())
t = int(input())

graph = [[] for _ in range(n+1)]

for i in range(t):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

ans = [False] * (n+1)

q = deque()
q.append(1)
ans[1] = True

while q:
    x = q.popleft()

    for i in graph[x]:
        if ans[i] == False:
            ans[i] = True
            q.append(i)

cnt = -1
for i in ans:
    if i:
        cnt += 1

print(cnt)