import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

def dfs(start, visited, graph, group):
    visited[start] = group

    for i in graph[start]:
        if visited[i] == 0:
            res = dfs(i, visited, graph, -group)
            if not res:
                return False
        else:
            if visited[i] == group:
                return False
    return True

k = int(input())

for _ in range(k):
    v, e = map(int, input().split())
    graph = [[] for _ in range(v+1)]
    visited = [0] * (v+1)

    for _ in range(e):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    for i in range(1,v+1):
        if visited[i] == 0:
            res = dfs(i, visited, graph, 1)
            if not res:
                break
    if res:
        print('YES')
    else:
        print('NO')