import sys
input = sys.stdin.readline

n = int(input())

dp = [n // 2 + 1] * (n + 1)
dp[0] = 0
coins = [2, 5]

for i in coins:
    for j in range(i, n + 1):
        dp[j] = min(dp[j], dp[j - i] + 1)

print(-1) if dp[n] == (n // 2 + 1) else print(dp[n])