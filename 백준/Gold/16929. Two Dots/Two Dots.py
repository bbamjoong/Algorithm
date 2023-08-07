import sys
input=sys.stdin.readline
m,n=map(int,input().split())
arr=[[i for i in input().rstrip()]for _ in range(m)]
visited=[[0] * n for _ in range(m)]
ans=False
dx=[0,1,0,-1]
dy=[1,0,-1,0]
def dfs(color,x,y,cnt):
    global ans,a,b
    if ans==True:
        return
    for i in range(4):
        nx=x+dx[i]
        ny=y+dy[i]
        if nx<0 or nx>=m or ny<0 or ny>=n:
            continue
        if cnt>=4 and nx==a and ny==b:
            ans=True
            return        
        if arr[nx][ny]==color and visited[nx][ny]==0:
            visited[nx][ny]=1
            dfs(color,nx,ny,cnt+1)
            visited[nx][ny]=0
for i in range(m):
    for j in range(n):
        a,b = i,j
        if visited[a][b]==1:
            continue
        else:
            visited[a][b]=1
            dfs(arr[i][j],i,j,1)
        if ans==True:
            print('Yes')
            exit()
if ans!=True:
    print('No')