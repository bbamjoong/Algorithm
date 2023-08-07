import sys
input = sys.stdin.readline
import math
n = int(input())
li1 = []
ans=math.inf
for i in range(n):
    li1.append(list(map(int, input().split())))

for i in range(3):
    dp = [[math.inf, math.inf, math.inf] for _ in range(n)]
    dp[0][i] = li1[0][i]
    for j in range(1,n):    
        dp[j][0] = min(dp[j-1][1], dp[j-1][2]) + li1[j][0]
        dp[j][1] = min(dp[j-1][0], dp[j-1][2]) + li1[j][1]
        dp[j][2] = min(dp[j-1][0], dp[j-1][1]) + li1[j][2]

    for j in range(3):
        if i != j:
            ans = min(ans, dp[-1][j])

print(ans)