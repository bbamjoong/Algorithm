import sys
input = sys.stdin.readline
n=int(input())
li=list(map(int, input().split()))
dp = li[:]

for i in range(n):
    for j in range(i):
        if li[i] > li[j]:
            dp[i] = max(dp[i], dp[j] + li[i])
print(max(dp))
