import sys
input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n = int(input())
    dp =  [0, 1, 1, 1, 2, 2, 3, 4, 5, 7, 9]

    if n <= 10:
        print(dp[n])
    else:
        for i in range(11,n+1):
            dp.append(dp[i-1] + dp[i-5])
        print(dp[-1])