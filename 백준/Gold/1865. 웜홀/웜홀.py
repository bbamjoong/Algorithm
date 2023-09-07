import sys
input = sys.stdin.readline

tc = int(input())

for _ in range(tc):
    # n: 지점, m: 간선, w: 음의 간선
    n,m,w = map(int, input().split())

    graph = []
    
    for i in range(m):
        s, e, t = map(int, input().split())
        graph.append([s,e,t])
        graph.append([e,s,t])

    for i in range(w):
        s, e, t = map(int, input().split())
        graph.append([s,e,-t])

    dist = [1e9] * (n+1)

    def bf(x):
        dist[x] = 0

        for i in range(n):
            for j in range(len(graph)):
                
                start, end, cost = graph[j][0], graph[j][1], graph[j][2]

                if (dist[end] > dist[start] + cost):
                    dist[end] = dist[start] + cost

                    if i == n-1:
                        return True
    
        return False

    if bf(1):
        print("YES")
    else:
        print("NO")