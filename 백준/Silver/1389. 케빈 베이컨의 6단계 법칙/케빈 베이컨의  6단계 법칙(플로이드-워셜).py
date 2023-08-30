import sys
input = sys.stdin.readline

#입력
n, m = map(int, input().split())
graph = [[n for _ in range(n)] for _ in range(n)]

for _ in range(m):
    a, b = map(int, input().split())
    graph[a-1][b-1] = 1
    graph[b-1][a-1] = 1

#플로이드-워셜(경로의 최소값 구하는 알고리즘)
for k in range(n): #거칠 경로
    for i in range(n): # 시작점
        for j in range(n): # 도착점
            if i == j:
                graph[i][j] = 0 #본인이 도착값일 필요는 없다
            else:
                graph[i][j] = min(graph[i][j],
                                       graph[i][k] + graph[k][j])

ans = []
for i in graph:
    ans.append(sum(i))
print(ans.index(min(ans)) + 1)
