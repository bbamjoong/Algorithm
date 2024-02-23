import heapq

t = int(input())

def prim(start):
    global ans, cnt
    pq = []
    heapq.heappush(pq, [0, start])

    while cnt < v:
        weight, now = heapq.heappop(pq);

        if visited[now]:
            continue;
    
        visited[now] = True;
        cnt += 1;
        ans += weight;
    
        for getNode in graph[now]:
            weight, next = getNode;
            if not visited[next]:
                heapq.heappush(pq, getNode);


for tc in range(1, t + 1):
    v, e = map(int, input().split())

    graph = [[] for _ in range(v + 1)]  # 정점의 개수 + 1
    visited = [False for _ in range(v + 1)]
    for _ in range(e):
        a, b, c = map(int, input().split())
        graph[a].append([c, b])
        graph[b].append([c, a])

    ans = 0;
    cnt = 0;
    prim(1);

    print(f"#{tc} {ans}")