import sys
input = sys.stdin.readline
n = int(input())
li1 = list(map(int, input().split()))
dp = [1] * n

for i in range(1,n):
    for j in range(i):
        if li1[i] > li1[j]:
            dp[i] = max(dp[i], dp[j]+1)

print(max(dp))