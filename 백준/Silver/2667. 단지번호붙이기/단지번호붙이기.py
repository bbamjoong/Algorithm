import sys
input = sys.stdin.readline

def dfs(x,y):
    global cnt, res
    if x<0 or x>=n or y<0 or y>=n:
        return False
    if graph[x][y] == 1:
        graph[x][y] = 0
        cnt+=1
        dfs(x-1,y)
        dfs(x,y-1)
        dfs(x+1,y)
        dfs(x,y+1)
        return True
    return False

n = int(input())
graph = [list(map(int, input().strip())) for _ in range(n)]

cnt=0
res = []
for i in range(n):
    for j in range(n):
        if dfs(i, j) == True:
            res.append(cnt)
            cnt=0
res.sort()
print(len(res))
[print(i) for i in res]