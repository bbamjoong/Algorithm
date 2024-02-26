import sys
input = sys.stdin.readline

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
# 체스판의 / 대각선 개수 : 2*n - 1
diag_check1 = [0] * (2*n - 1)
# 체스판의 \ 대각선 개수 : 2*n - 1
diag_check2 = [0] * (2*n - 1)

dp = [0] * (2*n - 1)
if arr[n-1][n-1] == 1:
    dp[-1] = 1

for d in range(2*n-3, -1, -1):
    for y in range(d+1):
        x = d - y
        if 0 <= x < n and 0 <= y < n and arr[x][y]:
            dp[d] += dp[d+1] + 1
            break

    else:
        dp[d] = dp[d+1]



ans = 0
def chess(diag, cnt):
    global ans
    if diag == 2*n - 1:
        ans = max(ans, cnt)
        return

    # 답은 최대 개수를 찾는 것임
    # 따라서 앞으로 더 높을 수 있는 개수인 dp[diag]에 지금까지 찾은 cnt를 더했을 때
    # ans 이하이면 가지치기를 한다
    if dp[diag] + cnt <= ans:
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