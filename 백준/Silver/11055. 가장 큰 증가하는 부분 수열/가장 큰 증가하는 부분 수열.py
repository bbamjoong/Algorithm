import sys
input = sys.stdin.readline
n = int(input())
dp = [0] * 1001
for i in map(int, input().split()):
    dp[i] = max(dp[:i]) + i
print(max(dp))