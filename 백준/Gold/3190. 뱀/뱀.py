import sys
input = sys.stdin.readline

n = int(input())
k = int(input())

# 맵 만들고 / 사과가 있는 곳은 1로 변경
board = [[0] * n for _ in range(n)]
for i in range(k):
    x, y = map(int, input().split())
    board[x-1][y-1] = 1

# 현재 뱀 꼬리가 어떤 좌표에 있는지 나타냄
visited = [[False] * n for _ in range(n)]

l = int(input())
dict = {}
for i in range(l):
    a, b = map(str, input().split())
    dict[int(a)] = b

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

x, y = 0, 0
cnt = 0
direction = 0

q = [(0,0)]
visited[0][0] = True

while True:
    cnt += 1
    x += dx[direction]
    y += dy[direction]

    # 맵 밖을 벗어나면 종료
    if x < 0 or x >= n or y < 0 or y >= n:
        break

    # 사과를 먹었고, 뱀의 몸이 없는 곳이라면
    if board[x][y] == 1 and visited[x][y] == False:
        # 사과를 제거해주고
        board[x][y] = 0
        # queue에 좌표 입력 / 방문표시
        q.append((x,y))
        visited[x][y] = True

        # 방향 전환
        if cnt in dict:
            if dict[cnt] == 'D':
                direction = (direction + 1) % 4
            elif dict[cnt] == 'L':
                direction = (direction - 1) % 4
    
    # 사과가 없는 곳이고 , 뱀의 몸이 없는 곳이라면
    elif board[x][y] == 0 and visited[x][y] == False:
        # queue에 좌표 입력 / 방문표시
        q.append((x,y))
        visited[x][y] = True
        # 꼬리부분 queue에서 제거 / 방문최신화
        nx, ny = q.pop(0)
        visited[nx][ny] = False

        # 방향 전환
        if cnt in dict:
            if dict[cnt] == 'D':
                direction = (direction + 1) % 4
            elif dict[cnt] == 'L':
                direction = (direction - 1) % 4        
    
    else:
        break

print(cnt)