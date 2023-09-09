import sys
input = sys.stdin.readline

n, k = map(int, input().split())

thing = [[0,0]]
for _ in range(n):
    thing.append(list(map(int, input().split())))

dp = [[0] * (k+1) for _ in range(n+1)]

for i in range(1,n+1):
    for j in range(1,k+1):
        weight = thing[i][0]
        value = thing[i][1]

        if j < weight: #가방에 못 넣는 경우:
            dp[i][j] = dp[i-1][j]

        else: #가방에 넣을 수 있는 경우:
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight] + value)

print(dp[n][k])