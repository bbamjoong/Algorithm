import sys
input = sys.stdin.readline

n, m = map(int, input().split())
x, y, d = map(int, input().split())

dx = [-1,0,1,0]
dy = [0,1,0,-1]

graph = [list(map(int, input().split())) for _ in range(n)]

def dfs(x, y, d, cnt):
    # 현재칸이 청소되지 않았다면 청소 후 cnt+1
    if graph[x][y] == 0:
        graph[x][y] = 2
        cnt += 1
    
    # 주변 네칸이 벽이거나 청소가 되었다면
    if graph[x-1][y] != 0 and graph[x+1][y] != 0 and graph[x][y-1] != 0 and graph[x][y+1] != 0:
        nx, ny = x - dx[d], y - dy[d]
        # 바라보는 방향의 뒤쪽 칸이 벽이라 후진이 불가능하다면
        if graph[nx][ny] == 1:
            print(cnt)
            exit()
        # 후진이 가능하다면
        else:
            dfs(nx,ny,d,cnt)

    # 청소할 공간이 있다면
    else:
        #반시계로 90도 회전
        d = (d-1) % 4
        nx, ny = x + dx[d], y + dy[d]
        # 회전한 방향의 앞쪽 칸이 청소가 가능한 칸이라면 전진
        if graph[nx][ny] == 0:
            dfs(nx,ny,d,cnt)
        # 아니라면 다시 반시계로 90도 회전해야하므로 현재좌표에서 dfs 호출
        else:
            dfs(x,y,d,cnt)
        
dfs(x, y, d, 0)
