import sys
input = sys.stdin.readline
n = int(input())
dp = [k  for k in range(n+1)]
dp[1] = 1
for i in range(2,n+1):
    for j in range(1,i):
        if j*j > i:
            break
        if dp[i] > dp[i-j*j] + 1:
            dp[i] = dp[i-j*j] + 1
print(dp[n])