import sys
input = sys.stdin.readline

#입력
n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]

#플로이드-워셜(경로의 최소값 구하는 알고리즘)
for k in range(n): #거칠 경로
    for i in range(n): # 시작점
        for j in range(n): # 도착점
            if graph[i][k] and graph[k][j]:
                graph[i][j] = 1


for i in graph:
    print(*i)