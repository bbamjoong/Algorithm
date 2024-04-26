import sys
input = sys.stdin.readline


n, k = map(int, input().split())

values = []
for i in range(n):
    a = int(input())
    values.append(a)

values.sort()

dp = [0] * (k + 1)
dp[0] = 1

for c in values:
    for i in range(c, k + 1):
        dp[i] += dp[i - c]
        
print(dp[k])