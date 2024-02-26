import sys
input = sys.stdin.readline

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
# 체스판의 / 대각선 개수 : 2*n - 1
diag_check1 = [0] * (2*n - 1)
# 체스판의 \ 대각선 개수 : 2*n - 1
diag_check2 = [0] * (2*n - 1)

# 대각선에 놓을 수 있는 비숍의 개수를 미리 계산하는 함수
def upper_bound(diag):
    able_bishops = 0
    for d in range(diag, 2*n - 1):
        for y in range(d + 1):
            x = d - y
            if 0 <= x < n and 0 <= y < n and arr[x][y]:
                able_bishops += 1
                break
    return able_bishops

ans = 0

# 백트래킹을 이용한 비숍 배치 함수
def chess(diag, cnt):
    global ans
    if diag == 2*n - 1:
        ans = max(ans, cnt)
        return

    # 대각선에 놓을 수 있는 비숍의 최대 개수를 미리 계산하여 탐색 범위를 줄임
    ub = upper_bound(diag)
    if ub + cnt <= ans:
        return

    if diag < n:
        x = diag
        y = 0
    else:
        x = n - 1
        y = diag - (n - 1)

    # 조건이 맞으면 현재 대각선에 비숍을 놓는다
    while x >= 0 and y < n:
        if arr[x][y] == 1 and diag_check1[x+y] == 0 and diag_check2[y-x+n-1] == 0:
            diag_check1[x+y] = 1
            diag_check2[y-x+n-1] = 1
            chess(diag+1, cnt+1)
            diag_check1[x+y] = 0
            diag_check2[y-x+n-1] = 0
        x -= 1
        y += 1

    chess(diag+1, cnt)  # 현재 대각선에 비숍을 놓지않는 경우

chess(0, 0)
print(ans)
