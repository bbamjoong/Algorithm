import sys
input = sys.stdin.readline

n = int(input())
if n == 1:
    print(2)
elif n == 2:
    print(7)
elif n == 3:
    print(22)
else:
    dp = [0] * (n+1)

    dp[1] = 2
    dp[2] = 7
    dp[3] = 22

    sm = 1
    for i in range(4, n + 1):
        sm += dp[i-3]
        dp[i] = (sm * 2 + dp[i-2] * 3 + dp[i-1] * 2) % 1_000_000_007

    print(dp[n])
