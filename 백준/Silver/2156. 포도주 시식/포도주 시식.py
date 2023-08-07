import sys
input = sys.stdin.readline

n = int(input())
li = []
[li.append(int(input())) for _ in range(n)]
dp = [0] * n

if n>=1:
    dp[0] = li[0]
if n>=2:
    dp[1] = li[0] + li[1]
if n>=3:
    dp[2] = max(li[0]+li[1], li[0]+li[2], li[2]+li[1])
if n>=4:
    for i in range(3,n):
        dp[i] = max(dp[i-1], dp[i-2]+li[i], dp[i-3]+li[i-1]+li[i])

print(max(dp))