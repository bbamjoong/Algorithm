import sys
input = sys.stdin.readline
n = int(input())
cost = [list(map(int, input().split())) for i in range(n)]
ans = sys.maxsize
visited = [0] * n

def dfs(start, now, value, cnt):
    global ans
    if cnt==n:
        if cost[now][start] != 0:
            value += cost[now][start]
            if ans > value:
                ans = value
            return

    if value > ans:
        return

    for i in range(n):
        if visited[i] == 0 and cost[now][i] != 0:
            visited[i] = 1
            dfs(start, i, value+cost[now][i], cnt+1)
            visited[i] = 0    

for i in range(n):
    visited[i] = 1
    dfs(i,i,0,1)
    visited[i] = 0
print(ans)