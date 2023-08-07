import sys
input = sys.stdin.readline

n, s = map(int, input().split())
arr = list(map(int, input().split()))
cnt = 0

def dfs(idx, sm):
    global cnt

    if idx == n:
        if sm==s:
            cnt +=1
        else:
            pass
        return
    
    dfs(idx+1,sm+arr[idx])
    dfs(idx+1,sm+0)


dfs(0,0)

if s == 0:
    print(cnt-1)
else:
    print(cnt)