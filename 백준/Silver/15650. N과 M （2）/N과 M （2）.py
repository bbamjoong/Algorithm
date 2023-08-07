import sys
input = sys.stdin.readline

n,m = map(int, input().split())
res=[]

def dfs():
    if len(res) == m:
        print(' '.join(map(str, res)))
        return
    
    for i in range(1,n+1):
        if i not in res:
            if len(res) == 0:
                res.append(i)
                dfs()
                res.pop()
            elif i > res[-1]:
                res.append(i)
                dfs()
                res.pop()

dfs()