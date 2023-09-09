import sys
input = sys.stdin.readline

# 한개의 정점 -> 다수의 정점 : 다익스트라, 다수의 정점 -> 다수의 정점 : 플로이드-워셜
# 따라서 플로이드-워셜 알고리즘을 이용한다. 시간복잡도 : O(n**3)

n = int(input())
t = int(input())

graph = [[1e9 for _ in range(n)] for _ in range(n)]

for _ in range(t):
    a,b,c = map(int, input().split())
    if graph[a-1][b-1] != 1e9: # 여러개의 도로가 존재할 경우 최소 값을 이용
        graph[a-1][b-1] = min(graph[a-1][b-1], c)
    else:
        graph[a-1][b-1] = c


#플로이드-워셜(경로의 최소값 구하는 알고리즘)
for k in range(n): #거칠 경로
    for i in range(n): # 시작점
        for j in range(n): # 도착점
            if i == j:
                graph[i][j] = 0 #본인이 도착값일 필요는 없다
            else:
                graph[i][j] = min(graph[i][j],
                                       graph[i][k] + graph[k][j])

for i in range(n):
    for j in range(n):
        if graph[i][j] == 1e9:
            graph[i][j] = 0
            
for i in graph:
    print(*i)