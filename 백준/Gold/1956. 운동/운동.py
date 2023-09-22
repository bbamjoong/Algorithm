import sys
input=sys.stdin.readline

v, e = map(int, input().split())

graph = [[1e9] * v for _ in range(v)]

for i in range(e):
    a,b,c = map(int, input().split())
    graph[a-1][b-1] = c


for i in range(v):
    for k in range(v):
        for j in range(v):
            graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])


ans = 1e9
for i in range(v):
    ans = min(ans, graph[i][i])

print(ans if ans != 1e9 else -1)