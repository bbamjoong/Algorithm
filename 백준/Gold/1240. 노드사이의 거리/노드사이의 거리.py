import sys

input = sys.stdin.readline

n, m = map(int, input().split())
li = [[] for _ in range(n + 1)]

for i in range(n - 1):
    a, b, c = map(int, input().split())
    li[a].append([b, c])
    li[b].append([a, c])


def dfs(now, sm):
    global end, ans
    if now == end:
        ans = sm
        return True

    for next, weight in li[now]:
        if not visited[next]:  # 방문 안했으면
            visited[next] = True
            if dfs(next, sm + weight):
                return True


for i in range(m):
    start, end = map(int, input().split())
    visited = [False] * (n + 1)
    visited[start] = True

    ans = 0
    dfs(start, 0)
    print(ans)