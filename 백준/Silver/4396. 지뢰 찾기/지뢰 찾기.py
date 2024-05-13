import sys
input = sys.stdin.readline

# 지뢰찾기는 팔방향 탐색
dx = [-1, -1, -1, 0, 0, 1, 1, 1]
dy = [-1, 0, 1, -1, 1, -1, 0, 1]

n = int(input())

mines = [list(input().rstrip()) for _ in range(n)]
players = [list(input().rstrip()) for _ in range(n)]

for x in range(n):
    for y in range(n):
        if players[x][y] == 'x' and mines[x][y] == '.': # 플레이어가 선택, 평범한 칸
            cnt = 0 # 지뢰 개수

            for i in range(8):
                nx, ny = x + dx[i], y + dy[i]

                # 범위 벗어나면 안됨
                if nx < 0 or ny < 0 or nx >= n or ny >= n:
                    continue

                # 지뢰라면 카운트 증가
                if mines[nx][ny] == '*':
                    cnt += 1

            players[x][y] = cnt

        elif players[x][y] == 'x' and mines[x][y] == '*': # 플레이어가 선택, 지뢰
            for i in range(n):
                for j in range(n):
                    if mines[i][j] == '*':
                        players[i][j] = '*'

for i in range(n):
    for j in range(n):
        print(players[i][j], end='')
    print()