import sys
input = sys.stdin.readline

n, m, r = map(int, input().split())

item = list(map(int, input().split()))


# 한 노드에서 다른 노드까지의 최소거리를 구해야한다.
# 이 작업을 모든 노드에서 수행해야 하므로
# 다익스트라가 아닌 플로이드-워셜을 이용한다.
graph = [[1e9 for _ in range(n)] for _ in range(n)]

# 길은 양방향이므로 그래프에 길 추가시 유의한다.
for i in range(r):
    a,b,c = map(int, input().split())
    if graph[a-1][b-1] == 1e9:
        graph[a-1][b-1] = c
    else:
        graph[b-1][a-1] = min(graph[a-1][b-1], c)

    if graph[b-1][a-1] == 1e9:
        graph[b-1][a-1] = c
    else:
        graph[b-1][a-1] = min(graph[b-1][a-1], c)



for k in range(n):
    for i in range(n):
        for j in range(n):
            if i == j:
                graph[i][j] = 0
            else:
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

ans = 0
for row in graph:
    cnt = 0
    for i in range(len(row)):
        # 수색범위 이내의 노드라면
        if row[i] <= m:
            # item개수를 추가한다.
            cnt += item[i]
    ans = max(ans, cnt)

print(ans)