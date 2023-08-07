import sys
input = sys.stdin.readline
def dfs(start, now, value, cnt):
    global ans

    # 모든 곳을 방문했을 때
    if cnt == n:
        # 마지막 섬에서 처음 섬으로 이동할 수 있다면
        if a[now][start] != 0:
            value += a[now][start]
            if ans > value:
                ans = value
        return

    # 지금까지 구한 ans보다 value가 크다면 return
    if value > ans:
        return

    for i in range(n):
        if not visited[i] and a[now][i]:
            visited[i] = 1
            dfs(start, i, value + a[now][i], cnt + 1)
            visited[i] = 0


n = int(input())
a = [list(map(int, input().split()))for _ in range(n)]
visited = [0] * n

ans = sys.maxsize

for i in range(n):
    visited[i] = 1
    dfs(i, i, 0, 1)
    visited[i] = 0
    
print(ans)