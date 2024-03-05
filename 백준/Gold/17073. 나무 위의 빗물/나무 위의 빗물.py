import sys
input = sys.stdin.readline
from collections import deque

n, water = map(int, input().split())

li = [[] for _ in range(n + 1)]
for i in range(n - 1):
    a, b = map(int, input().split())
    li[a].append(b)
    li[b].append(a)

cnt = 0  # 자식 노드의 개수를 찾는다.


def bfs(now):
    global cnt
    q = deque()
    visited = [False] * (n + 1)

    q.append(now)
    visited[1] = True

    while q:
        now = q.popleft()
        if len(li[now]) == 1 and visited[li[now][0]]:
            # 리프노드 = 간선1개, 그리고 그 간선과 연결된 노드는 이미 방문처리 되어있음
            cnt += 1

        for next in li[now]:
            if not visited[next]:
                visited[next] = True
                q.append(next)


bfs(1)
print(f"{water / cnt:.3f}")