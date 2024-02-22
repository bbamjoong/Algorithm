import sys
input = sys.stdin.readline

n = int(input());
# dp[i][j][k]는 길이가 i이고 마지막 숫자가 j이며 비트마스크 k를 사용하여 만들 수 있는 계단 수의 개수
dp = [[[0 for _ in range(1 << 10)] for _ in range(10)] for _ in range(n)]

for i in range(1, 10): # 첫 숫자는 0이 될 수 없다.
    dp[0][i][1<<i] = 1 # 첫 숫자를 고른 경우의 비트를 활성화 해주고 이때 경우의 수는 1이다.
    
for i in range(1, n): # n개의 자리수
    for j in range(10): # 0 ~ 9
        for k in range(1<<10): # 숫자는 10개이다.
            if j - 1 >= 0:
                # 길이가 하나 작고, 현재 도달할 수 보다 마지막 수가 작은 수의 비트를 더해줍니다.
                dp[i][j][k | (1 << j)] += dp[i-1][j-1][k] 
            
            if j + 1 <= 9:
                # 길이가 하나 작고, 현재 도달할 수 보다 마지막 수가 큰 수의 비트를 더해줍니다.
                dp[i][j][k | (1 << j)] += dp[i-1][j+1][k]
            # 연산 이후 문제의 조건에 맞게 나누어 준다.                
            dp[i][j][k | (1 << j)] %= 1000000000

ans = 0;            
for j in range(10):
    ans += dp[n-1][j][(1<<10) - 1];
    ans %= 1000000000
    
print(ans)