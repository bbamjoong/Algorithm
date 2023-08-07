import sys
input = sys.stdin.readline

dx = [0,0,-1,1]
dy = [-1,1,0,0]

m, n = map(int, input().split())
arr = [list(input().strip()) for _ in range(m)]
visited = [[False] * n for _ in range(m)]


visited = [0] * 26
visited[ord(arr[0][0])-65] = 1

length = 1
mx = 1

def dfs(x,y):
    global length, mx

    for i in range(4):
        nx, ny = x+dx[i], y+dy[i]

        if nx<0 or nx>=m or ny<0 or ny>=n:
            continue

        if visited[ord(arr[nx][ny])-65] == 0:
            visited[ord(arr[nx][ny])-65] = 1
            length += 1
            mx = max(mx, length)
            dfs(nx,ny)
            visited[ord(arr[nx][ny])-65] = 0
            length -= 1        

dfs(0,0)
print(mx)
