import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

n, m = map(int, input().split())

graph = [[] for _ in range(n+1)]
for i in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def dfs(now, end, length):
    global depth

    if now == end:
        depth = min(length, depth)
        return

    if length == depth:
        return
    
    for i in graph[now]:
        if not visited[i]:
            visited[i] = True
            dfs(i, end, length + 1)
            visited[i] = False

ans = []
for i in range(1,n+1):
    cnt = 0
    for j in range(1,n+1):
        if i != j:
            depth = 1e9
            visited = [False] * (n+1)
            dfs(i, j, 0)
            cnt += depth
    ans.append(cnt)

print(ans.index(min(ans)) + 1)