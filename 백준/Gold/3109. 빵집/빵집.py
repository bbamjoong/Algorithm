import sys
input = sys.stdin.readline

dx = [-1, 0, 1]
dy = [1, 1, 1]

r, c = map(int, input().split())

arr = [list(input().strip()) for _ in range(r)]
visited = [[False] * c for _ in range(r)]

def dfs(x, y):
    if y == c - 1:
        return True
    
    for i in range(3):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if nx < 0 or ny < 0 or nx >= r or ny >= c:
            continue
        
        if arr[nx][ny] == "x":
            continue
            
        if visited[nx][ny] == True:
            continue
        
        visited[nx][ny] = True
        if dfs(nx, ny):
            return True
    return False

ans = 0
for i in range(r):
    if dfs(i, 0):
        ans += 1

print(ans)