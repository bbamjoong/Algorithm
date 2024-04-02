import sys
input = sys.stdin.readline

MOD = 1_000_000_007

n, q = map(int, input().split())
Ai = [0] + list(map(int, input().split()))

li = [[] for _ in range(n + 1)]
for i in range(n - 1):
    a, b = map(int, input().split())
    li[a].append(b)
    li[b].append(a)


def dfs(now, num):
    global b, ans

    if now == b:
        ans = num
        return

    for i in li[now]:
        if not visited[i]:  # 방문 안한 다음점
            visited[i] = True
            dfs(i, str(int(num + str(Ai[i])) % MOD))


for i in range(q):
    a, b = map(int, input().split())
    visited = [False] * (n + 1)
    visited[a] = True  # 방문처리

    ans = 'a'
    dfs(a, str(Ai[a]))
    print(ans)
