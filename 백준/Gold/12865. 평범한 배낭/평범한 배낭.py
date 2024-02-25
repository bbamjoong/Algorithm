import sys
input = sys.stdin.readline

n, k = map(int, input().split())
thing = [list(map(int, input().split())) for _ in range(n)] # 물건[무게, 가치]

dp = [[0] * (k+1) for _ in range(n+1)]

for i in range(1, n+1):
    for j in range(1, k+1): # 무게
        weight = thing[i-1][0]
        value = thing[i-1][1]
        
        if j < weight:
            dp[i][j] = dp[i-1][j] # 위칸의 무게 데려옴
        
        else: # 위칸의 무게 vs 내 무게를 뺀 위의 칸 + 내 가치
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight] + value)
            
print(dp[-1][-1])