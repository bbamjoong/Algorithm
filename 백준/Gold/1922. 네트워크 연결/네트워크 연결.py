import sys
import heapq

input = sys.stdin.readline

n = int(input())  # 정점
m = int(input())  # 간선

graph = [[] for _ in range(n + 1)]  # 배열에 인접리스트 이용
for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append([c, b])
    graph[b].append([c, a])

visited = [False for _ in range(n + 1)]  # 방문 정보

def prim(start):
    global ans;

    pq = []
    heapq.heappush(pq, [0, start])  # 처음은 weight가 0이다.

    while len(pq) != 0:
        node = heapq.heappop(pq)
        weight = node[0];
        now = node[1];

        if visited[now]:
            continue;

        visited[now] = True;
        ans += weight;

        for getnode in graph[now]:
            if not visited[getnode[1]]:
                heapq.heappush(pq, getnode)


ans = 0;
prim(1);
print(ans);