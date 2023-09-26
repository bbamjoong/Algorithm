import sys
input = sys.stdin.readline

n = int(input())
dp = [0]*31

dp[1]=1
dp[2]=3

# 일반적인 타일 깔기의 경우의 수를 구한다.
for i in range(3,n+1):
    dp[i] = dp[i-1] + 2*dp[i-2]

# 중복되는 타일을 제거한다.
if n>=3:
    if n%2==0:
        print((dp[n] - (2*dp[(n-2)//2] + dp[n//2]))//2 + (2*dp[(n-2)//2] + dp[n//2]))
    else:
        print((dp[n] - dp[(n-1)//2])//2 + dp[(n-1)//2])
else:
    print(dp[n])