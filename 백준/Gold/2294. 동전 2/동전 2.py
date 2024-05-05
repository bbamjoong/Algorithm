import sys
input = sys.stdin.readline

n, k = map(int, input().split())
li =[int(input()) for _ in range(n)]


dp = [11111] * (k+1) # k는 최대 10000
dp[0] = 0 # 0원은 0가지

for num in li:
    for i in range(num, k+1):
        dp[i] = min(dp[i], dp[i - num] + 1)

if dp[k] == 11111:
    print(-1)

else:
    print(dp[k])