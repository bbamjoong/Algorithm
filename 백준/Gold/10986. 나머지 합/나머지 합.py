import sys
input = sys.stdin.readline

n, m = map(int, input().split())
li = list(map(int, input().split()))

sm = 0
dp = [0] * m # 누적합 저장 dp

for i in range(n):
    sm += li[i]
    dp[sm % m] += 1

ans = dp[0] # 나머지가 0인 것 찾기.

# 나머지가 같은 구간 두개 뽑기.
for i in range(m):
    ans += dp[i] * (dp[i] - 1) // 2

print(ans)