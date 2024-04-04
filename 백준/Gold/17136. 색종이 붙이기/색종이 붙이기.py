import sys
input = sys.stdin.readline


def dfs(x, y, cnt):
    global total_paper, first_count

    if first_count == 0:  # 1이 모두 사라졌으면 끝
        total_paper = min(total_paper, cnt)

    if x == 10:  # 마지막 행까지 도달했으면 cnt와 total 비교
        total_paper = min(total_paper, cnt)
        return

    if y >= 10:  # 다음 행 탐색
        dfs(x + 1, 0, cnt)
        return

    if arr[x][y] == 1:
        for size in range(5):  # 색종이 탐색
            if papers[size] == 0:  # 색종이 재고가 0이면 못붙입니다.
                continue

            if x + size >= 10 or y + size >= 10:  # 색종이가 범위를 벗어나면 안됨
                continue

            if not check_value(x, y, size):  # 범위 내에 0이 존재하면 못 붙임
                break

            change_value(x, y, size, 2)  # 붙인 곳은 2로 바꿔줌
            first_count -= size * size

            papers[size] -= 1
            dfs(x, y + size + 1, cnt + 1)

            # 백트래킹.
            papers[size] += 1
            change_value(x, y, size, 1)  # 다시 1로 바꿔줌
            first_count += size * size

    else:
        dfs(x, y + 1, cnt)


def check_value(x, y, size):  # x, y, 색종이 크기
    for i in range(x, x + size + 1):
        for j in range(y, y + size + 1):
            if arr[i][j] == 0 or arr[i][j] == 2:  # 0인 곳에는 색종이를 붙일 수 없다.
                return False

    return True


def change_value(x, y, size, value):
    for i in range(x, x + size + 1):
        for j in range(y, y + size + 1):
            arr[i][j] = value


arr = [list(map(int, input().split())) for _ in range(10)]  # 무조건 10 x 10

first_count = 0
for i in range(10):
    for j in range(10):
        if arr[i][j] == 1:
            first_count += 1

papers = [5, 5, 5, 5, 5]
total_paper = 25

dfs(0, 0, 0)

if total_paper == 25:
    print(-1)
else:
    print(total_paper)

