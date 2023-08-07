import sys
input=sys.stdin.readline

m,n=map(int,input().split())
arr=[[i for i in input().rstrip()]for _ in range(m)]
visited=[[0] * n for _ in range(m)]

ans=False

dx=[0, 1, 0, -1]
dy=[1, 0, -1, 0]

def dfs(color,x,y,cnt):
    global ans,a,b
    if ans == True:
        return
    
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]

        # graph를 벗어나면 안됨
        if nx < 0 or nx >= m or ny < 0 or ny >= n:
            continue
        # 만약 이동횟수가 4번이라면 ㅁ 모양을 형성할 수 있다.
        # 따라서 이동횟수가 4번이상 그리고 다음 이동할(nx,ny)좌표가 처음 좌표 (a,b)라면
        # ans를 True로 바꾸고 반환
        if cnt >= 4 and nx == a and ny == b:
            ans=True
            return        
        
        # 색깔이 같고 방문하지 않은 곳이라면
        if arr[nx][ny] == color and visited[nx][ny] == 0:
            visited[nx][ny]=1
            dfs(color, nx, ny, cnt+1)
            # 조건을 달성하지 못했을 경우 방문하지 않은 것으로 최신화
            visited[nx][ny]=0

for i in range(m):
    for j in range(n):
        a,b = i,j

        if visited[a][b] == 1:
            continue

        else:
            visited[a][b] = 1
            dfs(arr[i][j], i, j, 1)
        # ans가 True라면 'YES'출력
        if ans == True:
            print('Yes')
            exit()

if ans!=True:
    print('No')
