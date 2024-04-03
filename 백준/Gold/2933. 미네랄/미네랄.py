from collections import deque
import sys
input = sys.stdin.readline

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]


def break_cluster(height, cnt):
    y = 0
    if cnt % 2 == 0: # 왼쪽에서 막대를 던짐
        for j in range(c):
            if arr[height][j] == 'x': # 미네랄을 만났으면 깨버림.
                arr[height][j] = '.'
                y = j # 미네랄이 깨진 곳 저장.
                break

    else: # 오른쪽에서 막대를 던짐. 순서만 반대이고 왼쪽에서 던진것과 같은 로직.
        for j in range(c - 1, -1, -1):
            if arr[height][j] == 'x':
                arr[height][j] = '.'
                y = j
                break

    # y가 0이어도 y가 1 이상인 곳에 미네랄이 있을 수 있으니 탐색은 해줌.
    for j in range(4):
        nx = height + dx[j]
        ny = y + dy[j]

        if nx < 0 or ny < 0 or nx >= r or ny >= c: # 범위 벗어나면 안됨.
            continue

        if arr[nx][ny] == 'x': # 만약 미네랄이라면 클러스터에 추가해줍니다.
            cluster.append([nx, ny])


def bfs(x, y):
    q = deque()
    visited = [[False] * c for _ in range(r)]
    q.append([x, y])
    visited[x][y] = True

    li = []
    while q:
        x, y = q.popleft()

        if x == r - 1: # 제일 아래쪽이면 떨어트릴 필요도 없음.
            return

        if arr[x + 1][y] == '.': # 아래쪽이 땅이라면 떨어져야함.
            li.append([x, y])

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or ny < 0 or nx >= r or ny >= c: # 범위 벗어나면 안됨.
                continue

            if visited[nx][ny]: # 이미 방문한 곳도 안됨
                continue

            if arr[nx][ny] != 'x': # 미네랄이어야해. 옮길 집단을 찾는다.
                continue

            visited[nx][ny] = True
            q.append([nx, ny])

    # bfs로 떨어뜨릴 애들 탐색이 끝났으면 진짜 떨어뜨려야죠.
    fall(visited, li)


def fall(visited, li):
    # 모든 점을 1씩 떨어뜨려보면서 바닥에 도달하면 break 해버리는것임.
    # 혹은 바닥이 아니라 아래쪽이 다른 미네랄이 있다면 멈춰야겠지.
    fall_amount = 1 # 일단 한칸 떨어뜨리고, 더 떨어뜨릴 수 있으면 더 떨어뜨림.

    finish = False # 끝났다는 의미의 boolean 변수
    while True:
        for x, y in li:
            if x + fall_amount == r - 1: # 바닥에 도달하면 반드시 멈춰야지.
                finish = True
                break

            # 떨어뜨릴곳 그 아래가 미네랄이라면? 그러면 못떨어뜨려요.
            if arr[x + fall_amount + 1][y] == 'x':
                # 그리고 그곳이 내 클러스터이면 안됨.
                if not visited[x + fall_amount + 1][y]:
                    finish = True
                    break

        if finish:
            break

        # 끝난게 아니면 떨어뜨릴 간격을 증가시켜서 탐색해본다.
        fall_amount += 1

    for x in range(r - 2, -1, -1): # 아래쪽부터 떨어뜨립시다.
        for y in range(c):
            if arr[x][y] == 'x' and visited[x][y]:
                arr[x][y] = '.' # 현재 미네랄을 .으로 바꾸고 -> 그 아래를 x로 바꿈
                arr[x + fall_amount][y] = 'x'


r, c = map(int, input().split())
arr = [list(input().strip()) for _ in range(r)]

n = int(input())
sticks = list(map(int, input().split()))

cluster = deque()

for cnt in range(n):
    height = sticks[cnt] # 높이

    # 높이와 순번을 입력해줌. -> 왼쪽, 오른쪽에서 던지는게 다름.
    # 아래쪽이1, 위쪽이 r임.
    break_cluster(r - height, cnt)

    # q가 빌때까지 bfs()
    while cluster:
        x, y = cluster.popleft()
        bfs(x, y)


for i in range(r):
    for j in range(c):
        print(arr[i][j], end='')
    print()
