import sys
input = sys.stdin.readline
n = int(input())
li = list(map(int, input().split()))

dp = [[0] * n for _ in range(2)]
dp[0][0] = li[0]
dp[1][0] = li[0]

for i in range(1,n):
    # dp[0]음수를 제거 안하고 계속 더해갈때 그 때마다 최대값
    dp[0][i] = max(dp[0][i-1] + li[i], li[i])
    # dp[1]음수를 제거 할 때 그 때의 최대값
    dp[1][i] = max(dp[0][i-1], dp[1][i-1] + li[i])
print(max(max(dp[0]),max(dp[1])))