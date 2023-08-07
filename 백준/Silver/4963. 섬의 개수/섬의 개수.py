import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

def dfs(x,y):
    global res
    if x<0 or x>=n or y<0 or y>=m:
        return False
    if graph[x][y] == 1:
        graph[x][y] = 0
        dfs(x-1,y)
        dfs(x,y-1)
        dfs(x+1,y)
        dfs(x,y+1)
        dfs(x+1,y+1)
        dfs(x+1,y-1)
        dfs(x-1,y+1)
        dfs(x-1,y-1)
        return True
    return False


while True:
    m, n = map(int, input().split())
    graph = [list(map(int, input().split())) for _ in range(n)]
    res=0
    if m==0 and n==0:
        break
    else:
        for i in range(n):
            for j in range(m):
                if dfs(i, j) == True:
                    res+=1
    print(res)