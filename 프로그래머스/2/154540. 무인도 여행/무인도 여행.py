from collections import deque

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

answer = []

def bfs(maps, i, j, n, m, visited):
    q = deque()
    q.append((i, j))
    visited[i][j] = True
    cnt = int(maps[i][j])
    
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            
            if maps[nx][ny] == 'X':
                continue
            
            if visited[nx][ny]:
                continue
            
            visited[nx][ny] = True
            cnt += int(maps[nx][ny])
            q.append((nx, ny))
            
    answer.append(cnt)

def solution(maps):
    n, m = len(maps), len(maps[0])
    visited = [[False] * m for _ in range(n)]
        
    for i in range(n):
        for j in range(m):
            if maps[i][j] != 'X' and visited[i][j] == False:
                bfs(maps, i, j, n, m, visited)
    
    if answer:
        return sorted(answer)
    else:
        return [-1]