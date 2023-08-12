import sys
input = sys.stdin.readline

n = int(input())
graph = [[0] * 101 for _ in range(101)]
# 0, 1, 2, 3 방향에 맞게 dx, dy 설정
dx = [0, -1, 0, 1]
dy = [1, 0, -1, 0]

for i in range(n):
    y, x, d, g = map(int, input().split())
    graph[x][y] = 1

    # 좌표가 나아갈 방향 리스트
    dir = [d]
    # 세대 수 만큼 반복
    for j in range(g):
        # 방향은 결국엔 시계방향으로 90도씩 돌기 때문에 (dir+1) % 4를 해준다.
        # 다음 세대로 나아갈 때는 출발해서 도착할때까지의 방향의 역순으로 배치가 된다.
        for k in range((len(dir))-1,-1,-1):
            dir.append((dir[k] + 1) % 4)

    for j in range(len(dir)):
        x+=dx[dir[j]]
        y+=dy[dir[j]]

        if x < 0 or x >= 101 or y < 0 or y >= 101:
            continue

        graph[x][y] = 1

ans = 0
for i in range(100):
    for j in range(100):
        if graph[i][j] == 1 and graph[i + 1][j] == 1 and graph[i][j + 1] == 1 and graph[i + 1][j + 1] == 1:
            ans += 1

print(ans)