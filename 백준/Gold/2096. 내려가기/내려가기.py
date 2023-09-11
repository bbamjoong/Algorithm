import sys
input = sys.stdin.readline

n = int(input())

max_dp = [0,0,0]
min_dp = [0,0,0]

for _ in range(n):
    a,b,c = map(int, input().split())

    max_tmp = []
    min_tmp = []

    max_tmp.append(max(a + max_dp[0], a + max_dp[1]))
    min_tmp.append(min(a + min_dp[0], a + min_dp[1]))

    max_tmp.append(max(b + max_dp[0], b + max_dp[1], b + max_dp[2]))
    min_tmp.append(min(b + min_dp[0], b + min_dp[1], b + min_dp[2]))

    max_tmp.append(max(c + max_dp[1], c + max_dp[2]))
    min_tmp.append(min(c + min_dp[1], c + min_dp[2]))

    max_dp = max_tmp
    min_dp = min_tmp

print(max(max_dp), min(min_dp))