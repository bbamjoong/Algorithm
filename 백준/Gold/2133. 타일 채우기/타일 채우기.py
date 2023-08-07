import sys
input = sys.stdin.readline
n = int(input())
a = int(n // 2)
dp = [0, 3, 11] + [0] * (a-2)
if n % 2 == 1:
    print(0)
else:
    for i in range(3,a+1):
        dp[i] = dp[i-1] * 3  + 2
        dp[i] += sum(dp[1:i-1]*2)
    print(dp[a])
