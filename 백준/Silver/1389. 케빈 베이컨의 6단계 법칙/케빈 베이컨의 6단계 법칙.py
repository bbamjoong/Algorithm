import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

n, m = map(int, input().split())

# 양방향 그래프
graph = [[] for _ in range(n+1)]
for i in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def dfs(now, end, depth):
    global res

    # 끝점 도달시 res를 가장 작은 값으로
    if now == end:
        res = min(depth, res)
        return

    # 현재 탐색중에 depth가 res에 도달 시 return (더 큰 값은 필요없으므로)
    if depth == res:
        return
    
    # 방문하지 않은곳이라면 depth+=1 후 방문
    for i in graph[now]:
        if not visited[i]:
            visited[i] = True
            dfs(i, end, depth + 1)
            # 한 가지의 경우 탐색 후 return을 받으면 방문했던 경로를 해제해야한다.
            visited[i] = False

ans = []
for i in range(1,n+1):
    cnt = 0
    for j in range(1,n+1):
        if i != j:
            res = 1e9
            visited = [False] * (n+1)
            dfs(i, j, 0)
            cnt += res
    ans.append(cnt)

print(ans.index(min(ans)) + 1)
