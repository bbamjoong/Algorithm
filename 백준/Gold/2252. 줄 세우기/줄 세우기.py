import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())

# 진입차수
indegree = [0] * (n+1)
# 각 노드에 연결된 간선 정보
graph = [[] for _ in range(n+1)]

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    indegree[b] += 1

res = []
q = deque()

# 진입차수가 0이면 queue에 원소 추가
for i in range(1, n+1):
    if indegree[i] == 0:
        q.append(i)

while q:
    now = q.popleft()
    res.append(now)

    # res에 노드가 추가되면, 노드와 간선으로 연결된 다른 노드들의 진입차수를 1 감소
    for i in graph[now]:
        indegree[i] -= 1

        # 연결된 다른 노드들의 진입차수가 0이라면 queue에 원소 추가
        if indegree[i] == 0:
            q.append(i)

print(*res)