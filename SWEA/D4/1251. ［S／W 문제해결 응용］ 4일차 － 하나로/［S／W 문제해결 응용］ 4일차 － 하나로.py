import heapq

T = int(input())


def prim(start):
    global ans, cnt
    pq = []
    heapq.heappush(pq, [0, start])
    cnt += 1

    while len(pq) != 0 & cnt < n:
        node = heapq.heappop(pq);
        now = node[1];
        weight = node[0];

        if visited[now]:  # 방문했으면 continue
            continue;

        visited[now] = True;
        ans += weight;

        for getnode in arr[now]:
            if not visited[getnode[1]]:
                heapq.heappush(pq, getnode);


for tc in range(1, T + 1):
    n = int(input())
    xList = list(map(int, input().split()));
    yList = list(map(int, input().split()));
    e = float(input())

    arr = [[] for _ in range(n + 1)]
    # 조합을 짭니다.
    for i in range(n):
        for j in range(i + 1, n):
            dist = (xList[i] - xList[j]) ** 2 + (yList[i] - yList[j]) ** 2;
            arr[i].append([dist, j]);
            arr[j].append([dist, i]);

    visited = [False for _ in range(n + 1)]

    ans = 0;
    cnt = 0;
    prim(1);
    ans *= e;
    print(f"#{tc} {round(ans)}")